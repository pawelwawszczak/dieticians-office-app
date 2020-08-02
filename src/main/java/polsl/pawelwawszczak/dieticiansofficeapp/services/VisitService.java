package polsl.pawelwawszczak.dieticiansofficeapp.services;

public interface VisitService {

    Patient findById(Long id);
    Patient save(Patient patient);
    Set<Patient> findAll();
}
