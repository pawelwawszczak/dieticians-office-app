package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.VisitRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.VisitService;

import java.time.LocalDate;

@Component
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
