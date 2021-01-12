package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Role;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.RoleRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.RoleService;

@Component
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
