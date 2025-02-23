package uz.alfabu.bookrecommendationapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        description = "Bearer Authentication with JWT token [description]",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
@OpenAPIDefinition(security = @SecurityRequirement(name = "bearerAuth"))
@Component
public class SwaggerConfig {
}
