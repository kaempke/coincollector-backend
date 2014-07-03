/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hypoport.caching.dao;

import com.google.inject.Guice;
import de.hypoport.caching.guice.HypoCachingAssembler;
import de.hypoport.caching.rest.User;
import java.net.UnknownHostException;
import java.util.Collection;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author steffen.kaempke
 */
public class UserDaoTest {
  
  private IUserDao dao;
  
  public UserDaoTest() {
  }
  
 
  @Before
  public void setUp() {
    this.dao = Guice.createInjector(new HypoCachingAssembler()).getInstance(IUserDao.class);
  }
  
  @After
  public void tearDown() throws UnknownHostException {
    this.dao.removeAllUser();
  }
  


  /**
   * Test of all method, of class UserDao.
   */
  @Test
  public void testSave() {
    final User peter = new User("fwefewf");
    dao.save(peter);
    Assert.assertEquals("fwefewf", peter.getImei());
  }
  


  /**
   * Test of all method, of class UserDao.
   */
  @Test
  public void testAll() {
    final User peter = new User("fwefewf");
    final User uwe = new User("grehtjh");
    dao.save(peter);
    dao.save(uwe);
    Collection<User> users = dao.all();
    Assert.assertTrue(users.contains(peter));
    Assert.assertTrue(users.contains(uwe));
   
  }
  
   @Test
  public void testRead() {
    dao.save(new User("ewhjtzjh"));
    Assert.assertEquals("ewhjtzjh", dao.read("ewhjtzjh").getImei());
  }
  
}
