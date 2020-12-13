package polsl.pawelwawszczak.dieticiansofficeapp.service;

import org.springframework.stereotype.Service;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;

import java.time.LocalDate;

@Service
public interface EmailService {

    void sendVisitConfirmationEmail(String to, LocalDate localDate);
}
