package com.better.xing.web.rest.controller;

import com.better.xing.web.common.annotations.NoLogin;
import com.better.xing.web.rest.base.RestBaseResp;
import com.better.xing.web.rest.request.CheckSmsCaptchaReq;
import com.better.xing.web.rest.request.PicCaptchaReq;
import com.better.xing.web.rest.request.SendSmsCaptchaReq;
import com.better.xing.web.rest.response.PicCaptchaResp;
import com.better.xing.web.service.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 15:27
 */
@Log4j2
@Api(value = "用户登录接口",tags = {"用户登录页面操作"},description = "用户登录接口")
@RestController
public class LoginController {
    @Autowired
    private CaptchaService captchaService;
    @NoLogin
    @ApiOperation(value = "获取图形验证码", notes = "获取图形验证码", tags = "用户登录页面操作")
    @PostMapping(value = "/getPicCaptcha")
    public ResponseEntity<RestBaseResp<PicCaptchaResp>> getPicCaptcha(@ApiParam(name = "user",value = "获取图形验证码参数")@RequestBody PicCaptchaReq req){
        RestBaseResp<PicCaptchaResp> picCaptchaRespRestBaseResp = captchaService.genPicCaptcha(req);
        return ResponseEntity.ok(picCaptchaRespRestBaseResp);
    }
    @NoLogin
    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码", tags = "用户登录页面操作")
    @PutMapping("/sendSmsCaptcha")
    public ResponseEntity<RestBaseResp> sendSmsCaptcha(@ApiParam(name = "user",value = "发送短信验证码参数")@RequestBody SendSmsCaptchaReq req){
        return ResponseEntity.ok(captchaService.sendSmsCaptcha(req));
    }
    @NoLogin
    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码", tags = "用户登录页面操作")
    @PostMapping("/checkSmsCaptcha")
    public ResponseEntity<RestBaseResp> checkSmsCaptcha(@ApiParam(name = "user",value = "发送短信验证码参数")@RequestBody CheckSmsCaptchaReq req){
        return ResponseEntity.ok(captchaService.checkSmsCaptcha(req));
    }
    @ApiOperation(value = "测试", notes = "测试", tags = "用户登录页面操作")
    @PostMapping("/test")
    public ResponseEntity<RestBaseResp> test(@ApiParam(name = "user",value = "发送短信验证码参数")@RequestBody CheckSmsCaptchaReq req){
        return ResponseEntity.ok(null);
    }
}
