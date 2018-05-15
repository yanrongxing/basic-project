package com.better.xing.web.common.annotations;

import java.lang.annotation.*;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/13 19:53
 */
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoLogin {
}
