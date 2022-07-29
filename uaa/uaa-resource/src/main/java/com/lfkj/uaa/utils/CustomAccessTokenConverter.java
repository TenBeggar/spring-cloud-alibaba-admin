package com.lfkj.uaa.utils;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication oAuth2Authentication = super.extractAuthentication(claims);
        oAuth2Authentication.setDetails(claims);
        return oAuth2Authentication;
    }
}
