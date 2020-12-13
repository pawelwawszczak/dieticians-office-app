package polsl.pawelwawszczak.dieticiansofficeapp.editor;

import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;

import java.beans.PropertyEditorSupport;

public class CustomDieticianEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Dietician dietician = (Dietician) getValue();
        return dietician == null ? "" : dietician.getEmailAddress();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Dietician dietician  = new Dietician();
        dietician.setEmailAddress(text);
        setValue(dietician);
    }
}
