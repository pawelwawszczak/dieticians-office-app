package polsl.pawelwawszczak.dieticiansofficeapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.User;
import polsl.pawelwawszczak.dieticiansofficeapp.web.dto.UserRegistrationDto;

@Service
public interface UserService extends UserDetailsService {

}
