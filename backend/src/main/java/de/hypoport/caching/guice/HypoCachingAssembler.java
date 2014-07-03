/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hypoport.caching.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import de.hypoport.caching.dao.IUserDao;
import de.hypoport.caching.dao.UserDao;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author steffen.kaempke
 */
public class HypoCachingAssembler extends AbstractModule{

  @Override
  protected void configure() {
     bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
     bind(IUserDao.class).to(UserDao.class).asEagerSingleton();
    
  }
  
}
