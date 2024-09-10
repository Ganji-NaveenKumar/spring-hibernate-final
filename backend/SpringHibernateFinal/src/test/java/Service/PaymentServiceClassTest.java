package Service;

import Naveen.dao.DAOPayment;
import Naveen.entity.Payment;
import Naveen.service.PaymentServiceClass;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceClassTest {

    @InjectMocks
    private PaymentServiceClass paymentService;

    @Mock
    private DAOPayment daoPayment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPayments() {
        List<Payment> mockPayments = new ArrayList<>();
        mockPayments.add(new Payment());
        when(daoPayment.getPayments()).thenReturn(mockPayments);

        List<Payment> payments = paymentService.getPayments();
        assertNotNull(payments);
        assertEquals(1, payments.size());
        verify(daoPayment, times(1)).getPayments();
    }

    @Test
    public void testGetPaymentById() {
        List<Payment> mockPayments = new ArrayList<>();
        mockPayments.add(new Payment());
        when(daoPayment.getPaymentById(anyInt())).thenReturn(mockPayments);

        List<Payment> payments = paymentService.getPaymentById(1);

        assertNotNull(payments);
        assertEquals(1, payments.size());
        verify(daoPayment, times(1)).getPaymentById(1);
    }

    @Test
    public void testSavePayment() {
        Payment payment = new Payment();
        doNothing().when(daoPayment).savePayment(payment);

        paymentService.savePayment(payment);

        verify(daoPayment, times(1)).savePayment(payment);
    }

    @Test
     public void testGetPayment() {
        Payment mockPayment = new Payment();
        when(daoPayment.getPayment(anyInt())).thenReturn(mockPayment);

        Payment payment = paymentService.getPayment(1);

        assertNotNull(payment);
        verify(daoPayment, times(1)).getPayment(1);
    }

    @Test
    public void testDeletePayment() {
        doNothing().when(daoPayment).deletePayment(anyInt());

        paymentService.deletePayment(1);

        verify(daoPayment, times(1)).deletePayment(1);
    }
}
