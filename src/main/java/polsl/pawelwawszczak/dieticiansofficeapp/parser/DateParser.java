package polsl.pawelwawszczak.dieticiansofficeapp.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateParser {

    SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
    Date inputDate;
    Date nineam;
    Date fivepm;

    public DateParser() {
    }

    public boolean checkVisitHours(String stringTime){

        try{
            inputDate = parser.parse(stringTime);
            nineam = parser.parse("09:00:00");
            fivepm = parser.parse("17:00:00");
        }catch (ParseException e){
            e.printStackTrace();
        }
        if(inputDate.before(nineam) || inputDate.after(fivepm)){
            return false;
        }else{
            return true;
        }
    }

}
