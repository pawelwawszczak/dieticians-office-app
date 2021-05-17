package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.exceptions.UserExistException;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Role;
import polsl.pawelwawszczak.dieticiansofficeapp.model.User;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.PatientRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.UserRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DieticianService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.PatientService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.RoleService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.UserService;
import polsl.pawelwawszczak.dieticiansofficeapp.web.dto.UserRegistrationDto;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private DieticianService dieticianService;
    private PatientService patientService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, DieticianService dieticianService, PatientService patientService, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.dieticianService = dieticianService;
        this.patientService = patientService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User registerNewUserAccount(UserRegistrationDto userRegistrationDto) throws UserExistException {
        if(!userEmailExist(userRegistrationDto.getEmailAddress())){
            throw new UserExistException("There is an account with the same email address: " + userRegistrationDto.getEmailAddress());
        }
        if(!userRegistrationDto.isDietician()) {
            Patient patient = new Patient(userRegistrationDto.getFirstName(),
                    userRegistrationDto.getLastName(),
                    userRegistrationDto.getEmailAddress(),
                    bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()),
                    new HashSet<>(Arrays.asList(roleService.findByName("ROLE_PATIENT"))));
            return patientService.save(patient);
        }else{
            Dietician dietician = new Dietician(userRegistrationDto.getFirstName(),
                    userRegistrationDto.getLastName(),
                    userRegistrationDto.getEmailAddress(),
                    bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()),
                    new HashSet<>(Arrays.asList(roleService.findByName("ROLE_DIETICIAN"))));
            return dieticianService.save(dietician);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmailAddress(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmailAddress(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    private boolean userEmailExist(String email) {
        return userRepository.findByEmailAddress(email) == null;
    }
}
