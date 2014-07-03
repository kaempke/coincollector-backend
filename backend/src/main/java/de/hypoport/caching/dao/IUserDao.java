/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hypoport.caching.dao;

import de.hypoport.caching.rest.User;
import java.util.Collection;

/**
 *
 * @author steffen.kaempke
 */
public interface IUserDao {

  Collection<User> all();

  void save(User user);

  void delete(String imei);
  
  User read(String imei);


  void removeAllUser();
  
}
