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
@ApiModel(value = "发送验证码参数",description = "发送验证码参数")
@Data
@ToString(callSuper = true)
public class SendSmsCaptchaReq extends RestBaseReq {
    @ApiModelProperty(value = "手机号码",notes = "手机号码",dataType = "String")
    private String mobile;
    @ApiModelProperty(value = "图形验证码",notes = "图形验证码",dataType = "String")
    private String picCaptchaCode;
    @ApiModelProperty(value = "业务类型",notes = "业务类型{0:注册，1：登录}",dataType = "Integer")
    private Integer checkType;
}
