package com.mt.search.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by robertmurray on 9/27/17.
 */

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Autowired
    private CrossSiteScriptingInterceptor crossSiteScriptingInterceptor;

    @Autowired
    private CORSInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor);
        registry.addInterceptor(corsInterceptor);
        registry.addInterceptor(crossSiteScriptingInterceptor);
    }
}