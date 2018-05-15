package com.better.xing.web.rest.request;

import com.better.xing.web.rest.base.RestBaseReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 16:54
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "获取验证码参数",description = "发送验证码参数")
public class PicCaptchaReq extends RestBaseReq {
    @ApiModelProperty(value = "图形验证码长度",notes = "图形验证码长度",dataType = "String")
    private Integer length;
}
