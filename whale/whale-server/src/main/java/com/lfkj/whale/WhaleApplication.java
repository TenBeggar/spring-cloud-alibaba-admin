package com.lfkj.whale;

import com.lfkj.uaa.EnableUaaResource;
import com.lfkj.web.enable.EnableGlobalException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableUaaResource
@EnableGlobalException
@EnableFeignClients(basePackages = {"com.lfkj.user.client"})
@SpringBootApplication
public class WhaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhaleApplication.class, args);
    }

}
