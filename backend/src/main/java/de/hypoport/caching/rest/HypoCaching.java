/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hypoport.caching.rest;

import de.hypoport.caching.dao.IUserDao;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @author steffen.kaempke
 */
@Path("users")
public class HypoCaching {

  @Inject
  private IUserDao dao;

  @GET
  @Produces(APPLICATION_JSON)
  public Collection<User> getAll() {
    return dao.all();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public User save(User user) {
    dao.save(user);
    return user;
  }
  
  @GET
  @Path("delete")
  public boolean delete() {
    dao.removeAllUser();
    return true;
  }

  @GET
  @Path("{imei}")
  @Produces(MediaType.APPLICATION_JSON)
  public User read(@PathParam("imei") String imei) {
    return dao.read(imei);

  }

}
