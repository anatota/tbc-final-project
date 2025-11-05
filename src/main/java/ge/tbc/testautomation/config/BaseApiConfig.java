package ge.tbc.testautomation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApiConfig {

    protected RequestSpecification spec;

    public BaseApiConfig(String baseUri) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        RestAssured.config = RestAssured.config()
                .objectMapperConfig(RestAssured.config()
                        .getObjectMapperConfig()
                        .defaultObjectMapperType(ObjectMapperType.JACKSON_2)
                        .jackson2ObjectMapperFactory((cls, charset) -> mapper));

        this.spec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .build();
    }
}
