package Naveen.DAO;

import Naveen.entity.Payment;

import java.util.List;

public interface DAOPayment {
    public List<Payment> getPayments();
    public  List<Payment> getPaymentById(int intId);

    public void savePayment(Payment payment);
    public Payment getPayment(int intId);

    public  void deletePayment(int intId);
}
