package Naveen.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public @Data class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;


    @NotNull(message = "Required")
    @Min(value = 5000, message = "Amount must be greater than 5000")
    @Column(name = "amount")
    private int amount;

    @NotEmpty(message = "Required")
    @Column(name="payment_method")
    private  String paymentMethod;

    @Column(name="date")
    @UpdateTimestamp
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
//            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "patient_id", nullable = true)
    @ToString.Exclude
    @NotNull(message = "select any one doctor")
    private Patient patient;

    public Payment(){

    }
    public Payment(int amount,String paymentMethod,Patient patient){
        this.amount=amount;
        this.paymentMethod=paymentMethod;
        this.patient=patient;
    }
}
