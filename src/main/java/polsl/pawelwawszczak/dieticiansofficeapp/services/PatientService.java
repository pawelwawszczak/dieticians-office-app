package polsl.pawelwawszczak.dieticiansofficeapp.services;

public interface PatientService {

    Patient findByLastName(String lastName);
    Patient findById(Long id);
    Patient save(Patient patient);
    Set<Patient> findAll();
}
