/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author DeividnN
 */
public class SpringConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MCV.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Configuration
    @EnableWebMvc
    @ComponentScan(basePackages = {"com.dnn"})
    private static class MCV extends WebMvcConfigurerAdapter {

        @Bean
        public ViewResolver views() {
            InternalResourceViewResolver view = new InternalResourceViewResolver();
            view.setViewClass(JstlView.class);
            view.setPrefix("WEB-INF/views/");
            view.setSuffix(".jsp");

            return view;
        }

        public MCV() {
        }
    }

}
