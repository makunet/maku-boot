package net.maku;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
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
    public GroupedOpenApi systemApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.maku.system"};
        return GroupedOpenApi.builder()
                .group("1")
                .displayName("System API")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public GroupedOpenApi quartzApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.maku.quartz"};
        return GroupedOpenApi.builder()
                .group("2")
                .displayName("Quartz API")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public GroupedOpenApi monitorApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.maku.monitor"};
        return GroupedOpenApi.builder()
                .group("3")
                .displayName("Monitor API")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public GroupedOpenApi memberApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.maku.member"};
        return GroupedOpenApi.builder()
                .group("4")
                .displayName("Member API")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public GroupedOpenApi iotApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.maku.iot"};
        return GroupedOpenApi.builder()
                .group("5")
                .displayName("Iot API")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public GroupedOpenApi otherApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.maku"};
        return GroupedOpenApi.builder()
                .group("99")
                .displayName("Other API")
                .pathsToMatch(paths)
                .packagesToExclude(
                        "net.maku.system", "net.maku.quartz", "net.maku.monitor", "net.maku.member", "net.maku.iot",
                        "net.maku.generator"
                )
                .packagesToScan(packagedToMatch)
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        Contact contact = new Contact();
        contact.setName("阿沐 babamu@126.com");

        return new OpenAPI().info(new Info()
                .title("Maku API")
                .description("Maku API")
                .contact(contact)
                .version("3.x")
                .termsOfService("https://maku.net")
        );
    }

}