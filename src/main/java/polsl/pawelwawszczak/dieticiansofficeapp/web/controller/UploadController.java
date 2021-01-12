package polsl.pawelwawszczak.dieticiansofficeapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import polsl.pawelwawszczak.dieticiansofficeapp.editor.CustomPatientEditor;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Diet;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Dietician;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DietStorageService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DieticianService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.PatientService;

import java.security.Principal;
import java.util.Set;

@Controller
public class UploadController {

    private DietStorageService dietStorageService;
    private PatientService patientService;
    private DieticianService dieticianService;

    public UploadController(DietStorageService dietStorageService, PatientService patientService, DieticianService dieticianService) {
        this.dietStorageService = dietStorageService;
        this.patientService = patientService;
        this.dieticianService = dieticianService;
    }

    @GetMapping("/dietician/send-diet")
    public String getFiles(Model model, Principal principal){
        Diet diet = new Diet();
        Dietician dietician = dieticianService.findByEmailAddress(principal.getName());
        Set<Patient> patients = dietician.getPatients();
        model.addAttribute("diet", diet);
        model.addAttribute("patients", patients);
        return "dieticianSendDiet";
    }

    @PostMapping("/dietician/uploadFile")
    public String uploadDietFile(@ModelAttribute("file") MultipartFile file, @ModelAttribute("diet") Diet diet){
        dietStorageService.store(file, diet.getPatient());
        return "dieticianHomePage";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Patient.class, new CustomPatientEditor());
    }
}
//trzeba w widoku przekazac id pacjenta i potem w parametrze metody store odszukac go z repozytorium
