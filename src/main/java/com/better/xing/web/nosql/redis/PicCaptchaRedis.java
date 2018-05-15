package com.better.xing.web.nosql.redis;

import com.better.xing.web.nosql.redis.vo.PicCaptchaVo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 22:11
 */
public interface PicCaptchaRedis extends CrudRepository<PicCaptchaVo,String>{
}
