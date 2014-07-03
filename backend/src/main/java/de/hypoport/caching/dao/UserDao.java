/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hypoport.caching.dao;

import de.hypoport.caching.rest.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author steffen.kaempke
 */
public class UserDao implements IUserDao {

  final Map<String,User> users = new HashMap<String, User>();

  public UserDao() {

  }

  @Override
  public Collection<User> all() {
    return users.values();
  }

  @Override
  public User read(String imei) {
    return users.get(imei);
  }

  @Override
  public void save(User user) {
    users.put(user.getImei(), user);
  }

  @Override
  public void delete(String imei) {
    users.remove(imei);
  }

  @Override
  public void removeAllUser() {
    users.clear();
  }


}
