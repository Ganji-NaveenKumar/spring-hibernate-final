package Naveen.service;

import Naveen.entity.Doctor;
import Naveen.entity.Patient;

import java.util.List;

public interface DoctorServices {
    public List<Doctor> getDoctors();
    public  void saveDoctor(Doctor doctor);

    public Doctor getDoctor(int intId);

    public void deleteDoctor(int intId);


}
