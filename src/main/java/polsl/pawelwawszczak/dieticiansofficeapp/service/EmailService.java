package polsl.pawelwawszczak.dieticiansofficeapp.service;

import org.springframework.stereotype.Service;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;

import java.time.LocalDate;

@Service
public interface EmailService {

    void sendVisitConfirmationToDietician(String to, LocalDate localDate);
    void sendVisitConfirmationToPatient(String to, LocalDate localDate);
    void sendVisitRemovalEmailToDietician(String to, LocalDate localDate);
    void sendVisitRemovalEmailToPatient(String to, LocalDate localDate);
}
