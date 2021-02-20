package com.example.demo.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Note {
    // todo 如何有选择的使用这些内容
    String content() default "随便写点内容";
    String value();
    boolean enable();
}
