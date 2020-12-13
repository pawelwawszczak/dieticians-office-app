package polsl.pawelwawszczak.dieticiansofficeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;

@Repository
public interface DieticianRepository extends JpaRepository<Dietician, Long> {
    Dietician findByEmailAddress(String name);
}
