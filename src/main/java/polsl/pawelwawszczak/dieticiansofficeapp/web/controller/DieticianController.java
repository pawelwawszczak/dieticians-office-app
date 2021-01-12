package polsl.pawelwawszczak.dieticiansofficeapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DieticianService;

import javax.mail.Multipart;
import java.security.Principal;

@Controller
@RequestMapping("/dietician")
public class DieticianController {

    private DieticianService dieticianService;

    public DieticianController(DieticianService dieticianService) {
        this.dieticianService = dieticianService;
    }

    @RequestMapping("/home")
    public String viewDieticianHome(){
        return "dieticianHomePage";
    }

    @GetMapping("/view-visits")
    public String viewScheduledVisits(Model model, Principal principal){
        model.addAttribute("visits", dieticianService.findByEmailAddress(principal.getName()).getVisits());
        return "dieticianScheduledVisits";
    }

    @GetMapping("/view-patients")
    public String viewAssignedPatients(Model model, Principal principal){
        model.addAttribute("patients", dieticianService.findByEmailAddress(principal.getName()).getPatients());
        return "dieticianAssignedPatients";
    }

    @RequestMapping(value = "/deleteVisit/{id}", method = RequestMethod.GET)
    public String deleteScheduledVisit(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes){
        dieticianService.deleteVisit(id);
        redirectAttributes.addFlashAttribute("message", "Your visit has been successfully deleted");
        return "redirect:/patient/success";
    }
}
