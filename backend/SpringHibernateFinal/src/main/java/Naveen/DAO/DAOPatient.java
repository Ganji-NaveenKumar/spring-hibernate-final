package Naveen.DAO;

import Naveen.entity.Patient;

import java.util.List;

public interface DAOPatient {
    public List<Patient> getDoctorPatients(int intId);
    public List<Patient> getPatients();
    public Patient getPatient(int intId);
    public void savePatient(Patient patient);

    public  void deletePatient(int intId);
}
