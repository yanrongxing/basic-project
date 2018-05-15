package com.better.xing.web.rest.base;

import lombok.Data;

import java.util.List;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 16:19
 */
public class RestBaseResp<T> {
    private Integer errorNo;
    private String errorInfo;
    private Integer isResultSet = 0;
    private T data;

    public RestBaseResp() {
    }

    public RestBaseResp(Integer errorNo, String errorInfo) {
        this.errorNo = errorNo;
        this.errorInfo = errorInfo;
    }

    public RestBaseResp(Integer errorNo, String errorInfo, T data) {
        this.errorNo = errorNo;
        this.errorInfo = errorInfo;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        if(data instanceof List){
            isResultSet = 1;
        }
        this.data = data;
    }

    public Integer getIsResultSet() {
        return isResultSet;
    }
}
