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
    public void sendVisitConfirmationEmail(String to, LocalDate localDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dietetykaplikacja@gmail.com");
        message.setTo(to);
        message.setSubject("Dietician visit confirmation");
        message.setText(String.format("Well done! Your visit has been scheduled on %s", localDate.toString()));
        javaMailSender.send(message);
    }
}
