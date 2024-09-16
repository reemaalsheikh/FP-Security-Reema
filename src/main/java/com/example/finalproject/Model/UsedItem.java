package com.example.finalproject.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "description cannot be empty")
    @Size(min = 5, message = "description must be at least 5 characters long")
    @Column(columnDefinition = "varchar(50) not null")
    private String description;

    @NotNull(message = "price should be not null!")
    @Positive
    @Column(columnDefinition = "double not null")
    private double price;

    @NotEmpty(message = "category cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String category; // Example: Books, Notes, Tools, etc.

    @NotNull
    @Column(columnDefinition = "boolean not null")
    private boolean isUsed;

    //Relations

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;  // The user who buys the product

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;  // The user who sells the product

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;


}
