package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "total price should be not null!")
    @Positive
    @Column(columnDefinition = "double not null")
    private double totalPrice;

    @NotEmpty(message = "status should be not Empty!")
    @Pattern(regexp="^(PENDING|COMPLETED|CANCELLED)$")
    //@Column(columnDefinition = "varchar(20) check(status='PENDING' or status='COMPLETED' or status='CANCELLED')")
    private String status="PENDING"; // PENDING, COMPLETED, CANCELLED

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    //Relations

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;


    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UsedItem> usedItems;  // List of products/used items in the order

}
