package package1;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;

abstract class EmailHandler {

    private static final String user_name = "";//Add your email address as string;
    private static final String pass_word = ""; //Add your password as string;

    // HashMap to store sent emails
    private static HashMap < String, ArrayList< Email >> sentMails;
    // read the HashMap from sentMails.ser file
    public static void start() {
        sentMails = FileHandler.readSentMails();
    }
    // Write the sentMails HashMap to sentMails.ser file
    public static void close() {
        FileHandler.writeSentMails(sentMails);
    }
    // return the Emails sent on a given date
    public static ArrayList < Email > retrieve(String date) {
        if (sentMails.containsKey(date)) {
            return sentMails.get(date);
        } else {
            return new ArrayList < Email > ();
        }
    }
    // Add the email objects to HashMap'
    private static void addEmail(String recipients, String subject, String body) {
        Email email = new Email(recipients, subject, body); // value
        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateStamp = sdf.format(date.getTime()); // key
        if (sentMails.containsKey(dateStamp)) {
            sentMails.get(dateStamp).add(email);
        } else {
            ArrayList < Email > emails = new ArrayList < > ();
            emails.add(email);
            sentMails.put(dateStamp, emails);
        }
    }
    public static void sendMail(String recipientMail, String subject, String body) {
        //Store the properties needed to access the SMTP server
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //Sender's email username & password
        final String username = user_name;
        //final String password = "xxxYY33XX&";
        final String password = pass_word;
        //Create Session object and pass the credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = prepareMessage(session, username, recipientMail, subject, body);
        try {
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("email doesn't send successfully!");
        }
        System.out.println("Email sent successfully to " + recipientMail);
        addEmail(recipientMail, subject, body);
    }
    // prepare and return message want to send
    private static Message prepareMessage(Session session, String username, String recipient,
                                          String subject, String body) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
        } catch (Exception e) {
            System.out.println("Error in preparing message!");;
        }
        return message;
    }
}