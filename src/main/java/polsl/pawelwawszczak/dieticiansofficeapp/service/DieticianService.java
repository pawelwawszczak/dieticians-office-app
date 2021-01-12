package polsl.pawelwawszczak.dieticiansofficeapp.service;

import org.springframework.stereotype.Service;
import polsl.pawelwawszczak.dieticiansofficeapp.exceptions.UserExistException;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.web.dto.UserRegistrationDto;

import java.util.List;

@Service
public interface DieticianService {

    Dietician save(Dietician dietician);
    List<Dietician> findAll();

    Dietician findByEmailAddress(String name);
    void deleteVisit(Long id);
}
