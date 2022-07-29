package com.lfkj.uaa;

import com.lfkj.uaa.config.AccessTokenConfig;
import com.lfkj.uaa.config.ResourceServerConfig;
import com.lfkj.uaa.config.WebSecurityConfig;
import com.lfkj.uaa.feign.AuthorizationRequestInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AccessTokenConfig.class,
        ResourceServerConfig.class,
        WebSecurityConfig.class,
        AuthorizationRequestInterceptor.class})
public @interface EnableUaaResource {
}
