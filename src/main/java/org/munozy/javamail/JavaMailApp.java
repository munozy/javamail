package org.munozy.javamail;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class JavaMailApp {
    private static final String USERNAME = "USERNAME"; // TO CHANGE (give gmail user account)
    private static final String PASSWORD = "PASSWORD"; // TO CHANGE (read README)
    private static final String FROM = "FROM"; // TO CHANGE (give a email)
    private static final String TO = "TO1,TO2"; // TO CHANGE (give a list of emails separated by ",")
    private static final String RCPT_TO = "RCPT_TO1,RCPT_TO2"; // TO CHANGE (give a list of emails separated by ",")

    private String host = "";
    private int port = 0;
    private String username = "";
    private String password = "";

    public JavaMailApp(String host, int port, String username, String password) {

        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;

        sendMail();
    }

    private void sendMail() {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", host);
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            System.out.println("Sending email...");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO));
            message.setSubject("Mail Subject");

            String msg = "This is my first email using JavaMailer";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File("pom.xml"));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);

            Transport.send(message, InternetAddress.parse(RCPT_TO));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Send email!");
    }

    public static void main(String... args) {
        new JavaMailApp("smtp.gmail.com", 587, USERNAME, PASSWORD);
    }
}