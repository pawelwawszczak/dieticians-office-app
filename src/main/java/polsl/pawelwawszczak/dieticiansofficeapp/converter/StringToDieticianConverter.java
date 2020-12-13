//package polsl.pawelwawszczak.dieticiansofficeapp.converter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
//import polsl.pawelwawszczak.dieticiansofficeapp.repository.DieticianRepository;
//
//@Component
//public class StringToDieticianConverter implements Converter<String, Dietician> {
//
//    @Autowired
//    private DieticianRepository dieticianRepository;
//
//    @Override
//    public Dietician convert(String s) {
//        return dieticianRepository.findByEmailAddress(s);
//    }
//}
