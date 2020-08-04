package polsl.pawelwawszczak.dieticiansofficeapp.services;

public interface VisitService {

    Visit findById(Long id);
    Visit save(Visit patient);
    Set<Visit> findAll();
}
