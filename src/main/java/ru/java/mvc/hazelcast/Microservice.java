package ru.java.mvc.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.sun.mail.smtp.SMTPTransport;
import ru.java.mvc.bean.News;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Microservice {

    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final String EMAIL_FROM = "email_from";
    private static final String EMAIL_TO = "email_to";

    public static void main(String[] args) {

        ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, null);
        session.setDebug(true);
        Message msg = new MimeMessage(session);

        String EMAIL_SUBJECT = "";
        String EMAIL_TEXT = "";

        try {
            News news;
            while (true) {
                IQueue<News> queueNews = client.getQueue("news");
                while (queueNews.peek() != null) {
                    news = queueNews.poll();
                    EMAIL_SUBJECT =  news.getNewsHeadline() + ", " + EMAIL_SUBJECT;
                    EMAIL_TEXT = news.getNewsMain() + " Author - " + news.getNewsAuthor() + ", " + EMAIL_TEXT;
                }
                if (!EMAIL_SUBJECT.equals("")) {
                    try {
                        System.out.println("start sending");
                        // from
                        msg.setFrom(new InternetAddress(EMAIL_FROM));
                        // to
                        msg.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(EMAIL_TO, false));
                        // subject
                        msg.setSubject(EMAIL_SUBJECT);
                        // content
                        msg.setText(EMAIL_TEXT);
                        msg.setSentDate(new Date());
                        System.out.println("filling");
                        // Get SMTPTransport
                        SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
                        System.out.println("Get SMTPTransport");
                        // connect
                        t.connect(SMTP_SERVER, USERNAME, PASSWORD);
                        System.out.println("connected");
                        // send
                        t.sendMessage(msg, msg.getAllRecipients());
                        t.close();
                        System.out.println("sended");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                EMAIL_SUBJECT = "";
                EMAIL_TEXT = "";
                System.out.println("sleep");
                Thread.sleep(60 * 5000);
            }
        } catch (Exception e) {
            client.shutdown();
        }
    }
}
