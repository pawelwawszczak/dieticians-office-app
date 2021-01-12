package polsl.pawelwawszczak.dieticiansofficeapp.service.implementation;

import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;
import polsl.pawelwawszczak.dieticiansofficeapp.parser.DateParser;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.VisitRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.VisitService;

import java.util.Optional;

@Component
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private DateParser dateParser = new DateParser();

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }

    @Override
    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    @Override
    public String checkIfAlreadyExist(Visit visit) {
        if(!dateParser.checkVisitHours(visit.getTime() + ":00")) {
            return "1";
        }
        for (Visit visit1 : visitRepository.findAll()) {
                if (visit.equals(visit1)) {
                    return "2";
                }
        }
        return "3";

    }
}
