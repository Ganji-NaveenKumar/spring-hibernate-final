package Naveen.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="doctor")
public  @Data class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @NotEmpty(message = "Required")
    @Size(min = 3, message = "minimum 3 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message ="Required")
    @Column(name="last_name")
    private String lastName;

    @Email(message = "Enter valid email")
    @Column(name="email")
    private String email;

    @NotEmpty(message = "Required")
    @Column(name="specialist")
    private String specialist;

    @OneToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    },mappedBy = "doctor")
    @ToString.Exclude
    private List<Patient> patientList;

    public Doctor(){

    }
    public  Doctor(String  firstName,String lastName,String email,String specialist){
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.specialist=specialist;
    }
}
