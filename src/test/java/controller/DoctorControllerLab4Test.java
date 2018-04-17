

package controller;

        import exceptions.ConsultationException;
        import exceptions.PatientException;
        import model.Patient;
        import org.junit.Assert.*;
        import org.junit.Test;
        import repository.Repository;

        import java.util.ArrayList;
        import java.util.List;

        import static org.junit.Assert.*;

public class DoctorControllerLab4Test {

    Repository repository = new Repository("FilePatientsTest.txt", "FileConsultationsTest.txt");
    DoctorController doctorController = new DoctorController(repository);

    @Test
    public void addPatientTest() throws Exception {
        try{
            doctorController.addPatient(new Patient(
                    "Aris", "1234567890123", "Home"
            ));
        }
        catch (PatientException e) {
            assert false;
        }

        int patient_index = doctorController.getPatientBySSN("1234567890123");
        assertEquals(0, patient_index);

        repository.cleanFiles();
    }

    @Test
    public void addConsultationTest() throws Exception {

        doctorController.addPatient(new Patient(
                "Aris", "1234567890123", "Home"
        ));

        try {
            List<String> meds = new ArrayList<>();
            meds.add("test");
            doctorController.addConsultation("1", "1234567890123", "dead", meds, "12/12/12");
        }
        catch(ConsultationException e) {
            assert false;
        }

        assertEquals(repository.getConsultationList().size(), 1);

        repository.cleanFiles();


    }

    @Test
    public void bigBangTest() throws Exception {
        doctorController.addPatient(new Patient(
                "Aris", "1234567890123", "Home"
        ));

        List<String> meds = new ArrayList<>();
        meds.add("test");
        doctorController.addConsultation("1", "1234567890123", "dead", meds, "12/12/12");

        List<Patient> patients = doctorController.getPatientsWithDisease("dead");

        assertEquals(patients.size(), 1);

        repository.cleanFiles();

    }

}

