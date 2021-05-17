package polsl.pawelwawszczak.dieticiansofficeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
