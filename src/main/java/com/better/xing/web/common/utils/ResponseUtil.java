package com.better.xing.web.common.utils;

import com.better.xing.web.common.enums.ErrorEnum;
import com.better.xing.web.rest.base.RestBaseResp;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 18:32
 */
public class ResponseUtil {
    public static RestBaseResp genSuccess(){
        return genResp(ErrorEnum.SUCCESS);
    }
    public static RestBaseResp genError(){
        return genSuccessAndRespBody(ErrorEnum.ERROR);
    }
    public static RestBaseResp genSuccessAndRespBody(Object body){
        return genErrorEnumAndRespBody(ErrorEnum.SUCCESS,body);
    }
    public static RestBaseResp genResp(ErrorEnum errorEnum){
        return new RestBaseResp(errorEnum.getErrorNo(),errorEnum.getErrorInfo());
    }
    public static RestBaseResp genErrorEnumAndRespBody(ErrorEnum errorEnum,Object body){
        RestBaseResp restBaseResp = new RestBaseResp(errorEnum.getErrorNo(), errorEnum.getErrorInfo());
        restBaseResp.setData(body);
        return restBaseResp;
    }
}
