package polsl.pawelwawszczak.dieticiansofficeapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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


}
