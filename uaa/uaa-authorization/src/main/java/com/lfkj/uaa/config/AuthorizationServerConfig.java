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
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")//授权码模式，密码模式，客户端模式，简化模式
//                .redirectUris("https://www.baidu.com")
//                .scopes("all", "read", "write");
    }

    @Resource
    private TokenStore tokenStore;
    @Resource
    private TokenEnhancer tokenEnhancer;

    //令牌管理服务配置
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(detailsService());
        defaultTokenServices.setSupportRefreshToken(true);//支持令牌刷新
        defaultTokenServices.setTokenStore(tokenStore);//令牌存储
        defaultTokenServices.setTokenEnhancer(tokenEnhancer);//令牌增强
        defaultTokenServices.setAccessTokenValiditySeconds(60 * 60 * 2);//令牌有效时间
        defaultTokenServices.setRefreshTokenValiditySeconds(60 * 60 * 12);//刷新时间
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
                .authorizationCodeServices(authorizationCodeServices())//授权码模式所需的认证管理器
                .authenticationManager(authenticationManager);//密码模式所需的认证管理器
//                .userDetailsService(userDetailsService)
//                .allowedTokenEndpointRequestMethods(HttpMethod.POST);//只允许POST方式请求 /oauth/token
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")//开放tokenkey，资源服务可以通过这个请求得到JWT加密的key
                .checkTokenAccess("permitAll()")//开放远程token检查 /oauth/check_token
                .allowFormAuthenticationForClients();//允许client使用form的方式进行authentication授权
    }
}
