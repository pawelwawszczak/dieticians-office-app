package polsl.pawelwawszczak.dieticiansofficeapp.model;

import polsl.pawelwawszczak.dieticiansofficeapp.model.enums.DietType;
import polsl.pawelwawszczak.dieticiansofficeapp.model.enums.Gender;
import polsl.pawelwawszczak.dieticiansofficeapp.model.enums.PsychicalActivity;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Patient extends User {

    private Double weight;

    private Double height;

    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private DietType dietType;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private PsychicalActivity psychicalActivity;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Diet> diets;

    @ManyToOne(cascade = CascadeType.ALL)
    private Dietician dietician;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Visit> visits;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String emailAddress, String password, Set<Role> roles) {
        super(firstName, lastName, emailAddress, password, roles);
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public DietType getDietType() {
        return dietType;
    }

    public void setDietType(DietType dietType) {
        this.dietType = dietType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PsychicalActivity getPsychicalActivity() {
        return psychicalActivity;
    }

    public void setPsychicalActivity(PsychicalActivity psychicalActivity) {
        this.psychicalActivity = psychicalActivity;
    }

    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }

    public Dietician getDietician() {
        return dietician;
    }

    public void setDietician(Dietician dietician) {
        this.dietician = dietician;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }


}
