package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;
import polsl.pawelwawszczak.dieticiansofficeapp.service.EmailService;

import java.time.LocalDate;

@Component
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendVisitConfirmationToDietician(String to, LocalDate localDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dietetykaplikacja@gmail.com");
        message.setTo(to);
        message.setSubject("Dietician visit confirmation");
        message.setText(String.format("New visit scheduled on %s", localDate.toString()));
        javaMailSender.send(message);
    }

    @Override
    public void sendVisitConfirmationToPatient(String to, LocalDate localDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dietetykaplikacja@gmail.com");
        message.setTo(to);
        message.setSubject("Dietician visit confirmation");
        message.setText(String.format("Well done! Your visit has been scheduled on %s", localDate.toString()));
        javaMailSender.send(message);
    }

    @Override
    public void sendVisitRemovalEmailToDietician(String to, LocalDate localDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dietetykaplikacja@gmail.com");
        message.setTo(to);
        message.setSubject("Dietician visit removal");
        message.setText(String.format("New visit removal on %s", localDate.toString()));
        javaMailSender.send(message);
    }

    @Override
    public void sendVisitRemovalEmailToPatient(String to, LocalDate localDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dietetykaplikacja@gmail.com");
        message.setTo(to);
        message.setSubject("Dietician visit removal");
        message.setText(String.format("New visit removal on %s", localDate.toString()));
        javaMailSender.send(message);
    }
}
