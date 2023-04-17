package com.spring.mvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class SpringWebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("Create servlet context");
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(ApplicationContextConfig.class);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "SpringDispatcher", new DispatcherServlet(appContext));// controller
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}
