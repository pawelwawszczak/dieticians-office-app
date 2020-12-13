package polsl.pawelwawszczak.dieticiansofficeapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String fileName;

    @Lob
    private byte[] data;

    @ManyToOne(cascade = CascadeType.ALL)
    private Patient patient;

    public Diet() {
    }

    public Diet(String fileName, byte[] data, Patient patient) {
        this.fileName = fileName;
        this.data = data;
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
