package com.hendisantika.springelasticsearchsample.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/12/19
 * Time: 06.24
 */
@Data
@Getter
@Setter
public class Organization {
    private Long id;
    private String name;
    private String address;
}