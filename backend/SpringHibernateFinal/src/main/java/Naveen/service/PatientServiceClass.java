package Naveen.service;

import Naveen.dao.DAOPatient;
import Naveen.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientServiceClass implements PatientServices{
    @Autowired
    private DAOPatient daoPatient;

    @Override
    @Transactional
    public List<Patient> getPatients() {
        return daoPatient.getPatients();
    }
    @Override
    @Transactional
    public List<Patient> getDoctorPatients(int intId) {
        return daoPatient.getDoctorPatients(intId);
    }

    @Override
    @Transactional
    public Patient getPatient(int intId){
        return  daoPatient.getPatient(intId);
    }

    @Override
    @Transactional
    public void savePatient(Patient patient) {
        daoPatient.savePatient(patient);
    }

    @Override
    @Transactional
    public  void deletePatient(int intId){
        daoPatient.deletePatient(intId);
    }
}
