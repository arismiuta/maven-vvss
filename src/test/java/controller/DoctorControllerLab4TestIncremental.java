package controller;

import exceptions.ConsultationException;
import model.Consultation;
import model.Patient;
import org.junit.Test;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DoctorControllerLab4TestIncremental {

    Repository repository = new Repository("FilePatientsTest.txt", "FileConsultationsTest.txt");
    DoctorController doctorController = new DoctorController(repository);

    @Test
    public void test_A() throws Exception {

        doctorController.addPatient(new Patient(
                "Aris", "1234567890123", "Home"
        ));

        assertEquals(repository.getPatientList().size(), 1);

        repository.cleanFiles();
    }

    @Test
    public void test_A_B() throws Exception {
        doctorController.addPatient(new Patient(
                "Aris", "1234567890123", "Home"
        ));

        List<String> meds = new ArrayList<>();
        meds.add("test5");
        doctorController.addConsultation("1", "1234567890123", "dead", meds, "12/12/12");

        List<Consultation> consultations = doctorController.getConsultationList();

        assertEquals(consultations.size(), 1);

        repository.cleanFiles();
    }

    @Test
    public void test_A_B_C() throws Exception {
        doctorController.addPatient(new Patient(
                "Aris", "1234567890123", "Home"
        ));

        List<String> meds = new ArrayList<>();
        meds.add("test4");
        doctorController.addConsultation("1", "1234567890123", "dead", meds, "12/12/12");

        List<Patient> patients = doctorController.getPatientsWithDisease("dead");

        assertEquals(patients.size(), 1);

        repository.cleanFiles();
    }

}
