package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.VerificationToken;
import com.isa.restaurant.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Q on 21-Apr-17.
 */
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;


    @Autowired
    public MailServiceImpl(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }


    @Override
    public void sendUserActivationEmail(Guest guest, String token) {

        MimeMessagePreparator preparator = getMessagePreparator(guest, token);

        try
        {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        }
        catch (MailException ex)
        {
            System.err.println(ex.getMessage());
        }
    }


    private MimeMessagePreparator getMessagePreparator(Guest guest, String token) {

        String messageText = "Dear " + guest.getFirstName() + " " + guest.getLastName() + ",\n\n" +
                             "Thank you for registration at out site! To activate your account please click the following link: " +
                             "<http://localhost:8080/guest/" + guest.getId() + "/activation/" + token + ">";

        MimeMessagePreparator preparator = mimeMessage ->
        {
            mimeMessage.setFrom("restaurantsapplication@gmail.com");
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(guest.getEmail()));
            mimeMessage.setText(messageText);
            mimeMessage.setSubject("Activation email for ISA Restaurant Application");
        };

        return preparator;
    }

}
