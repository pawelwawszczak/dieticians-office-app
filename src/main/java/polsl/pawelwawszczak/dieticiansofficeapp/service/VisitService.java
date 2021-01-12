package polsl.pawelwawszczak.dieticiansofficeapp.service;

import org.springframework.stereotype.Service;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;

import java.util.Optional;

@Service
public interface VisitService {
    void deleteById(Long id);
    Optional<Visit> findById(Long id);
    String checkIfAlreadyExist(Visit visit);
}
