package Service;

import Naveen.dao.DAODoctor;
import Naveen.entity.Doctor;
import Naveen.service.DoctorServiceClass;
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
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DoctorServiceClassTest {

    @InjectMocks
    private DoctorServiceClass doctorService;

    @Mock
    private DAODoctor daoDoctor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
   public void testGetDoctors() {
        List<Doctor> mockDoctors = new ArrayList<>();
        mockDoctors.add(new Doctor());
        mockDoctors.add(new Doctor());
        when(daoDoctor.getDoctors()).thenReturn(mockDoctors);

        List<Doctor> doctors = doctorService.getDoctors();

        assertNotNull(doctors);
        assertEquals(2, doctors.size());
        verify(daoDoctor, times(1)).getDoctors();
    }

    @Test
    public void testSaveDoctor() {
        Doctor doctor = new Doctor();
        doNothing().when(daoDoctor).saveDoctor(doctor);

        doctorService.saveDoctor(doctor);

        verify(daoDoctor, times(1)).saveDoctor(doctor);
    }

    @Test
    public void testGetDoctor() {
        Doctor mockDoctor = new Doctor();
        when(daoDoctor.getDoctor(anyInt())).thenReturn(mockDoctor);

        Doctor doctor = doctorService.getDoctor(1);

        assertNotNull(doctor);
        verify(daoDoctor, times(1)).getDoctor(1);
    }

    @Test
    public void testDeleteDoctor() {
        doNothing().when(daoDoctor).deleteDoctor(anyInt());

        doctorService.deleteDoctor(1);

        verify(daoDoctor, times(1)).deleteDoctor(1);
    }
}
