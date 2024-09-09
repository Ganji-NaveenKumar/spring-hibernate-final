package Service;

import Naveen.DAO.DAODoctor;
import Naveen.entity.Doctor;
import Naveen.service.DoctorServiceClass;
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

public class DoctorServiceClassTest {

    @InjectMocks
    private DoctorServiceClass doctorService;

    @Mock
    private DAODoctor daoDoctor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDoctors() {
        List<Doctor> mockDoctors = new ArrayList<>();
        mockDoctors.add(new Doctor());
        when(daoDoctor.getDoctors()).thenReturn(mockDoctors);

        List<Doctor> doctors = doctorService.getDoctors();

        assertNotNull(doctors);
        assertEquals(1, doctors.size());
        verify(daoDoctor, times(1)).getDoctors();
    }

    @Test
    void testSaveDoctor() {
        Doctor doctor = new Doctor();
        doNothing().when(daoDoctor).saveDoctor(doctor);

        doctorService.saveDoctor(doctor);

        verify(daoDoctor, times(1)).saveDoctor(doctor);
    }

    @Test
    void testGetDoctor() {
        Doctor mockDoctor = new Doctor();
        when(daoDoctor.getDoctor(anyInt())).thenReturn(mockDoctor);

        Doctor doctor = doctorService.getDoctor(1);

        assertNotNull(doctor);
        verify(daoDoctor, times(1)).getDoctor(1);
    }

    @Test
    void testDeleteDoctor() {
        doNothing().when(daoDoctor).deleteDoctor(anyInt());

        doctorService.deleteDoctor(1);

        verify(daoDoctor, times(1)).deleteDoctor(1);
    }
}
