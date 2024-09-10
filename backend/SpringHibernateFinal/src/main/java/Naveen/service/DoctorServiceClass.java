package Naveen.service;

import Naveen.dao.DAODoctor;
import Naveen.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorServiceClass implements DoctorServices {
    @Autowired
    private DAODoctor daoDoctor;



    @Override
    @Transactional
    public List<Doctor> getDoctors() {
        return  daoDoctor.getDoctors();
    }

    @Override
    @Transactional
    public void saveDoctor(Doctor doctor) {
        daoDoctor.saveDoctor(doctor);
    }

    @Override
    @Transactional
    public Doctor getDoctor(int intId) {
        return daoDoctor.getDoctor(intId);
    }

    @Override
    @Transactional
    public void deleteDoctor(int intId) {
        daoDoctor.deleteDoctor(intId);
    }



}
