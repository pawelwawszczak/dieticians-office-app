package polsl.pawelwawszczak.dieticiansofficeapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Role;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

//    private final PatientRepository patientRepository;
//
//    public BootstrapData(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }
//
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        patientRepository.save(getPatient());
    }
//
//    private Patient getPatient(){
//        Patient p1 = new Patient("Paweł",
//                "Wawszczak",
//                "pawel.wawszczak1@gmail.com",
//                "123",
//                new HashSet<Role>(Arrays.asList(new Role("USER"))));
//        p1.setAge(30);
////        p1.setDietician(new Dietician("Jan", "Kowalski", "janko@gmail.com", "312"));
////        p1.setGender(Gender.MEZCZYZNA);
//        return p1;
//    }
    }
