package polsl.pawelwawszczak.dieticiansofficeapp.editor;

import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;

import java.beans.PropertyEditorSupport;

public class CustomPatientEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Patient patient = (Patient) getValue();
        return patient == null ? "" : patient.getEmailAddress();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Patient patient  = new Patient();
        patient.setEmailAddress(text);
        setValue(patient);
    }

}
