package com.better.xing.web.rest.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 16:12
 */
@Data
public class PicCaptchaResp implements Serializable{
    private static final long serialVersionUID = 1L;
    private String sessionId;
    private String picCaptchaCode;
    private Integer picCaptchaLimit;
}
