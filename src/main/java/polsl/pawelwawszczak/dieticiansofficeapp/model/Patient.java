package polsl.pawelwawszczak.dieticiansofficeapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient extends Person {

    private DietType dietType;

}
