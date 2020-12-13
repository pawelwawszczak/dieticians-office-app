package polsl.pawelwawszczak.dieticiansofficeapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Diet;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface DietStorageService {

    Diet store(MultipartFile file, Patient patient);
    Optional<Diet> getDiet(Long id);
    List<Diet> getAllDiets();

}
