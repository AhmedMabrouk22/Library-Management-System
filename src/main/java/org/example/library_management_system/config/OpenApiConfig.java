package org.example.library_management_system.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Ahmed Mabrouk",
                        email = "said66913@gmail.com"
                ),
                description = "Library Management System RESTful API ",
                title = "Library Management System API",
                version = "1.0.0"
        )
)
public class OpenApiConfig {

}
