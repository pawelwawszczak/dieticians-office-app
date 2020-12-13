package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Role;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.DieticianRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.PatientRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.RoleRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.EmailService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.PatientService;
import polsl.pawelwawszczak.dieticiansofficeapp.web.dto.UserRegistrationDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DieticianRepository dieticianRepository;
    private EmailService emailService;

    List<Visit> visits;

    public PatientServiceImpl(PatientRepository patientRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, DieticianRepository dieticianRepository, EmailService emailService) {
        this.patientRepository = patientRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dieticianRepository = dieticianRepository;
        this.emailService = emailService;
    }

    @Override
    public Patient registerNewPatientAccount(UserRegistrationDto userRegistrationDto) {
//        if(!patientEmailExist(userRegistrationDto.getEmailAddress())){
//            throw new UserAlreadyExistException
//        }
        Patient patient = new Patient(userRegistrationDto.getFirstName(),
                                    userRegistrationDto.getLastName(),
                                    userRegistrationDto.getEmailAddress(),
                                    bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()),
                                    new HashSet<Role>(Arrays.asList(roleRepository.findByName("ROLE_PATIENT"))));
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
        patient.setDietician(dieticianRepository.findByEmailAddress(patientForm.getDietician().getEmailAddress()))  ;

        return patientRepository.save(patient);
    }

    @Override
    public void makeVisit(Patient patientForm, LocalDate localDate) {
        Patient patient = patientRepository.findByEmailAddress(patientForm.getEmailAddress());
        if(patientForm.getVisits() == null){
            visits = new ArrayList<>();
        } else{
            visits = patientForm.getVisits();
        }
        visits.add(new Visit(patient, dieticianRepository.findByEmailAddress(patient.getDietician().getEmailAddress()), localDate));
        patient.setVisits(visits);
        patientRepository.save(patient);
        emailService.sendVisitConfirmationEmail(patient.getEmailAddress(), localDate);
    }

//    private Dietician dieticianConverter(String dietician){
//        return dieticianRepository.findByEmailAddress(dietician);
//    }
}
