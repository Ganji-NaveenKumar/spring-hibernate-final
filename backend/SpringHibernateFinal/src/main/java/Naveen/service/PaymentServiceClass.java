package Naveen.service;

import Naveen.DAO.DAOPayment;
import Naveen.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceClass implements PaymentServices{
    @Autowired
    private DAOPayment daoPayment;

    @Override
    @Transactional
    public List<Payment> getPayments() {
        return  daoPayment.getPayments();
    }

    @Override
    @Transactional
    public List<Payment> getPaymentById(int intId) {
        return daoPayment.getPaymentById(intId);
    }
    @Override
    @Transactional
    public  void savePayment(Payment payment){
        daoPayment.savePayment(payment);
    }
    @Override
    @Transactional
    public Payment getPayment(int intId){
       return  daoPayment.getPayment(intId);
    }

    @Override
    @Transactional
    public  void deletePayment(int intId){
        daoPayment.deletePayment(intId);
    }
}
