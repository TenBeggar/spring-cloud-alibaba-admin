package com.lfkj.uaa.dto;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

public class CustomDetails {

    private Long id;
    private String username;
    private String nickname;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static CustomDetails me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            Map<String, ?> decodedDetails = (Map<String, ?>) details.getDecodedDetails();
            CustomDetails customDetails = new CustomDetails();
            BeanMap beanMap = BeanMap.create(customDetails);
            beanMap.putAll(decodedDetails);
            return customDetails;
        }
        return null;
    }
}
