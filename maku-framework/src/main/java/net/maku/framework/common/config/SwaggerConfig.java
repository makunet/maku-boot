package net.maku.framework.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 *
 * @author 阿沐  babamu@126.com
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.maku"};
        return GroupedOpenApi.builder().group("MakuBoot")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("阿沐 babamu@126.com");

        return new OpenAPI().info(new Info()
                .title("MakuBoot")
                .description("MakuBoot")
                .contact(contact)
                .version("3.0")
                .termsOfService("https://maku.net")
                .license(new License().name("MIT")
                        .url("https://maku.net")));
    }

}