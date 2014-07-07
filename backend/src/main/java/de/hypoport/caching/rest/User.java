/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hypoport.caching.rest;

/**
 * 
 * @author steffen.kaempke
 */
public class User {

	private String werber;
	private String neukunde;
	private String comment;
	private int coins;

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public String getWerber() {
		return werber;
	}

	public void setWerber(String werber) {
		this.werber = werber;
	}

	public String getNeukunde() {
		return neukunde;
	}

	public void setNeukunde(String neukunde) {
		this.neukunde = neukunde;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
