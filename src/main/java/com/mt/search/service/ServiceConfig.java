package com.mt.search.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by robertmurray on 9/27/17.
 */
@Configuration
public class ServiceConfig {

    @Bean
    public SearchService searchService() throws SearchProviderException {
        return new SearchService(simpleSearchProvider());
    }
    @Bean
    public SimpleFileSearchProviderImpl simpleSearchProvider() throws SearchProviderException {
        return new SimpleFileSearchProviderImpl("Programming_Data.txt");
    }
}
