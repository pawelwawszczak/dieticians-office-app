package polsl.pawelwawszczak.dieticiansofficeapp.service;

import org.springframework.stereotype.Service;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Role;

@Service
public interface RoleService {
    Role findByName(String name);
}
