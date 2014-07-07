/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hypoport.caching.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hypoport.caching.rest.DbConnection;
import de.hypoport.caching.rest.User;

/**
 * 
 * @author steffen.kaempke
 */
public class UserDao implements IUserDao {

	final Map<String, User> users = new HashMap<String, User>();
	private final DbConnection conn;

	public UserDao() {
		this.conn = new DbConnection("jdbc:sqlserver://S12KV077.hypoport.local", "coincollector", "A/123456789#");

		ResultSet rs = conn.execute("select * from f_premien('Daniel','Salazar Drabert','daniel.salazar@hypoport.de',200)");
		try {
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Collection<User> all() {
		return users.values();
	}

	@Override
	public User read(String werbermail) {
		return users.get(werbermail);
	}

	@Override
	public void save(User user) {
		users.put(user.getWerber(), user);
	}

	@Override
	public void delete(String imei) {
		users.remove(imei);
	}

	@Override
	public void removeAllUser() {
		users.clear();
	}

	@Override
	public List<Praemie> getPraemien() throws SQLException {
		final ResultSet execute = conn.execute("select * from Premien");
		final List<Praemie> praemien = new ArrayList<Praemie>();
		while (execute.next()) {
			String name = execute.getString(2);
			String wert = execute.getString(3);
			String text = execute.getString(5);
			String url = execute.getString(4);

			System.out.println(name);
			System.out.println(wert);
			System.out.println(text);
			System.out.println(url);
			final Praemie p = new Praemie();
			p.setCoin(wert);
			p.setName(name);
			p.setText(text);
			p.setUrl(url);
			if (null != p.getUrl()) {

				praemien.add(p);
			}
		}
		return praemien;
	}

}
