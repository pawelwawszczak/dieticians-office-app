package polsl.pawelwawszczak.dieticiansofficeapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import polsl.pawelwawszczak.dieticiansofficeapp.exceptions.UserExistException;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.web.dto.UserRegistrationDto;

import java.time.LocalDate;

@Service
public interface PatientService{

    Patient findByEmailAddress(String email);
    Patient setUserData(Patient patient);
    Patient save(Patient patient);


    String makeVisit(Patient patientForm, LocalDate localDate, String time);
    void deleteVisit(Long id);
}
