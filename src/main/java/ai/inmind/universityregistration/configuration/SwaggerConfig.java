package ai.inmind.universityregistration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    public static final String Class_Tag = "Class Controller";
    public static final String Course_Tag = "Course Controller";
    public static final String Instructor_Tag = "Instructor Controller";
    public static final String Student_Tag = "Student Controller";

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ai.inmind.universityregistration"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .tags(
                        new Tag(Class_Tag, "Perform CRUD operations on Class"),
                        new Tag(Course_Tag, "Perform CRUD operations on Course"),
                        new Tag(Instructor_Tag, "Perform CRUD operations on Instructor"),
                        new Tag(Student_Tag, "Perform CRUD operations on Student as well as allow Student registration")
                );
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("Spring Boot REST API for University Registration.<br />Created by Ahmad Obeid.")
                .version("1.0.0")
                .contact(new Contact("Ahmad Obeid", "https://www.linkedin.com/in/ahmad-obeid-487a8a204/", "obeid.ahmad2001@outlook.com"))
                .build();
    }
}
