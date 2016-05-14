/* 
 * Package Name: mn.odi.scc.util
 * File Name: EmailUtil.java
 * 
 * Created By: Tserentogtokh.D
 * Created Date: 2014/04/14
 * 
 * History
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * 2014/04/14 1.0.0 			Tserentogtokh.D				    Шинээр үүсгэв.
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2014 Od Innovation CO.,LTD SOFTWARE DIVSION
*/
package mn.odi.labor.util;

public class EmailUtil {
	// static String host = "smtp.gmail.com";
	// static String from = "downloaderfrom";
	// static Properties props = System.getProperties();
	//
	// private Session session;
	//
	// private LaborDAO communityDAO;
	//
	// public static void sendEmail(String email, String subject, String
	// content) {
	// props.put("mail.smtp.host", host);
	// props.put("mail.smtp.user", from);
	// props.put("mail.smtp.password", "fundamental");
	// props.put("mail.smtp.port", "587"); // 587 is the port number of yahoo
	// mail
	// props.put("mail.smtp.auth", "true");
	// props.put("mail.smtp.starttls.enable", "true");
	//
	// Session session = Session.getDefaultInstance(props, null);
	// MimeMessage message = new MimeMessage(session);
	// try {
	// message.setFrom(new InternetAddress(from));
	// message.addRecipient(Message.RecipientType.TO, new
	// InternetAddress(email));
	// message.setSubject(subject, "UTF-8");
	// message.setContent(content, "text/html; charset=UTF-8");
	// Transport transport = session.getTransport("smtp");
	// transport.connect(host, from, "fundamental");
	// transport.sendMessage(message, message.getAllRecipients());
	// transport.close();
	// } catch (AddressException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (MessagingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}