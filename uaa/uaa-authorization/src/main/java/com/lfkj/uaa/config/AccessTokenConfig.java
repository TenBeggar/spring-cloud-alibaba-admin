package com.lfkj.uaa.config;

import com.lfkj.uaa.utils.CustomTokenEnhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.Arrays;

@Configuration
public class AccessTokenConfig {

    //令牌存储策略
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("oauth2-jwt.key"), "lfkj-2022".toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("lfkj-oauth2"));
        return jwtAccessTokenConverter;
    }

    //令牌增强
    @Bean
    public TokenEnhancer tokenEnhancer() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(), jwtAccessTokenConverter()));
        return tokenEnhancerChain;
    }

    /*
      [root@localhost ~]# keytool -genkeypair -alias lfkj-oauth2 -keyalg RSA -keypass lfkj-2022 -keystore oauth2-jwt.key -storepass lfkj-2022
      您的名字与姓氏是什么?
        [Unknown]:  zhong
      您的组织单位名称是什么?
        [Unknown]:  lfkj
      您的组织名称是什么?
        [Unknown]:  lk^C[root@localhost ~]# clear
      [root@localhost ~]# keytool -genkeypair -alias lfkj-oauth2 -keyalg RSA -keypass lfkj-2022 -keystore oauth2-jwt.key -storepass lfkj-2022
      您的名字与姓氏是什么?
        [Unknown]:  zhong
      您的组织单位名称是什么?
        [Unknown]:  lfkj
      您的组织名称是什么?
        [Unknown]:  lfkj
      您所在的城市或区域名称是什么?
        [Unknown]:  beijing
      您所在的省/市/自治区名称是什么?
        [Unknown]:  beijing
      该单位的双字母国家/地区代码是什么?
        [Unknown]:  cn
      CN=zhong, OU=lfkj, O=lfkj, L=beijing, ST=beijing, C=cn是否正确?
        [否]:  y


      Warning:
      JKS 密钥库使用专用格式。建议使用 "keytool -importkeystore -srckeystore oauth2-jwt.key -destkeystore oauth2-jwt.key -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。
      [root@localhost ~]# keytool -importkeystore -srckeystore oauth2-jwt.key -destkeystore oauth2-jwt.key -deststoretype pkcs12
      输入源密钥库口令:  lfkj-2022
      已成功导入别名 lfkj-oauth2 的条目。
      已完成导入命令: 1 个条目成功导入, 0 个条目失败或取消

      Warning:
      已将 "oauth2-jwt.key" 迁移到 Non JKS/JCEKS。将 JKS 密钥库作为 "oauth2-jwt.key.old" 进行了备份。
      [root@localhost ~]# keytool -list -rfc --keystore oauth2-jwt.key | openssl x509 -inform pem -pubkey
      输入密钥库口令:  lfkj-2022
      -----BEGIN PUBLIC KEY-----
      MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhoJ6j4kUS8QrVeget8zX
      7mMQ5MwEA+4nsQuO0ZelHjoQiLZAta0iXIpSrXUTVXvgmpzbeIevPEt7w3hsPh25
      VlxQ9Rm1qqKei7l/T1sWtHNM2nfSF5tKUi61mVgMXeIO+Hxe9QEMuk5wq7ck+ZIQ
      TBnryptP/LAa51DClb97q1FclSt1DB4flplg/86ByiHID40JTpm84YmAxIj3wv4w
      /8m751A+jp57mhEgOfTloPlDiTfqe1mh5djya6Qt+5Tl01s5MP0QMipuBtdK8G2T
      +r5UJcJAi0T1XPZMCvbt2/bb1QlwdfwLTlbayTZG+Dp6VAO/Hli05aISPrCT8I6z
      OQIDAQAB
      -----END PUBLIC KEY-----
      -----BEGIN CERTIFICATE-----
      MIIDXTCCAkWgAwIBAgIESGtVzjANBgkqhkiG9w0BAQsFADBfMQswCQYDVQQGEwJj
      bjEQMA4GA1UECBMHYmVpamluZzEQMA4GA1UEBxMHYmVpamluZzENMAsGA1UEChME
      bGZrajENMAsGA1UECxMEbGZrajEOMAwGA1UEAxMFemhvbmcwHhcNMjIwNzE4MTg1
      MjU4WhcNMjIxMDE2MTg1MjU4WjBfMQswCQYDVQQGEwJjbjEQMA4GA1UECBMHYmVp
      amluZzEQMA4GA1UEBxMHYmVpamluZzENMAsGA1UEChMEbGZrajENMAsGA1UECxME
      bGZrajEOMAwGA1UEAxMFemhvbmcwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEK
      AoIBAQCGgnqPiRRLxCtV6B63zNfuYxDkzAQD7iexC47Rl6UeOhCItkC1rSJcilKt
      dRNVe+CanNt4h688S3vDeGw+HblWXFD1GbWqop6LuX9PWxa0c0zad9IXm0pSLrWZ
      WAxd4g74fF71AQy6TnCrtyT5khBMGevKm0/8sBrnUMKVv3urUVyVK3UMHh+WmWD/
      zoHKIcgPjQlOmbzhiYDEiPfC/jD/ybvnUD6OnnuaESA59OWg+UOJN+p7WaHl2PJr
      pC37lOXTWzkw/RAyKm4G10rwbZP6vlQlwkCLRPVc9kwK9u3b9tvVCXB1/AtOVtrJ
      Nkb4OnpUA78eWLTlohI+sJPwjrM5AgMBAAGjITAfMB0GA1UdDgQWBBRN6u5dUkHS
      IG2EOZnu71fUsQFJUDANBgkqhkiG9w0BAQsFAAOCAQEAbmayLudTEuB84hR0GqmY
      BIvleZYg9tJW1KUs7gUb1haSwPt8WlrEmC6DS8MOFzp1kQacg1xtEPYcEu60g5Y7
      sYvpC4rkFZw7tMx42rWMqV9jF6o624Iru/8fALFM+5DQHdeLCEbyhI5LLHfALV/L
      I85jXhiZ614GhggcQfsNIytmMOjGMr8Y+X9SD/vxuLxXkhxfLinJ42kt1SNpJcYJ
      aaj1nm9SEeUrVZNophNudjqSmcDgFKk1nyUne2+AzirJKMP+9JGmXBgKP6eRrabu
      hkTpFgatRif3JenUCBKO9tKWu02cafqkgeg41QLRpz9xTQ5W/31ZmpX3uOEtABWX
      qw==
      -----END CERTIFICATE-----
      */
}
