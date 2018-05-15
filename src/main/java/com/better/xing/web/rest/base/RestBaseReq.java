package com.better.xing.web.rest.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 16:00
 */
@ApiModel(value = "基础请求参数",description = "基础请求参数")
@Data
public class RestBaseReq {
    @ApiModelProperty(value = "会话ID",notes = "会话ID",dataType = "String")
    private String sessionId;
    @ApiModelProperty(value = "秘钥串",notes = "秘钥串",dataType = "String")
    private String secretKey;
}
