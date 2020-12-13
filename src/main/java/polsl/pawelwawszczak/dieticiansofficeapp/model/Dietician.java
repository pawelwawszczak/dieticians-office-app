package polsl.pawelwawszczak.dieticiansofficeapp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("2")
public class Dietician extends User {

    @OneToMany(mappedBy = "dietician", cascade = CascadeType.ALL)
    private Set<Patient> patients;

    @OneToMany(mappedBy = "dietician", cascade = CascadeType.ALL)
    private Set<Visit> visits;

    public Dietician() {
    }

    public Dietician(String firstName, String lastName, String emailAddress, String password, Set<Role> roles) {
        super(firstName, lastName, emailAddress, password, roles);
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
