package polsl.pawelwawszczak.dieticiansofficeapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Dietician dietician;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;

    private String time;

    public Visit() {
    }

    public Visit(Patient patient, Dietician dietician, LocalDate visitDate, String time) {
        this.patient = patient;
        this.dietician = dietician;
        this.visitDate = visitDate;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dietician getDietician() {
        return dietician;
    }

    public void setDietician(Dietician dietician) {
        this.dietician = dietician;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visit visit = (Visit) o;

        if (visitDate != null ? !visitDate.equals(visit.visitDate) : visit.visitDate != null) return false;
        return time != null ? time.equals(visit.time) : visit.time == null;
    }

    @Override
    public int hashCode() {
        int result = visitDate != null ? visitDate.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
