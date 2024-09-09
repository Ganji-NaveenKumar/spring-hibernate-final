package Naveen.service;

import Naveen.entity.Patient;

import java.util.List;

public interface PatientServices {
    public List<Patient> getPatients();
    public List<Patient> getDoctorPatients(int intId);

    public Patient getPatient(int intId);

    public void savePatient(Patient patient);

    public void deletePatient(int intId);
}
