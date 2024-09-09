package Naveen.DAO;

import Naveen.entity.Doctor;
import Naveen.entity.Patient;

import java.util.List;

public interface DAODoctor {

    public List<Doctor> getDoctors();

    public  void saveDoctor(Doctor doctor);

    public  Doctor getDoctor(int intId);

    public void deleteDoctor(int intId);


}
