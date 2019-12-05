package com.htyl.park.employee;

import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author weiguang
 */
@EnableNacosDiscovery
@MapperScan("com.htyl.park.employee.mapper")
@SpringBootApplication
public class EmployeeProviderBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeProviderBootStrap.class,args);
    }
}