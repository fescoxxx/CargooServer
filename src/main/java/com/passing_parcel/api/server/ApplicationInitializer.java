package com.passing_parcel.api.server;

import com.passing_parcel.api.server.config.WebConfig;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


/**
 * С этого класса начинается инициализация приложения
 */
@EnableScheduling
@EnableAsync
public class ApplicationInitializer implements WebApplicationInitializer {

    private final static String DISPATCHER = "dispatcher";

    /**
     *
     * @param servletContext
     * @throws ServletException
     */
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        ctx.register(WebConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));
        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER, new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}

