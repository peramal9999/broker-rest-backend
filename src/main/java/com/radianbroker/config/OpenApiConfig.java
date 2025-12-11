package com.radianbroker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	final String securitySchemeName = "bearerAuth";
	 
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(
			            new Components()
			                .addSecuritySchemes(securitySchemeName,
			                    new SecurityScheme()
			                        .name(securitySchemeName)
			                        .type(SecurityScheme.Type.HTTP)
			                        .scheme("bearer")
			                        .bearerFormat("JWT")
			                )
			        )
				.info(new Info()
						.title("Radian Broker-" + activeProfile)
						.description("REST APIs")
						.version("v1.0")
						.contact(new Contact()
								.name("Peramal Services Pvt.Ltd.")
								.url("https://www.peramalservices.com")
								.email("support@peramalservices.com"))
						.termsOfService("License of API")
						.license(new License().name("API license URL").url("#"))
				);
	}
}
