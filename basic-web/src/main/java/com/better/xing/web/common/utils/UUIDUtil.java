package com.better.xing.web.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 16:44
 */
public class UUIDUtil {
    public static String SESSION_ID = "session_id:";
    public static String PIC_CAPTCHA_ID = "pic_captcha_id:";
    public static String SMS_CAPTCHA_ID = "sms_captcha_id:";
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    public static String getRandomNum(int length) {
        StringBuilder sb=new StringBuilder();
        Random rand = new Random();
        for(int i=0;i<length;i++)
        {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
    //方法1：length为产生的位数
    public static String getRandomString(int length){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxc1vbn2mlk3jhg4fds5aqw6ert7yui8opQ9WER0TYUIOPASDFGHJKLZXCVBNM";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<length; ++i){
            //产生0-61的数字
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }
    /**
     * 第二种方法
     */
    public static String getRandomString2(int length){
        //产生随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //循环length次
        for(int i=0; i<length; i++){
            //产生0-2个随机数，既与a-z，A-Z，0-9三种可能
            int number=random.nextInt(3);
            long result=0;
            switch(number){
                //如果number产生的是数字0；
                case 0:
                    //产生A-Z的ASCII码
                    result=Math.round(Math.random()*25+65);
                    //将ASCII码转换成字符
                    sb.append(String.valueOf((char)result));
                    break;
                case 1:
                    //产生a-z的ASCII码
                    result=Math.round(Math.random()*25+97);
                    sb.append(String.valueOf((char)result));
                    break;
                case 2:
                    //产生0-9的数字
                    sb.append(String.valueOf
                            (new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }
}
