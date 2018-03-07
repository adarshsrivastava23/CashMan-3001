package com.adarsh.CashMan3001.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


/*This is service class for SMTP Notification to bank Admin in case to low balance or out of money of ATM */

@Service
public class EmailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /* This method will use default SMTP credentials available in 
     * application.properties
     * 
     * Currently default credentials will not work and on call of sendMail method we will get
     * Authentication failure message.
     * 
     * When correct credentials would added, It will work.
     * 
     * */
    public void sendMail(String subject, String message) {
    	LOGGER.info("Email Notification is being sent for low balance of ATM...");
    	LOGGER.info("Email Subject :" + subject);
    	LOGGER.info("Email Body :" + message);
    	
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("admin@bankAbc.com");
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("myMail@gmail.com");
        javaMailSender.send(mailMessage);
    }
}
