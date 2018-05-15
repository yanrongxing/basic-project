package com.better.xing.web.rest.request;

import com.better.xing.web.rest.base.RestBaseReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 15:41
 */
@ApiModel(value = "校验验证码参数",description = "校验验证码参数")
@Data
@ToString(callSuper = true)
public class CheckSmsCaptchaReq extends RestBaseReq {
    @ApiModelProperty(value = "手机号码",notes = "手机号码",dataType = "String")
    private String mobile;
    @ApiModelProperty(value = "短信验证码",notes = "短信验证码",dataType = "String")
    private String smsCaptchaCode;
    @ApiModelProperty(value = "业务类型",notes = "业务类型{0:注册，1：登录}",dataType = "Integer")
    private Integer checkType;
}
