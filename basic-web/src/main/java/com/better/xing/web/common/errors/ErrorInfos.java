package com.better.xing.web.common.errors;

public class ErrorInfos {

    public enum CommonErr implements ErrorInfoBase {

        SUCCESS(0, "执行成功"),
        ERROR(1, "执行错误"),
        COMMON_ERROR(2, "基本错误"),
        PARAM_ERR(1001, "参数错误"),
        TIME_ERR(1002, "时间格式转换错误"),;

        private int error_no;

        private String error_info;

        CommonErr(int error_no, String error_info) {
            this.error_no = error_no;
            this.error_info = error_info;
        }

        public int getError_no() {
            return error_no;
        }

        public String getError_info() {
            return error_info;
        }
    }

    public enum ClientErr implements ErrorInfoBase {

        NO_LOGIN(2000, "未登录或已超时"),
        LOGIN_ERR(2001, "用户名或密码错误"),
        PASSWORD_ERR(2002, "原密码错误"),
        VALIDATE_CODE_ERR(2003, "验证码错误"),
        CLIENT_NOT_EXIST(2004, "用户不存在"),
        NO_PERMISSION(2006, "没有权限"),
        MOBILE_EXIST(2012, "手机号已存在"),
        EMAIL_ERROR(2013, "邮箱格式错误"),
        VERFYBUSY_ERROR(2014, "发送过于频繁请稍后再试"),
        VERFY_DAILY_LIMIT_ERROR(2015, "当日发送上限"),
        VERFY_CODE_ERROR(2016, "验证码错误"),
        SEND_CODE_ERROR(2017, "发送验证码失败"),
        UPLOAD_FILE_TYPE_ERROR(2018, "文件类型不存在"),
        FILE_TRANS_ERROR(2019, "文件传输失败"),
        SEAT_NUM_NOT_ENOUGH_ERROR(2020, "座位不够了"),
        DRIVER_NO_AUTH(2021, "司机尚未认证"),
        ORDER_ACCEPTED(2022, "订单已被接"),
        DRIVER_AUTHED(2023, "您已经提交认证了，请等待审核"),;

        private int error_no;

        private String error_info;

        ClientErr(int error_no, String error_info) {
            this.error_no = error_no;
            this.error_info = error_info;
        }

        public int getError_no() {
            return error_no;
        }

        public String getError_info() {
            return error_info;
        }
    }

}
