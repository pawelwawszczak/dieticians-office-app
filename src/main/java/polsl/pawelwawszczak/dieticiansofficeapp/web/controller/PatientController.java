package polsl.pawelwawszczak.dieticiansofficeapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import polsl.pawelwawszczak.dieticiansofficeapp.editor.CustomDieticianEditor;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.User;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.DieticianRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.PatientRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.repository.UserRepository;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DieticianService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.PatientService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.VisitService;
import polsl.pawelwawszczak.dieticiansofficeapp.web.dto.UserRegistrationDto;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;
    private DieticianService dieticianService;
    private VisitService visitService;

    public PatientController(PatientService patientService, DieticianService dieticianService, VisitService visitService) {
        this.patientService = patientService;
        this.dieticianService = dieticianService;
        this.visitService = visitService;
    }

    //
//
//    public String updatePatientData(Model model,
//                                    UserRegistrationDto userRegistrationDto){
//
//    }

//    @GetMapping
//    public String viewUsername(){
//       currentUserName();
//    }

    @GetMapping("/update-data")
    public String viewUpdateForm(Model model, Principal principal) {
        model.addAttribute("patient", patientService.findByEmailAddress(principal.getName()));
        model.addAttribute("dieticians", dieticianService.findAll());
        return "updatePatientData";
    }

    @PostMapping("/update-data")
    public String sendUpdateForm(@ModelAttribute Patient patientForm){
        patientService.setUserData(patientForm);
        return "patientHomePage";
    }

    @RequestMapping("/home")
    public String viewPatientHomePage(){
        return "patientHomePage";
    }

//    @RequestMapping("/username")
//    public String viewCurrentUsername(Principal principal){
//        User user = principal.getName();
//    }

    @GetMapping("/set-visit")
    public String viewVisitForm(Model model, Principal principal) {
        model.addAttribute("patient", patientService.findByEmailAddress(principal.getName()));
        model.addAttribute("visit", new Visit());
//        model.addAttribute("dieticians", dieticianService.findAll());
        return "setVisitForm";
    }

    @PostMapping("/set-visit")
    public String makeVisit(@ModelAttribute Patient patientForm, @ModelAttribute Visit visit){
        patientService.makeVisit(patientForm, visit.getVisitDate());
        return "patientHomePage";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Dietician.class, new CustomDieticianEditor());
    }

    @GetMapping("/view-visits")
    public String viewScheduledVisits(Model model, Principal principal){
        model.addAttribute("visits", patientService.findByEmailAddress(principal.getName()).getVisits());
        return "scheduledVisits";
    }

    @RequestMapping(value = "/deleteVisit/{id}", method = RequestMethod.GET)
    public String deleteScheduledVisit(@PathVariable(value = "id") Long id){
        visitService.deleteById(id);
        return "patientHomePage";
    }



}
