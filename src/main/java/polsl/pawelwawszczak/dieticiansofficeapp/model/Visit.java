package polsl.pawelwawszczak.dieticiansofficeapp.model;

import java.io.Serializable;

public class Visit extends BaseEntity  {

    private Dietician dietician;
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dietician getDietician() {
        return dietician;
    }

    public void setDietician(Dietician dietician) {
        this.dietician = dietician;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
