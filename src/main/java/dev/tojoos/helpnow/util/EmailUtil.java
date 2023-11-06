package dev.tojoos.helpnow.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * Class responsible for the sending email messages.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2023-01-16
 */
@Slf4j
public class EmailUtil {
  private final String username;
  private final String password;
  private final Properties properties;
  private Session session;

  public EmailUtil(String username, String password, Properties properties) {
    this.username = username;
    this.password = password;
    this.properties = properties;
  }

  public void send(String subject, String content) {
    this.send(username, subject, content);
  }

  public void send(String email, String subject, String content) {
    if (session == null) {
      session = Session.getInstance(this.properties, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
      });
      log.info("Started new TLS session.");
    } else {
      log.info("TLS session already established.");
    }
    try {
      // add extra email send to server email (that is sending the messages) with the content
      Transport.send(createMimeMessage(email, session, subject, content));
      log.info("Email message sent successfully.");
    } catch (MessagingException | UnsupportedEncodingException e) {
      log.error("Error while sending an email: {}", e.getMessage());
    }
  }

  private MimeMessage createMimeMessage(String email, Session session, String subject, String body) throws MessagingException, UnsupportedEncodingException {
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress("helpnow@no-reply.com", "HelpNow"));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
    message.setSubject(subject);
    message.setText(body);
    return message;
  }
}