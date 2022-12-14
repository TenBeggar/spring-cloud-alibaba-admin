package com.lfkj.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private DataSource dataSource;

    @Bean
    public ClientDetailsService detailsService() {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
//        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(detailsService());
//        clients.inMemory()
//                .withClient("lfkj-internal-client")
//                .secret(passwordEncoder.encode("lfkj-internal-secret"))
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")//???????????????????????????????????????????????????????????????
//                .redirectUris("https://www.baidu.com")
//                .scopes("all", "read", "write");
    }

    @Resource
    private TokenStore tokenStore;
    @Resource
    private TokenEnhancer tokenEnhancer;

    //????????????????????????
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(detailsService());
        defaultTokenServices.setSupportRefreshToken(true);//??????????????????
        defaultTokenServices.setTokenStore(tokenStore);//????????????
        defaultTokenServices.setTokenEnhancer(tokenEnhancer);//????????????
        defaultTokenServices.setAccessTokenValiditySeconds(60 * 60 * 2);//??????????????????
        defaultTokenServices.setRefreshTokenValiditySeconds(60 * 60 * 12);//????????????
        return defaultTokenServices;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenServices(tokenServices())
                .authorizationCodeServices(authorizationCodeServices())//???????????????????????????????????????
                .authenticationManager(authenticationManager);//????????????????????????????????????
//                .userDetailsService(userDetailsService)
//                .allowedTokenEndpointRequestMethods(HttpMethod.POST);//?????????POST???????????? /oauth/token
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")//??????tokenkey?????????????????????????????????????????????JWT?????????key
                .checkTokenAccess("permitAll()")//????????????token?????? /oauth/check_token
                .allowFormAuthenticationForClients();//??????client??????form???????????????authentication??????
    }
}
