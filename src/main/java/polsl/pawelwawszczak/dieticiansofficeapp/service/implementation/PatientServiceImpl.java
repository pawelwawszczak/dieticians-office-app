package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.PatientRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.*;

import java.time.LocalDate;
import java.util.*;

@Component
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private DieticianService dieticianService;
    private EmailService emailService;
    private VisitService visitService;

    List<Visit> visits;

    public PatientServiceImpl(PatientRepository patientRepository, DieticianService dieticianService, EmailService emailService, VisitService visitService) {
        this.patientRepository = patientRepository;
        this.dieticianService = dieticianService;
        this.visitService = visitService;
        this.emailService = emailService;

    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient findByEmailAddress(String email) {
        return patientRepository.findByEmailAddress(email);
    }

    @Override
    public Patient setUserData(Patient patientForm) {
        Patient patient = patientRepository.findByEmailAddress(patientForm.getEmailAddress());
        patient.setGender(patientForm.getGender());
        patient.setPsychicalActivity(patientForm.getPsychicalActivity());
        patient.setAge(patientForm.getAge());
        patient.setHeight(patientForm.getHeight());
        patient.setWeight(patientForm.getWeight());
        patient.setDietType(patientForm.getDietType());
        patient.setDietician(dieticianService.findByEmailAddress(patientForm.getDietician().getEmailAddress()));

        return patientRepository.save(patient);
    }

    @Override
    public String makeVisit(Patient patientForm, LocalDate localDate, String time) {
        Patient patient = patientRepository.findByEmailAddress(patientForm.getEmailAddress());
        Visit visit = new Visit(patient, dieticianService.findByEmailAddress(patient.getDietician().getEmailAddress()), localDate, time);
        if (patient.getVisits() == null) {
            visits = new ArrayList<>();
        } else {
            visits = patient.getVisits();
        }
        switch(visitService.checkIfAlreadyExist(visit)){
            case "1":
                return "Closing hours";
            case "2":
                return "Same day&hour";
            default:
                visits.add(visit);
                patient.setVisits(visits);
                patientRepository.save(patient);
                emailService.sendVisitConfirmationToDietician(patient.getDietician().getEmailAddress(), localDate);
                emailService.sendVisitConfirmationToPatient(patient.getEmailAddress(), localDate);
                return "";
        }
    }

    @Override
    public void deleteVisit(Long id) {
        emailService.sendVisitRemovalEmailToDietician(visitService.findById(id).get().getDietician().getEmailAddress(), visitService.findById(id).get().getVisitDate());
        visitService.deleteById(id);
    }

}
