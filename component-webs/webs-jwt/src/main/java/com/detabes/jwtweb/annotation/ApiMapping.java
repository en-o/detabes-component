package com.detabes.jwtweb.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * jwt-web专属注解
 *
 * @author tn
 * @version 1
 * @ClassName ApiMapping
 * @description jwt-web专属注解
 * @date 2020/6/18 16:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
@Inherited
public @interface ApiMapping {
    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] params() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] headers() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] consumes() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] produces() default {};

    @AliasFor(annotation = RequestMapping.class)
    RequestMethod[] method() default {RequestMethod.GET};

    boolean enable() default true;

    boolean checkToken() default true;

}
