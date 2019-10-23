package com.example.cli.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwei
 * @title: JacksonConfig
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 09:35
 */
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper=new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.addMixIn(Object.class, IgnoreHibernatePropertiesInJackson.class);
        return mapper;
    }
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private abstract static class IgnoreHibernatePropertiesInJackson{ }
}


