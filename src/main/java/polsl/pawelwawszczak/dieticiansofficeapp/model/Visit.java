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

    public Visit() {
    }

    public Visit(Patient patient, Dietician dietician, LocalDate visitDate) {
        this.patient = patient;
        this.dietician = dietician;
        this.visitDate = visitDate;
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
}
