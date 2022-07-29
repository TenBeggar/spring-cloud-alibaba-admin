package com.lfkj.user.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "User API", description = "This is a user project.", version = "0.0.1-SNAPSHOT"),
        servers = @Server(url = "http://192.168.3.22:8080/user"),
        security = @SecurityRequirement(name = "BearerAuth"))
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(password = @OAuthFlow(
                tokenUrl = "http://192.168.3.22:8080/uaa/oauth/token",
                scopes = @OAuthScope(name = "all", description = "Grants for all."))))
public class SpringDocConfig {
}
