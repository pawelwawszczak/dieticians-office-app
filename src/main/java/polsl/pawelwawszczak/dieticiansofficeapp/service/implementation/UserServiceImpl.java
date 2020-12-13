package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Role;
import polsl.pawelwawszczak.dieticiansofficeapp.model.User;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.PatientRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.UserRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.UserService;
import polsl.pawelwawszczak.dieticiansofficeapp.web.dto.UserRegistrationDto;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmailAddress(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmailAddress(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }
}
