package com.better.xing.web.service;

import com.better.xing.web.common.enums.ErrorEnum;
import com.better.xing.web.common.utils.ResponseUtil;
import com.better.xing.web.common.utils.UUIDUtil;
import com.better.xing.web.jpa.dao.UserRepository;
import com.better.xing.web.rest.base.RestBaseResp;
import com.better.xing.web.rest.request.CheckSmsCaptchaReq;
import com.better.xing.web.rest.request.PicCaptchaReq;
import com.better.xing.web.rest.request.SendSmsCaptchaReq;
import com.better.xing.web.jpa.model.User;
import com.better.xing.web.rest.response.PicCaptchaResp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 16:37
 */
@Service
@Log4j2
public class CaptchaService {
    @Autowired
    private UserRepository userRepository;
    @Value("${pic.captcha.limit}")
    private Integer limit;
    @Autowired
    private RedisTemplate template;
    public RestBaseResp<PicCaptchaResp> genPicCaptcha(PicCaptchaReq req){
        String sessionId = req.getSessionId();
        if(StringUtils.isEmpty(sessionId)){
            sessionId = UUIDUtil.getUUID();
        }
        String picCaptchaCode = UUIDUtil.getRandomString(req.getLength());
        PicCaptchaResp picCaptchaResp = new PicCaptchaResp();
        picCaptchaResp.setPicCaptchaCode(picCaptchaCode);
        picCaptchaResp.setPicCaptchaLimit(limit);
        picCaptchaResp.setSessionId(sessionId);
        ValueOperations valueOperations = template.opsForValue();
        valueOperations.set(UUIDUtil.PIC_CAPTCHA_ID+sessionId,picCaptchaResp,10, TimeUnit.MINUTES);
        return ResponseUtil.genSuccessAndRespBody(picCaptchaResp);
    }
    public RestBaseResp sendSmsCaptcha(SendSmsCaptchaReq req){
        String sessionId = req.getSessionId();
        String mobile = req.getMobile();
        String picCaptchaCode = req.getPicCaptchaCode();
        Integer checkType = req.getCheckType();
        ValueOperations valueOperations = template.opsForValue();
        Object o = valueOperations.get(UUIDUtil.PIC_CAPTCHA_ID + sessionId);
        if(o == null){//如果根据sessionId找不到图形验证码，告知图形验证码无效
            return ResponseUtil.genResp(ErrorEnum.PIC_CAPTCHA_FAILURE);
        }else {
            PicCaptchaResp picCaptchaResp = (PicCaptchaResp) o;
            Integer picCaptchaLimit = picCaptchaResp.getPicCaptchaLimit();
            if(picCaptchaLimit <= 0){//如果超过使用次数，告知图形验证码无效
                return ResponseUtil.genResp(ErrorEnum.PIC_CAPTCHA_FAILURE);
            }else{
                String picCaptchaCode1 = picCaptchaResp.getPicCaptchaCode();
                if(!(picCaptchaCode+"").equalsIgnoreCase(picCaptchaCode1)){//如果错误，限制次数减1
                    picCaptchaResp.setPicCaptchaLimit(picCaptchaLimit - 1);
                    Long expire = template.getExpire(UUIDUtil.PIC_CAPTCHA_ID + sessionId, TimeUnit.SECONDS);
                    valueOperations.set(UUIDUtil.PIC_CAPTCHA_ID+sessionId,picCaptchaResp,expire, TimeUnit.SECONDS);
                    return ResponseUtil.genResp(ErrorEnum.PIC_CAPTCHA_DIFF);
                }else{
                    String smsCode = UUIDUtil.getRandomNum(6);
                    valueOperations.set(UUIDUtil.SMS_CAPTCHA_ID+sessionId+":"+mobile+":"+checkType,smsCode,10, TimeUnit.MINUTES);
                    //TODO 发送短信验证码代码开始
                    log.info("{}发送的短信验证码是：{}",mobile,smsCode);
                    template.delete(UUIDUtil.PIC_CAPTCHA_ID+sessionId);
                    return ResponseUtil.genSuccess();
                }
            }
        }
    }
    public RestBaseResp checkSmsCaptcha(CheckSmsCaptchaReq req){
        String sessionId = req.getSessionId();
        String mobile = req.getMobile();
        String smsCaptchaCode = req.getSmsCaptchaCode();
        Integer checkType = req.getCheckType();
        ValueOperations valueOperations = template.opsForValue();
        Object smsCaptcha = valueOperations.get(UUIDUtil.SMS_CAPTCHA_ID + sessionId+":"+mobile+":"+checkType);
        if(smsCaptcha == null){
            return ResponseUtil.genResp(ErrorEnum.SMS_CAPTCHA_FAILURE);
        }else{
            String s = smsCaptcha.toString();
            if(!(s+"").equalsIgnoreCase(smsCaptchaCode)){
                return ResponseUtil.genResp(ErrorEnum.SMS_CAPTCHA_DIFF);
            } else {
                User user = userRepository.findUserByMobile(mobile);
                String uuid = UUIDUtil.getUUID();
                if(checkType == 0){//注册
                    if(user != null){
                        return ResponseUtil.genResp(ErrorEnum.MOBILE_EXIST);
                    }else{
                        user = new User();
                        user.setId(uuid);
                        user.setMobile(mobile);
                        userRepository.save(user);
                        template.delete(UUIDUtil.SMS_CAPTCHA_ID + sessionId+":"+mobile+":"+checkType);
                        return ResponseUtil.genSuccess();
                    }
                }else if(checkType == 1){//登录
                    valueOperations.set(sessionId,user);
                    template.delete(UUIDUtil.SMS_CAPTCHA_ID + sessionId+":"+mobile+":"+checkType);
                    return ResponseUtil.genSuccess();
                }
                return ResponseUtil.genSuccess();
            }
        }
    }
}
