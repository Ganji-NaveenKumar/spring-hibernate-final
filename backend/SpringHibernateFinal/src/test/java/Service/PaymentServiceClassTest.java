package Service;

import Naveen.DAO.DAOPayment;
import Naveen.entity.Payment;
import Naveen.service.PaymentServiceClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PaymentServiceClassTest {

    @InjectMocks
    private PaymentServiceClass paymentService;

    @Mock
    private DAOPayment daoPayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPayments() {
        List<Payment> mockPayments = new ArrayList<>();
        mockPayments.add(new Payment());
        when(daoPayment.getPayments()).thenReturn(mockPayments);

        List<Payment> payments = paymentService.getPayments();
        assertNotNull(payments);
        assertEquals(1, payments.size());
        verify(daoPayment, times(1)).getPayments();
    }

    @Test
    void testGetPaymentById() {
        List<Payment> mockPayments = new ArrayList<>();
        mockPayments.add(new Payment());
        when(daoPayment.getPaymentById(anyInt())).thenReturn(mockPayments);

        List<Payment> payments = paymentService.getPaymentById(1);

        assertNotNull(payments);
        assertEquals(1, payments.size());
        verify(daoPayment, times(1)).getPaymentById(1);
    }

    @Test
    void testSavePayment() {
        Payment payment = new Payment();
        doNothing().when(daoPayment).savePayment(payment);

        paymentService.savePayment(payment);

        verify(daoPayment, times(1)).savePayment(payment);
    }

    @Test
    void testGetPayment() {
        Payment mockPayment = new Payment();
        when(daoPayment.getPayment(anyInt())).thenReturn(mockPayment);

        Payment payment = paymentService.getPayment(1);

        assertNotNull(payment);
        verify(daoPayment, times(1)).getPayment(1);
    }

    @Test
    void testDeletePayment() {
        doNothing().when(daoPayment).deletePayment(anyInt());

        paymentService.deletePayment(1);

        verify(daoPayment, times(1)).deletePayment(1);
    }
}
