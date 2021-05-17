package polsl.pawelwawszczak.dieticiansofficeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.User;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmailAddress(String name);
}
