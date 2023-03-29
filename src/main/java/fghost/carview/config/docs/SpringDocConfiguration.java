package fghost.carview.config.docs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public GroupedOpenApi publicApiV1() {
        return GroupedOpenApi.builder()
                .packagesToScan("fghost.carview")
                .group("v1")
                .pathsToMatch("/**")
                .build();
    }
//    @Bean
//    public OpenAPI customOpenAPI(@Value("${spring.application.name}") String host) {
//        return new OpenAPI()
//                .addServersItem(new Server().url("/").description(host))
//                .components(new Components())
//                .info(new Info().title("CarView").description(
//                                "Project responsible for providing a backend for the CarView app.")
//                        .termsOfService("http://swagger.io/terms/")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
//    }
}
