package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.exceptions.UserExistException;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Role;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.DieticianRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.PatientRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.RoleRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DieticianService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.EmailService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.RoleService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.VisitService;
import polsl.pawelwawszczak.dieticiansofficeapp.web.dto.UserRegistrationDto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DieticianServiceImpl implements DieticianService {

    private final DieticianRepository dieticianRepository;
    private EmailService emailService;
    private VisitService visitService;

    public DieticianServiceImpl(DieticianRepository dieticianRepository, EmailService emailService, VisitService visitService) {
        this.dieticianRepository = dieticianRepository;
        this.emailService = emailService;
        this.visitService = visitService;
    }

    @Override
    public Dietician save(Dietician dietician) {
        return dieticianRepository.save(dietician);
    }

    @Override
    public Dietician findByEmailAddress(String name) {
        return dieticianRepository.findByEmailAddress(name);
    }


    @Override
    public List<Dietician> findAll() {
        return dieticianRepository.findAll();
    }

    @Override
    public void deleteVisit(Long id) {
        emailService.sendVisitRemovalEmailToPatient(visitService.findById(id).get().getPatient().getEmailAddress(), visitService.findById(id).get().getVisitDate());
        visitService.deleteById(id);
    }

}
