package dev.bozlak.on_muhasebe_spring_boot.utils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public static final String BEARER_AUTH_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI onMuhasebeOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ön Muhasebe REST API")
                        .version("v1")
                        .description("""
                                Every endpoint except POST /api/login needs a JWT token.

                                1. Send POST /api/login with your username and password.
                                2. Copy the jwtToken value from the response.
                                3. Press the Authorize button and paste only the token itself,
                                   without the "Bearer " prefix. Swagger UI adds that prefix.

                                The userId and adminId of the endpoints are read from the token's
                                claims by JwtAuthenticationFilter, so they are not request parameters.
                                """))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH_SCHEME_NAME))
                .components(new Components().addSecuritySchemes(
                        BEARER_AUTH_SCHEME_NAME,
                        new SecurityScheme()
                                .name(BEARER_AUTH_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .description("The jwtToken returned by POST /api/login")
                ));
    }
}
