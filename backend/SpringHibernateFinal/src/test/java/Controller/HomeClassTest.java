package Controller;

import Naveen.Controller.HomeClass;
import Naveen.entity.Doctor;
import Naveen.entity.Patient;
import Naveen.entity.Payment;
import Naveen.service.DoctorServices;
import Naveen.service.PatientServices;
import Naveen.service.PaymentServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HomeClassTest {

    @Mock
    private DoctorServices doctorServices;

    @Mock
    private PatientServices patientServices;

    @Mock
    private PaymentServices paymentServices;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private HomeClass homeClass;

    @Before
    public void setUp() {

    }

    @Test
    public void testHomePage() {
        String result = homeClass.homePage();
        assertEquals("home-page", result);
    }

    @Test
    public void testDoctorsPage() {
        List<Doctor> doctors = Collections.singletonList(new Doctor());
        when(doctorServices.getDoctors()).thenReturn(doctors);

        String result = homeClass.doctorsPage(model);

        verify(model).addAttribute("doctors", doctors);
        assertEquals("doctor-page", result);
    }

    @Test
    public void testPatientsPage() {
        List<Patient> patients = Collections.singletonList(new Patient());
        when(patientServices.getPatients()).thenReturn(patients);

        String result = homeClass.patientPage(model);

        verify(model).addAttribute("patients", patients);
        assertEquals("patient-page", result);
    }

    @Test
    public void testGetDoctorPatients() {
        int doctorId = 1;
        List<Patient> patients = Collections.singletonList(new Patient());
        Doctor doctor = new Doctor();

        when(patientServices.getDoctorPatients(doctorId)).thenReturn(patients);
        when(doctorServices.getDoctor(doctorId)).thenReturn(doctor);

        String result = homeClass.patientDetails(model, doctorId);

        verify(model).addAttribute("patients", patients);
        verify(model).addAttribute("doctor", doctor);
        assertEquals("specific-patient-page", result);
    }

    @Test
    public void testUpdateDoctor() {
        int doctorId = 1;
        Doctor doctor = new Doctor();

        when(doctorServices.getDoctor(doctorId)).thenReturn(doctor);

        String result = homeClass.updateDoctor(model, doctorId);

        verify(model).addAttribute("doctor", doctor);
        assertEquals("showDoctorForm", result);
    }

    @Test
    public void testSaveDoctor_Valid() {
        Doctor doctor = new Doctor();
        when(bindingResult.hasErrors()).thenReturn(false);

        String result = homeClass.saveDoctor(doctor, bindingResult);

        verify(doctorServices).saveDoctor(doctor);
        assertEquals("redirect:/doctors", result);
    }

    @Test
    public void testSaveDoctor_Invalid() {
        Doctor doctor = new Doctor();
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = homeClass.saveDoctor(doctor, bindingResult);

        assertEquals("showDoctorForm", result);
        verify(doctorServices, never()).saveDoctor(any());
    }

    @Test
    public void testSavingDoctor() {
        Doctor doctor = new Doctor();

        String result = homeClass.savingDoctor(model);

        verify(model).addAttribute("doctor", doctor);
        assertEquals("showDoctorForm", result);
    }

    @Test
    public void testDeleteDoctor() {
        int doctorId = 1;
        doNothing().when(doctorServices).deleteDoctor(doctorId);

        String result = homeClass.deleteDoctor(doctorId);

        verify(doctorServices).deleteDoctor(doctorId);
        assertEquals("redirect:/doctors", result);
    }

    @Test
    public void testShowPatientForm() {
        List<Doctor> doctors = Collections.singletonList(new Doctor());
        when(doctorServices.getDoctors()).thenReturn(doctors);

        String result = homeClass.showPatientForm(model);

        verify(model).addAttribute("doctors", doctors);
        verify(model).addAttribute("patient", new Patient());
        assertEquals("showPatientForm", result);
    }

    @Test
    public void testShowPatientUpdateForm() {
        int doctorId = 1;
        Doctor doctor = new Doctor();

        when(doctorServices.getDoctor(doctorId)).thenReturn(doctor);

        String result = homeClass.showPatientUpdateForm(model, doctorId);

        verify(model).addAttribute("doctor", doctor);
        verify(model).addAttribute("patient", new Patient());
        assertEquals("showPatientUpdateForm", result);
    }

    @Test
    public void testAddPatient_Success() {
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        doctor.setId(1);
        patient.setDoctor(doctor);

        when(bindingResult.hasErrors()).thenReturn(false);

        when(doctorServices.getDoctor(1)).thenReturn(doctor);

        String view =homeClass.addPatient(patient, bindingResult, model);


        verify(patientServices).savePatient(patient);

        assertEquals("redirect:/patients", view);
    }


    @Test
    public void testAddSpecificPatient() {
        int doctorId = 1;
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        when(doctorServices.getDoctor(doctorId)).thenReturn(doctor);

        String result = homeClass.addSpecificPatient(patient, doctorId, model);

        verify(patientServices).savePatient(patient);
        assertEquals("redirect:/patients", result);
    }

    @Test
    public void testDeletePatient() {
        int patientId = 1;
        doNothing().when(patientServices).deletePatient(patientId);

        String result = homeClass.deletePatient(patientId);

        verify(patientServices).deletePatient(patientId);
        assertEquals("redirect:/patients", result);
    }

    @Test
    public void testGetPayments() {
        List<Payment> payments = Collections.singletonList(new Payment());
        when(paymentServices.getPayments()).thenReturn(payments);

        String result = homeClass.getPayments(model);

        verify(model).addAttribute("payments", payments);
        assertEquals("payment-page", result);
    }

    @Test
    public void testAddPayment() {
        List<Patient> patients = Collections.singletonList(new Patient());
        when(patientServices.getPatients()).thenReturn(patients);

        String result = homeClass.addPayment(model);

        verify(model).addAttribute("patients", patients);
        verify(model).addAttribute("payment", new Payment());
        assertEquals("showPaymentForm", result);
    }

    @Test
    public void testShowPatientPayment() {
        int patientId = 1;
        Patient patient = new Patient();

        when(patientServices.getPatient(patientId)).thenReturn(patient);

        String result = homeClass.showPatientPayment(model, patientId);

        verify(model).addAttribute("patient", patient);
        verify(model).addAttribute("payment", new Payment());
        assertEquals("showPaymentUpdateForm", result);
    }

    @Test
    public void testUpdatePayment() {
        int paymentId = 1;
        Payment payment = new Payment();
        List<Patient> patients = Collections.singletonList(new Patient());

        when(paymentServices.getPayment(paymentId)).thenReturn(payment);
        when(patientServices.getPatients()).thenReturn(patients);

        String result = homeClass.updatePayment(model, paymentId);

        verify(model).addAttribute("payment", payment);
        verify(model).addAttribute("patients", patients);
        assertEquals("showPaymentForm", result);
    }

    @Test
    public void testSavePayment_Valid() {
        Payment payment = new Payment();
        when(bindingResult.hasErrors()).thenReturn(false);

        String result = homeClass.savePayment(payment, bindingResult, model);

        verify(paymentServices).savePayment(payment);
        assertEquals("redirect:/payments", result);
    }

    @Test
    public void testSavePayment_Invalid() {
        Payment payment = new Payment();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(patientServices.getPatients()).thenReturn(Collections.singletonList(new Patient()));

        String result = homeClass.savePayment(payment, bindingResult, model);

        assertEquals("showPaymentForm", result);
        verify(paymentServices, never()).savePayment(payment);
    }

    @Test
    public void testSaveSpecificPayment() {
        int patientId = 1;
        Payment payment = new Payment();
        Patient patient = new Patient();
        when(patientServices.getPatient(patientId)).thenReturn(patient);

        String result = homeClass.addSpecificPayment(payment, patientId, bindingResult);

        verify(paymentServices).savePayment(payment);
        assertEquals("redirect:/payments", result);
    }

    @Test
    public void testDeletePayment() {
        int paymentId = 1;
        doNothing().when(paymentServices).deletePayment(paymentId);

        String result = homeClass.deletePayment(paymentId);

        verify(paymentServices).deletePayment(paymentId);
        assertEquals("redirect:/payments", result);
    }

    @Test
    public  void testGetPatientPayment(){
        int patientId=1;
        List<Payment> payments = Collections.singletonList(new Payment());
        Patient patient1=new Patient();

        when(paymentServices.getPaymentById(patientId)).thenReturn(payments);
        when(patientServices.getPatient(patientId)).thenReturn(patient1);

        String result=homeClass.getPatientPayment(model,patientId);

        verify(model).addAttribute("payments",payments);
        verify(model).addAttribute("patient",patient1);

        assertEquals("specific-payment-page",result);
    }
}
