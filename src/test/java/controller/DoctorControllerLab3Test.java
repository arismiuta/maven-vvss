package controller;

import exceptions.ConsultationException;
import exceptions.PatientException;
import model.Patient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorControllerLab3Test {


    Repository repository;
    DoctorController doctorController;

    @Before
    public void setUp() throws PatientException, ConsultationException, IOException {

        repository  = new Repository("FilePatientsTest.txt", "FileConsultationsTest.txt");
        doctorController = new DoctorController(repository);

        doctorController.addPatient(new Patient(
                "Test Patient",
                "1234567890123",
                "Test Street"
        ));
        List<String> meds = new ArrayList<>();
        meds.add("test");
        doctorController.addConsultation(
                "100","1234567890123", "TEST", meds, "12/12/12"
        );

    }

    @After
    public void after() {
        repository.cleanFiles();
    }

    @Test
    public void testPath1() {
        //1,2,12
        try {
            List<String> meds = null;
            doctorController.addConsultation("1", "1234567890123", "dead", meds, "12/12/12");
            assert false;
        }
        catch(ConsultationException e) {
            assert true;
        } catch (IOException e) {
            assert false;
        }

    }

    @Test
    public void testPath2() {
        //1,3,11,12
        try {
            List<String> meds = new ArrayList<>();
            doctorController.addConsultation("", "", "", meds, "");
            assert false;
        }
        catch(ConsultationException e) {
            assert true;
        } catch (IOException e) {
            assert false;
        }

        try {
            List<String> meds = new ArrayList<>();
            meds.add("test");
            doctorController.addConsultation(null, "1234567890123", null, meds, "");
            assert false;
        }
        catch(ConsultationException e) {
            assert true;
        } catch (IOException e) {
            assert false;
        }

        try {
            List<String> meds = new ArrayList<>();
            meds.add("test");
            doctorController.addConsultation(null, "1234567890123", "test", meds, "12/12/12");
            assert false;
        }
        catch(ConsultationException e) {
            assert true;
        } catch (IOException e) {
            assert false;
        }

        try {
            List<String> meds = new ArrayList<>();
            meds.add("test");
            doctorController.addConsultation("2", "1234567890124", "test", meds, "12/12/12");
            assert false;
        }
        catch(ConsultationException e) {
            assert true;
        } catch (IOException e) {
            assert false;
        }

        try {
            List<String> meds = new ArrayList<>();
            meds.add("test");
            doctorController.addConsultation("100", "1234567890124", "test", meds, "12/12/12");
            assert false;
        }
        catch(ConsultationException e) {
            assert true;
        } catch (IOException e) {
            assert false;
        }
    }

    @Test
    public void testPath3() throws IOException {
        try {
            List<String> meds = new ArrayList<>();
            meds.add("test");
            doctorController.addConsultation("100", "1234567890123", "test", meds, "12/12/12");
            assert true;
        }
        catch(ConsultationException | IOException e) {
            assert false;
        }

    }
}
