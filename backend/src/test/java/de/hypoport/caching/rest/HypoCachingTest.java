/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hypoport.caching.rest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import de.hypoport.caching.dao.IUserDao;
import de.hypoport.caching.guice.HypoCachingAssembler;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import junit.framework.Assert;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author steffen.kaempke
 */
public class HypoCachingTest {

  static final URI BASE_URI = getBaseURI();
  HttpServer server;

  private IUserDao dao;

  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost/").port(9998).build();
  }

  @Before
  public void startServer() throws IOException {

    final Injector injector = Guice.createInjector(new HypoCachingAssembler());
    this.dao = injector.getInstance(IUserDao.class);
    
    ResourceConfig rc = new PackagesResourceConfig("de.hypoport.caching.rest");
    IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(rc, injector);
    server = GrizzlyServerFactory.createHttpServer(BASE_URI + "services/", rc, ioc);
  }

  @After
  public void tearDown() {
    this.dao.removeAllUser();
    this.server.stop();
  }

  /**
   * Test of getAll method, of class HypoCaching.
   */
  @Test
  public void testGetAll() {
    this.dao.save(new User("hzrthrth"));
    Client client = Client.create(new DefaultClientConfig());
    WebResource service = client.resource(getBaseURI());

    ClientResponse resp = service.path("services").path("users").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

    final String s = resp.getEntity(String.class);

    Assert.assertTrue(s.contains("hzrthrth"));
  }

  @Test
  public void testGet() {
    final List<ElementEnum> elements = new ArrayList<ElementEnum>();
    elements.add(ElementEnum.BAUM);
    elements.add(ElementEnum.FUNDAMENT);
    
    final User peter = new User("hzrthrth");
    peter.setCoins(245);
    peter.setElemente(elements);
    this.dao.save(peter);
    Client client = Client.create(new DefaultClientConfig());
    WebResource service = client.resource(getBaseURI());

    ClientResponse resp = service.path("services").path("users").path("hzrthrth").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

    final String s = resp.getEntity(String.class);
    System.out.println(s);
    Assert.assertEquals("{\"imei\":\"hzrthrth\",\"coins\":245,\"elemente\":[\"BAUM\",\"FUNDAMENT\"]}", s);
  }

 
}
