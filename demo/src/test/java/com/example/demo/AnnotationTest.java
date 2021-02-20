package com.example.demo;

import com.example.demo.annotation.Note;
import com.example.demo.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnnotationTest {

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 测试一下注解内容
     */
    @Test
    public void personTest() {
        Person person = new Person();
        Class personClass = person.getClass();
        // 校验person上是否有note注解
        if (personClass.isAnnotationPresent(Note.class)) {
            System.out.println("Person 类上设置了Note注解");
            // 获取Person类上指定的注解
            Note note = (Note) personClass.getAnnotation(Note.class);
            System.out.println(note.toString());
        }
    }


    /**
     *  spring获取src下面所有的类
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void allClass() throws IOException, ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        {
            /*// 1.
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            MetadataReaderFactory metaReader = new CachingMetadataReaderFactory();*/
            // 2.这种方式也可以
            ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
            MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);

            Resource[] resources = resolver.getResources("classpath*:com/example/demo/**/*.class");
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            for (Resource resource : resources) {
                MetadataReader reader = metaReader.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                Class<?> clazz = loader.loadClass(className);
                classList.add(clazz);
            }
        }
        System.out.println("src下面的所有类：" + classList);
    }


}
