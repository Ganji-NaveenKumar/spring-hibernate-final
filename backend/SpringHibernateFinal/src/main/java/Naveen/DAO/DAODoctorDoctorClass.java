package Naveen.dao;

import Naveen.entity.Doctor;
import Naveen.entity.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAODoctorDoctorClass implements DAODoctor {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Doctor> getDoctors() {

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Query<Doctor> query = session.createQuery("from Doctor order by id", Doctor.class);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new DataAccessException("Error retrieving doctors", e) {};
        }
    }
    @Override
    public  void saveDoctor(Doctor doctor){
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(doctor);
        } catch (HibernateException e) {
            throw new DataAccessException("Error saving doctor", e) {};
        }
    }

    @Override
    public Doctor getDoctor(int intId) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Doctor doctor = session.get(Doctor.class, intId);
            if (doctor == null) {
                throw new DataAccessException("Doctor with ID " + intId + " not found") {};
            }
            return doctor;
        } catch (HibernateException e) {
            throw new DataAccessException("Error retrieving doctor with ID " + intId, e) {};
        }
    }

    @Override
    public void deleteDoctor(int intId) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Doctor doctor = session.get(Doctor.class, intId);

            if (doctor != null) {
                for (Patient patient : doctor.getPatientList()) {
                    patient.setDoctor(null);
                    session.merge(patient);
                }
                session.delete(doctor);
            } else {
                throw new DataAccessException("Doctor with ID " + intId + " not found") {};
            }
        } catch (HibernateException e) {
            throw new DataAccessException("Error deleting doctor with ID " + intId, e) {};
        }
    }



}
