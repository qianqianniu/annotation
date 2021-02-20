package com.example.demo.entity;

import com.example.demo.annotation.Note;
import lombok.Data;

@Data
// 自定义的note注解
@Note(value = "测试",enable = true)
public class Person {
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 爱好
     */
    private String hobby;
}
