package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Diet;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.DietRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.PatientRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DietStorageService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DietStorageServiceImpl implements DietStorageService {

    private final DietRepository dietRepository;
    private final PatientRepository patientRepository;

    public DietStorageServiceImpl(DietRepository dietRepository, PatientRepository patientRepository) {

        this.dietRepository = dietRepository;
        this.patientRepository = patientRepository;
    }

    public Diet store(MultipartFile file, Patient patient){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            Diet dietFile = new Diet(fileName, file.getBytes(), patientRepository.findByEmailAddress(patient.getEmailAddress()), file.getContentType());
            return dietRepository.save(dietFile);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;



    }

    public Optional<Diet> getDiet(Long id){
        return dietRepository.findById(id);
    }

    public List<Diet> getAllDiets() {
        return dietRepository.findAll();
    }
}
