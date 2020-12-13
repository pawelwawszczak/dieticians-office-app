package polsl.pawelwawszczak.dieticiansofficeapp.service;

import org.springframework.stereotype.Service;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;

import java.time.LocalDate;

@Service
public interface VisitService {
    void deleteById(Long id);
}
