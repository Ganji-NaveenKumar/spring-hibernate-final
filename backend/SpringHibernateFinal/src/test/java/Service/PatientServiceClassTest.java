package Service;

import Naveen.DAO.DAOPatient;
import Naveen.entity.Patient;
import Naveen.service.PatientServiceClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PatientServiceClassTest {

    @InjectMocks
    private PatientServiceClass patientService;

    @Mock
    private DAOPatient daoPatient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPatients() {
        List<Patient> mockPatients = new ArrayList<>();
        mockPatients.add(new Patient());
        when(daoPatient.getPatients()).thenReturn(mockPatients);

        List<Patient> patients = patientService.getPatients();

        assertNotNull(patients);
        assertEquals(1, patients.size());
        verify(daoPatient, times(1)).getPatients();
    }

    @Test
    void testGetDoctorPatients() {
        List<Patient> mockPatients = new ArrayList<>();
        mockPatients.add(new Patient());
        when(daoPatient.getDoctorPatients(anyInt())).thenReturn(mockPatients);

        List<Patient> patients = patientService.getDoctorPatients(1);

        assertNotNull(patients);
        assertEquals(1, patients.size());
        verify(daoPatient, times(1)).getDoctorPatients(1);
    }

    @Test
    void testGetPatient() {
        Patient mockPatient = new Patient();
        when(daoPatient.getPatient(anyInt())).thenReturn(mockPatient);

        Patient patient = patientService.getPatient(1);

        assertNotNull(patient);
        verify(daoPatient, times(1)).getPatient(1);
    }

    @Test
    void testSavePatient() {
        Patient patient = new Patient();
        doNothing().when(daoPatient).savePatient(patient);

        patientService.savePatient(patient);

        verify(daoPatient, times(1)).savePatient(patient);
    }

    @Test
    void testDeletePatient() {
        doNothing().when(daoPatient).deletePatient(anyInt());

        patientService.deletePatient(1);

        verify(daoPatient, times(1)).deletePatient(1);
    }
}
