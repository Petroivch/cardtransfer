package com.example.coursach.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transfer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")

    Long id;

    @Column(name = "transaction_date")
    java.sql.Date transaction_date;


    @Column(name = "amount")
    Double amount;

    @Column(name = "result")
    boolean result;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_source_id", referencedColumnName = "id")
    Card cardSource;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_destination_id", referencedColumnName = "id")
    Card cardDestination;
}
