package polsl.pawelwawszczak.dieticiansofficeapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.User;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DieticianService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.PatientService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.UserService;
import polsl.pawelwawszczak.dieticiansofficeapp.web.dto.UserRegistrationDto;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UserRegistrationController {

    private PatientService patientService;

    private DieticianService dieticianService;

    public UserRegistrationController(PatientService patientService, DieticianService dieticianService) {
        this.patientService = patientService;
        this.dieticianService = dieticianService;
    }

    @GetMapping("/registration")
    public String viewRegistrationForm(Model model){
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(UserRegistrationDto userRegistrationDto){
        if(!userRegistrationDto.isDietician()) {
            patientService.registerNewPatientAccount(userRegistrationDto);
        }else{
            dieticianService.registerNewDieticianAccount(userRegistrationDto);
        }
        return "redirect:/registration?success";
    }


//
//    }
//    @PostMapping
//    @ModelAttribute("patient")
//    public String registerPatientAccount(UserRegistrationDto userRegistrationDto){
//        patientService.save(userRegistrationDto);
//        return "redirect:/registration?success";
//    }
//
//    @PostMapping
//    @ModelAttribute("dietician")
//    public String registerDieticianAccount(UserRegistrationDto userRegistrationDto){
//        dieticianService.save(userRegistrationDto);
//        return "redirect:/registration?success";

}
