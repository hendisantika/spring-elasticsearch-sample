package com.hendisantika.springelasticsearchsample.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/12/19
 * Time: 06.25
 */
@Document(indexName = "sample", type = "employee")
public class Employee {
    @Id
    private String id;

    @Field(type = FieldType.Object)
    private Organization organization;

    @Field(type = FieldType.Object)
    private Department department;
    private String name;
    private int age;
    private String position;
}
