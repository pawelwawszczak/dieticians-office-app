package polsl.pawelwawszczak.dieticiansofficeapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import polsl.pawelwawszczak.dieticiansofficeapp.editor.CustomDieticianEditor;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Visit;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DieticianService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.PatientService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.VisitService;

import java.security.Principal;

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
        return "patientUpdateData";
    }

    @PostMapping("/update-data")
    public String sendUpdateForm(@ModelAttribute Patient patientForm, RedirectAttributes redirectAttributes){
        patientService.setUserData(patientForm);
        redirectAttributes.addFlashAttribute("message", "You've successfully updated data!");
        return "redirect:/patient/success";
    }

    @RequestMapping("/home")
    public String viewPatientHomePage(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("success", "Data updated successfully");
        return "patientHomePage";
    }

    @GetMapping("/success")
    public String viewSuccessMessage(){
        return "success";
    }

    @GetMapping("/error")
    public String viewErrorMessage(){
        return "error";
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
        return "patientSetVisit";
    }

    @PostMapping("/set-visit")
    public String makeVisit(@ModelAttribute Patient patientForm, @ModelAttribute Visit visit, RedirectAttributes redirectAttributes){
        switch(patientService.makeVisit(patientForm, visit.getVisitDate(), visit.getTime())){
            case "Closing hours":
                redirectAttributes.addFlashAttribute("message", "Please schedule your visit between 9:00-17:00");
                return "redirect:/patient/error";
            case "Same day&hour":
                redirectAttributes.addFlashAttribute("message", "There is a visit scheduled on that day and time");
                return "redirect:/patient/error";
            default:
                redirectAttributes.addFlashAttribute("message", "You've successfully scheduled a visit!");
                return "redirect:/patient/success";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Dietician.class, new CustomDieticianEditor());
    }

    @GetMapping("/view-visits")
    public String viewScheduledVisits(Model model, Principal principal){
        model.addAttribute("visits", patientService.findByEmailAddress(principal.getName()).getVisits());
        return "patientScheduledVisits";
    }

    @RequestMapping(value = "/deleteVisit/{id}", method = RequestMethod.GET)
    public String deleteScheduledVisit(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes){
        patientService.deleteVisit(id);
        redirectAttributes.addFlashAttribute("message", "Your visit has been successfully deleted");

        return "redirect:/patient/success";
    }



}
