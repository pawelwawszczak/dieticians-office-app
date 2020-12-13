package polsl.pawelwawszczak.dieticiansofficeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {
}
