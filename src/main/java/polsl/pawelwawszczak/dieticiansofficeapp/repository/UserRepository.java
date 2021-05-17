package polsl.pawelwawszczak.dieticiansofficeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.User;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    User findByEmailAddress(String email);
}
