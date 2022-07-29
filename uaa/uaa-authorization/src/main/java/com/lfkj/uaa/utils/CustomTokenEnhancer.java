package com.lfkj.uaa.utils;

import com.lfkj.uaa.domain.CustomDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomDetails) {
            CustomDetails customDetails = (CustomDetails) principal;
            Map<String, Object> custom = new HashMap<>();
            custom.put("username", customDetails.getUsername());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(custom);
        }
        return accessToken;
    }
}
