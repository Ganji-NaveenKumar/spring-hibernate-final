package Naveen.DAO;

import Naveen.entity.Patient;
import Naveen.entity.Payment;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class DAOPaymentClass implements DAOPayment{

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Payment> getPayments() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            return session.createQuery("from Payment order by id", Payment.class).list();
        } catch (HibernateException e) {
            throw new DataAccessException("Error retrieving all payments", e) {};
        }
    }

    @Override
    public List<Payment> getPaymentById(int intId){
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            String hql = "from Payment p where p.patient.id = :patientId";
            return session.createQuery(hql, Payment.class)
                    .setParameter("patientId", intId)
                    .list();
        } catch (HibernateException e) {
            throw new DataAccessException("Error retrieving payments for patient with ID " + intId, e) {};
        }
    }

    @Override
    public  void savePayment(Payment payment){
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(payment);
        } catch (HibernateException e) {
            throw new DataAccessException("Error saving payment", e) {};
        }
    }

    @Override
    public Payment getPayment(int intId){
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Payment payment = session.get(Payment.class, intId);
            if (payment == null) {
                throw new EntityNotFoundException("Payment not found with ID: " + intId);
            }
            return payment;
        } catch (HibernateException e) {
            throw new DataAccessException("Error retrieving payment with ID " + intId, e) {};
        }
    }

    @Override
    public  void deletePayment(int intId){
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Payment payment = session.get(Payment.class, intId);
            if (payment != null) {
                Patient patient = payment.getPatient();
                if (patient != null) {
                    patient.removePayment(payment);
                }
                session.delete(payment);
            } else {
                throw new EntityNotFoundException("Payment not found with ID: " + intId);
            }
        } catch (HibernateException e) {
            throw new DataAccessException("Error deleting payment with ID " + intId, e) {};
        }
    }
}
