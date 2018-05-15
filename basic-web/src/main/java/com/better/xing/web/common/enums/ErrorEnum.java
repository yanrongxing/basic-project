package com.better.xing.web.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 18:34
 */
public enum ErrorEnum {
    SUCCESS(0,"SUCCESS"),
    ERROR(-1,"ERROR"),

    ARAS_FAILURE(3001,"参数有误"),


    PIC_CAPTCHA_FAILURE(3101,"图形验证码无效"),
    PIC_CAPTCHA_DIFF(3102,"图形验证码错误"),

    SMS_CAPTCHA_FAILURE(3201,"短信验证码无效"),
    SMS_CAPTCHA_DIFF(3202,"短信验证码错误"),

    MOBILE_EXIST(3301,"此手机号已注册"),

    SESSION_FAILURE(3401,"会话失效"),
    ;
    private Integer errorNo;
    private String errorInfo;

    ErrorEnum() {
    }

    ErrorEnum(Integer errorNo, String errorInfo) {
        this.errorNo = errorNo;
        this.errorInfo = errorInfo;
    }

    public Integer getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(Integer errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
