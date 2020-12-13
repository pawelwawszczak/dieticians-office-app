package polsl.pawelwawszczak.dieticiansofficeapp.web.dto;


public class UserRegistrationDto {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private boolean dietician = false;

    public UserRegistrationDto() {
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDietician() {
        return dietician;
    }

    public void setDietician(boolean dietician) {
        this.dietician = dietician;
    }
}