package com.lfkj.user;

import com.lfkj.uaa.EnableUaaResource;
import com.lfkj.web.enable.EnableGlobalException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableUaaResource
@EnableGlobalException
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
