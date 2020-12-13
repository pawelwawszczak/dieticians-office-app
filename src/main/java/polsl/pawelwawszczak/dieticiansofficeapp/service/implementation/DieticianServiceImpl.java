package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Role;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.DieticianRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.PatientRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.RoleRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DieticianService;
import polsl.pawelwawszczak.dieticiansofficeapp.web.dto.UserRegistrationDto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DieticianServiceImpl implements DieticianService {

    private final DieticianRepository dieticianRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public DieticianServiceImpl(DieticianRepository dieticianRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.dieticianRepository = dieticianRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public Dietician findByEmailAddress(String name) {
        return dieticianRepository.findByEmailAddress(name);
    }

    @Override
    public Dietician registerNewDieticianAccount(UserRegistrationDto userRegistrationDto) {
//        if(!patientEmailExist(userRegistrationDto.getEmailAddress())){
//            throw new UserAlreadyExistException
//        }
        Dietician dietician = new Dietician(userRegistrationDto.getFirstName(),
                userRegistrationDto.getLastName(),
                userRegistrationDto.getEmailAddress(),
                bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()),
                new HashSet<Role>(Arrays.asList(roleRepository.findByName("ROLE_DIETICIAN"))));
        return dieticianRepository.save(dietician);

    }

    @Override
    public List<Dietician> findAll() {
        return dieticianRepository.findAll();
    }
}
