package Service;

import Naveen.dao.DAOPatient;
import Naveen.entity.Patient;
import Naveen.service.PatientServiceClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceClassTest {

    @InjectMocks
    private PatientServiceClass patientService;

    @Mock
    private DAOPatient daoPatient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPatients() {
        List<Patient> mockPatients = new ArrayList<>();
        mockPatients.add(new Patient());
        when(daoPatient.getPatients()).thenReturn(mockPatients);

        List<Patient> patients = patientService.getPatients();

        assertNotNull(patients);
        assertEquals(1, patients.size());
        verify(daoPatient, times(1)).getPatients();
    }

    @Test
    public void testGetDoctorPatients() {
        List<Patient> mockPatients = new ArrayList<>();
        mockPatients.add(new Patient());
        when(daoPatient.getDoctorPatients(anyInt())).thenReturn(mockPatients);

        List<Patient> patients = patientService.getDoctorPatients(1);

        assertNotNull(patients);
        assertEquals(1, patients.size());
        verify(daoPatient, times(1)).getDoctorPatients(1);
    }

    @Test
    public void testGetPatient() {
        Patient mockPatient = new Patient();
        when(daoPatient.getPatient(anyInt())).thenReturn(mockPatient);

        Patient patient = patientService.getPatient(1);

        assertNotNull(patient);
        verify(daoPatient, times(1)).getPatient(1);
    }

    @Test
    public void testSavePatient() {
        Patient patient = new Patient();
        doNothing().when(daoPatient).savePatient(patient);

        patientService.savePatient(patient);

        verify(daoPatient, times(1)).savePatient(patient);
    }

    @Test
     public void testDeletePatient() {
        doNothing().when(daoPatient).deletePatient(anyInt());

        patientService.deletePatient(1);

        verify(daoPatient, times(1)).deletePatient(1);
    }
}
