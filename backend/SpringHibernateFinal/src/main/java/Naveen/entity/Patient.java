package Naveen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="patients")

public  @Data class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Required")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Required")
    @Column(name="last_name")
    private  String lastName;

    @NotEmpty(message = "Required")
    @Column(name="details")
    private  String details;

    @NotEmpty(message = "Required")
    @Size(min = 10,message = "10 digits are required")
    @Column(name="contact")
    private  String contact;

    @NotEmpty(message = "Required")
    @Email(message = "Enter valid email")
    @Column(name="email")
    private  String email;

    @ManyToOne(fetch =FetchType.EAGER,cascade = {
          CascadeType.DETACH,
          CascadeType.MERGE,
          CascadeType.PERSIST,
          CascadeType.REFRESH
    })
    @JoinColumn(name="doctor_id",nullable = true)
    @ToString.Exclude
    private Doctor doctor;

    public  Patient(){

    }

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Payment> paymentList;

    public Patient(String  firstName,String lastName,String contact,String details,String email,Doctor doctor){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.contact=contact;
        this.details=details;
        this.doctor=doctor;
    }
    public void removePayment(Payment payment) {
        if (paymentList != null && paymentList.contains(payment)) {
            paymentList.remove(payment);
            payment.setPatient(null);
        }
    }
}
