package ru.t_systems.alyona.sbb.config;

import org.springframework.lang.NonNull;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING = "/";
    private static final String CONFIG_PACKAGE = ApplicationInitializer.class.getPackageName();

    public void onStartup(@NonNull ServletContext servletContext) {
        WebApplicationContext webAppContext = createWebApplicationContext(servletContext);
        DispatcherServlet dispatcherServlet = createDispatcherServlet(webAppContext);
        registerDispatcherServlet(servletContext, dispatcherServlet);
    }

    private WebApplicationContext createWebApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.setServletContext(servletContext);
        webApplicationContext.scan(CONFIG_PACKAGE);
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));
        servletContext.addListener(new RequestContextListener());
        return webApplicationContext;
    }

    private DispatcherServlet createDispatcherServlet(WebApplicationContext webAppContext) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return dispatcherServlet;
    }

    private void registerDispatcherServlet(ServletContext servletContext, DispatcherServlet dispatcherServlet) {
        ServletRegistration.Dynamic dynamic = servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcherServlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping(DISPATCHER_SERVLET_MAPPING);
    }
}
