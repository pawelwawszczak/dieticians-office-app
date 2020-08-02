package polsl.pawelwawszczak.dieticiansofficeapp.services;

public interface DieticianService {

    Dietician findByLastName(String lastName);
    Dietician findById(Long id);
    Dietician save(Dietician patient);
    Set<Dietician> findAll();
}
