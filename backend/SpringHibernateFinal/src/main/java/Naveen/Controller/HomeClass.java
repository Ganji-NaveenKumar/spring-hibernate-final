package Naveen.Controller;

import Naveen.entity.Doctor;
import Naveen.entity.Patient;
import Naveen.entity.Payment;
import Naveen.service.DoctorServices;
import Naveen.service.PatientServices;
import Naveen.service.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.Doc;
import javax.validation.Valid;
import  java.util.*;

@Controller
public class HomeClass {
    @Autowired
    private DoctorServices doctorServices;

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private PaymentServices paymentServices;

    @GetMapping("/")
    public String homePage(){
        return "home-page";
    }
    @GetMapping("/doctors")
    public String doctorsPage(Model model){
        List<Doctor> doctorList=doctorServices.getDoctors();
        model.addAttribute("doctors",doctorList);
        return "doctor-page";
    }

    @GetMapping("/patients")
    public  String patientPage(Model model){
        List<Patient> patients=patientServices.getPatients();
        model.addAttribute("patients",patients);
        return  "patient-page";
    }

    @GetMapping("/getDoctorPatients")
    public String patientDetails(Model model, @RequestParam("doctorId") int intId){
        List<Patient> patients=patientServices.getDoctorPatients(intId);
        Doctor doctor=doctorServices.getDoctor(intId);
        model.addAttribute("patients",patients);
        model.addAttribute("doctor",doctor);
        return "specific-patient-page";
    }


    @GetMapping("/showDoctorDetail")
    public  String updateDoctor(Model model,@RequestParam("doctorId") int intId){
        Doctor doctor=doctorServices.getDoctor(intId);
        model.addAttribute("doctor",doctor);
        return "showDoctorForm";
    }

    @PostMapping("/saveDoctor")
    public  String saveDoctor( @Valid @ModelAttribute("doctor") Doctor doctor, BindingResult result){
        if(result.hasErrors()){
            return "showDoctorForm";
        }
        doctorServices.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/showDoctorForm")
    public  String savingDoctor(Model model){
        Doctor doctor=new Doctor();
        model.addAttribute("doctor",doctor);
        return  "showDoctorForm";
    }


    @GetMapping("/deleteDoctor")
    public  String deleteDoctor(@RequestParam("doctorId") int intId){
        doctorServices.deleteDoctor(intId);
        return "redirect:/doctors";
    }
    @GetMapping("/showPatientForm")
    public String showPatientForm(Model model){
        List<Doctor> doctors = doctorServices.getDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("patient",new Patient());
        return "showPatientForm";
    }
    @GetMapping("/showPatientUpdateForm")
    public String showPatientUpdateForm(Model model,@RequestParam("doctorId") int intId){
        Doctor doctor=doctorServices.getDoctor(intId);
        model.addAttribute("doctor",doctor);
        model.addAttribute("patient",new Patient());
        return "showPatientUpdateForm";
    }

    @PostMapping("/savePatient")
    public String addPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult result,Model model){
        if (patient.getDoctor() == null || result.hasErrors()) {
            return "showPatientForm";
        }
        int intId = patient.getDoctor().getId();
        Doctor doctor = doctorServices.getDoctor(intId);
        patient.setDoctor(doctor);
        patientServices.savePatient(patient);
        return  "redirect:/patients";
    }

    @PostMapping("/saveSpecificPatient")
    public String addSpecificPatient(@Valid @ModelAttribute("patient") Patient patient,@RequestParam("doctorId")int intId, Model model){
        Doctor doctor = doctorServices.getDoctor(intId);
        patient.setDoctor(doctor);
        patientServices.savePatient(patient);
        return  "redirect:/patients";
    }
    @GetMapping("/deletePatient")
    public  String deletePatient(@RequestParam("patientId") int intId){
        patientServices.deletePatient(intId);
        return "redirect:/patients";
    }

    @GetMapping("/payments")
    public String getPayments(Model  model){
        List<Payment> payments=paymentServices.getPayments();
        model.addAttribute("payments",payments);
        return  "payment-page";
    }



    @GetMapping("/showPaymentForm")
    public String  addPayment(Model model){
        List<Patient> patients=patientServices.getPatients();
        model.addAttribute("patients",patients);
        model.addAttribute("payment",new Payment());
        return  "showPaymentForm";
    }

    @GetMapping("/showPatientPaymentForm")
    public  String showPatientPayment(Model model,@RequestParam("patientId") int intId){
        Patient patient=patientServices.getPatient(intId);
        model.addAttribute("patient",patient);
        model.addAttribute("payment",new Payment());
        return "showPaymentUpdateForm";
    }
    @GetMapping("/showPaymentDetail")
    public  String updatePayment(Model model,@RequestParam("paymentId") int intId){
        Payment payment=paymentServices.getPayment(intId);
        model.addAttribute("payment",payment);
        List<Patient> patients=patientServices.getPatients();
        model.addAttribute("patients",patients);
        return "showPaymentForm";
    }


    @PostMapping("/savePayment")
    public  String savePayment(@Valid @ModelAttribute("payment") Payment payment, BindingResult result,Model model){
        if(result.hasErrors()){
            List<Patient> patients = patientServices.getPatients();
            model.addAttribute("patients", patients);
            return "showPaymentForm";
        }
        paymentServices.savePayment(payment);
        return  "redirect:/payments";
    }

    @PostMapping("/updatePayment")
    public  String updatePayment(@Valid @ModelAttribute("payment") Payment payment, BindingResult result){
        if(result.hasErrors()){
            return "showPaymentUpdateForm";
        }
        paymentServices.savePayment(payment);
        return "redirect:/payments";
    }

    @GetMapping("/deletePayment")
    public  String deletePayment(@RequestParam("paymentId") int intId){
        paymentServices.deletePayment(intId);
        return  "redirect:/payments";
    }




    @GetMapping("/getPatientPayment")
    public  String getPatientPayment(Model model,@RequestParam("patientId") int intId){
        List<Payment> payments=paymentServices.getPaymentById(intId);
        Patient patient=patientServices.getPatient(intId);
        model.addAttribute("payments",payments);
        model.addAttribute("patient",patient);
        return  "specific-payment-page";
    }

    @PostMapping("/saveSpecificPayment")
    public String addSpecificPayment(@Valid @ModelAttribute("payment") Payment payment,@RequestParam("patient.id") int intId, BindingResult result){
        if(result.hasErrors()){
            return "showPaymentUpdateForm";
        }
        Patient patient=patientServices.getPatient(intId);
        payment.setPatient(patient);
        paymentServices.savePayment(payment);
        return  "redirect:/payments";
    }

    @GetMapping("/contact")
    public  String getContact(){
        return "contact";
    }



    @GetMapping("/showPatientDetail")
    public String getPatient(Model model,@RequestParam("patientId") int intId){
        Patient patient=patientServices.getPatient(intId);
        model.addAttribute("patient",patient);
        List<Doctor> doctors = doctorServices.getDoctors();
        model.addAttribute("doctors", doctors);
        return  "showPatientForm";
    }
}
