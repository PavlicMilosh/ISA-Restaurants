package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.Reservation;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

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

        MimeMessagePreparator preparator = getActivationMessagePreparator(guest, token);

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


    @Override
    public void sendInvitationEmail(Guest fromWhom, Guest toWhom, Reservation reservation, String token)
    {
        MimeMessagePreparator preparator = getInvitationMessagePreparator(fromWhom, toWhom, reservation, token);

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


    private MimeMessagePreparator getActivationMessagePreparator(Guest guest, String token) {

        String messageText = "Dear " + guest.getFirstName() + " " + guest.getLastName() + ",\n\n" +
                "Thank you for registration at out site! To activate your account please click the following link: " +
                "<http://localhost:8080/guest/" + guest.getId() + "/activation/" + token + ">";

        return getPreparator(guest.getEmail(), messageText, "Activation email for ISA Restaurant Application");
    }


    private MimeMessagePreparator getInvitationMessagePreparator(Guest fromWhom, Guest toWhom, Reservation reservation, String token) {

        DateFormat df = new SimpleDateFormat("dd.MMM.yyyy HH:mm");
        String messageText = "Dear " + toWhom.getFirstName() + " " + toWhom.getLastName() + ",\n\n" +
                "Your friend " + fromWhom.getFirstName() + " " + fromWhom.getLastName() + " has invited you to attend for meal at:\n" +
                "Restaurant name: " + reservation.getRestaurant().getName() + "\n" +
                "Restaurant address: " + "neka adresa" + "\n" +
                "Attendance time: " + df.format(reservation.getDateTimeStart()) + "\n" +
                "If you wish to accept invitation please click on the folowing link: " +
                "<http://localhost:8080/guest/" + toWhom.getId() + "/acceptInvitation/" + token + ">";

        return getPreparator(toWhom.getEmail(), messageText, "Invitation to meal");
    }


    private MimeMessagePreparator getPreparator(String email, String messageText, String subject)
    {
        MimeMessagePreparator mmp = mimeMessage ->
        {
            mimeMessage.setFrom("restaurantsapplication@gmail.com");
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setText(messageText);
            mimeMessage.setSubject(subject);
        };
        return mmp;
    }

}
