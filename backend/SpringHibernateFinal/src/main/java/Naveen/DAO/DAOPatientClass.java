package Naveen.dao;

import Naveen.entity.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOPatientClass implements DAOPatient{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Patient> getDoctorPatients(int intId) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            String hql = "from Patient p where p.doctor.id = :doctorId order by p.id";
            return session.createQuery(hql, Patient.class)
                    .setParameter("doctorId", intId)
                    .list();
        } catch (HibernateException e) {
            throw new DataAccessException("Error retrieving patients for doctor with ID " + intId, e) {};
        }
    }
    @Override
    public List<Patient> getPatients() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            return session.createQuery("from Patient order by id", Patient.class).list();
        } catch (HibernateException e) {
            throw new DataAccessException("Error retrieving all patients", e) {};
        }
    }

    @Override
    public Patient getPatient(int intId) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Patient patient = session.get(Patient.class, intId);
            if (patient == null) {
                throw new DataAccessException("Patient with ID " + intId + " not found") {};
            }
            return patient;
        } catch (HibernateException e) {
            throw new DataAccessException("Error retrieving patient with ID " + intId, e) {};
        }
    }
    @Override
    public  void savePatient(Patient patient){
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(patient);
        } catch (HibernateException e) {

            throw new DataAccessException("Error saving patient", e) {};
        }
    }

    @Override
    public void deletePatient(int intId) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Patient patient = session.get(Patient.class, intId);
            if (patient == null) {
                throw new DataAccessException("Patient with ID " + intId + " not found") {};
            }
            session.delete(patient);
        } catch (HibernateException e) {
            throw new DataAccessException("Error deleting patient with ID " + intId, e) {};
        }
    }
}
