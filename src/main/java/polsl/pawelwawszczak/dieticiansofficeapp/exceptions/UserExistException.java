package polsl.pawelwawszczak.dieticiansofficeapp.exceptions;

public class UserExistException extends Exception {

    public UserExistException(String errorMessage){
        super(errorMessage);
    }
}
