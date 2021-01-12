package polsl.pawelwawszczak.dieticiansofficeapp.web.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Diet;
import polsl.pawelwawszczak.dieticiansofficeapp.model.Patient;
import polsl.pawelwawszczak.dieticiansofficeapp.service.DietStorageService;
import polsl.pawelwawszczak.dieticiansofficeapp.service.PatientService;

import java.security.Principal;

@Controller
public class DownloadController {

    private final DietStorageService dietStorageService;
    private final PatientService patientService;

    public DownloadController(DietStorageService dietStorageService, PatientService patientService) {
        this.dietStorageService = dietStorageService;
        this.patientService = patientService;
    }

    @GetMapping("/patient/get-diet")
    public String viewDiets(Model model, Principal principal){
        Patient patient = patientService.findByEmailAddress(principal.getName());
        model.addAttribute("diets", patient.getDiets());
        return "patientDownloadDiet";
    }

    @GetMapping("/patient/download-diet/{dietId}")
    public ResponseEntity<ByteArrayResource> downloadDiet(@PathVariable Long dietId){
        Diet diet = dietStorageService.getDiet(dietId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(diet.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+diet.getFileName())
                .body(new ByteArrayResource(diet.getData()));
    }
}
