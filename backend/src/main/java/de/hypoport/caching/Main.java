package de.hypoport.caching;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import de.hypoport.caching.guice.HypoCachingAssembler;

public class Main extends GuiceServletContextListener {

  
  @Override
  protected Injector getInjector() {
    final Module module = new HypoCachingAssembler();
    return Guice.createInjector(new ServletModule() {

      @Override
      protected void configureServlets() {

        ResourceConfig rc = new PackagesResourceConfig("de.hypoport.caching.rest");
        for (Class<?> resource : rc.getClasses()) {
          bind(resource);
        }
        serve("/services/*").with(GuiceContainer.class);
      }
    }, module);
  }

}
