package polsl.pawelwawszczak.dieticiansofficeapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String docType;

    @Lob
    private byte[] data;

    @ManyToOne(cascade = CascadeType.ALL)
    private Patient patient;

    public Diet() {
    }

    public Diet(String fileName, byte[] data, Patient patient, String docType) {
        this.fileName = fileName;
        this.data = data;
        this.patient = patient;
        this.docType = docType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }
}
