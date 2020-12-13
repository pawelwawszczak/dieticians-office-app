//package polsl.pawelwawszczak.dieticiansofficeapp.formatters;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
//import polsl.pawelwawszczak.dieticiansofficeapp.repository.DieticianRepository;
//
//import java.text.ParseException;
//import java.util.Locale;
//
//@Component
//public class DieticianFormatter implements Formatter<Dietician> {
//
//        @Autowired
//        private DieticianRepository dieticianRepository;
//
//    @Override
//    public String print(Dietician dietician, Locale locale) {
//        return null;
//    }
//
//    @Override
//    public Dietician parse(String s, Locale locale) throws ParseException {
//        return dieticianRepository.findByEmailAddress(s);
//    }
//}
