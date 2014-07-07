/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hypoport.caching.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.hypoport.caching.dao.IUserDao;
import de.hypoport.caching.dao.Praemie;

/**
 * 
 * @author steffen.kaempke
 */
@Path("users")
public class HypoCaching {

	@Inject
	private IUserDao dao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User save(User user) {
		User werber = dao.read(user.getWerber());
		if (null != werber) {
			werber.setCoins(werber.getCoins() + 1000);
			dao.save(werber);
		} else {
			user.setCoins(1000);
			dao.save(user);
		}
		send(user);
		return user;
	}

	@GET
	@Path("/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("email") final String userMail) {
		return dao.read(userMail);
	}
	
	@GET
	@Path("/praemien")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Praemie> getPraemien() throws SQLException {
		return dao.getPraemien();
	}

	private void send(User user) {
		// Recipient's email ID needs to be mentioned.
		String to = user.getNeukunde();

		// Sender's email ID needs to be mentioned
		String from = user.getWerber();

		// Assuming you are sending email from localhost
		String host = "mail-luebeck.hypoport.local";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Empfehlung");

			String userComment = user.getComment() == null ? "" : user.getComment(); 
			
			// Now set the actual message
			message.setText(userComment
					+ "\n"
					+ "\n\nGuten Tag,"
					+

					"\n\nich melde mich auf Empfehlung bei Ihnen. Ein Kunde war von unserem Service so begeistert, dass er uns gerne auch Ihnen vorstellen möchte."

					+ "\n\nDr. Klein ist ein erfolgreicher unabhängiger Vertrieb von Finanz- und Versicherungslösungen in Deutschland. "

					+ "\n\nSeit Gründung des Unternehmens 1954 sind wir DIE PARTNER FÜR IHRE FINANZEN."

					+ "\n\nSie profitieren:"
					+ "\nÜber 50 Jahre Erfahrung als Finanzpartner"
					+ "\nUnabhängige Beratung durch Experten"
					+ "\nKostenfreier und unverbindlicher Service"
					+ "\nMaßgeschneiderte und günstige Angebote"

					+ "\n\nHaben Sie gezielte Fragen oder Wünsche? Möchten Sie sich selber einen Überblick verschaffen, wie Sie derzeit Versichert sind und ob vielleicht Handlungsbedarf besteht."
					+ "\nOb beim Shopping oder der Autoreparatur - die Zweite Meinung zählt! Erst recht bei Ihren Versicherungen. Holen Sie sich gerne ganz unverbindlich unsere Zweite Meinung dazu!"

					+ "\n\nDer einfachste Weg Führt über ein kurzes Telefonat. Wann kann ich Sie am Besten erreichen?  --> [Jetzt Telefon-Kontakt vereinbaren]"

					+ "\n\nIch freue mich von Ihnen zu hören." + "\n\nFreundliche Grüße");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
