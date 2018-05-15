package com.better.xing.web.nosql.redis.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 22:08
 */
@RedisHash("picCaptcha")
@Data
public class PicCaptchaVo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private String sessionId;
    private String picCaptchaCode;
    private Integer picCaptchaLimit;
}
