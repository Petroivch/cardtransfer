package com.example.coursach.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Integer id;
    @Column(name = "number")
    String number;

    @Column(name = "cvv")
    Integer cvv;

    @Column(name = "date_exp")
    String date_expiration;


    @Column(name = "amount")
    Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @OneToMany(mappedBy = "cardSource", cascade = CascadeType.ALL)
    List<Transfer> transferSourceList;

    @OneToMany(mappedBy = "cardDestination", cascade = CascadeType.ALL)
    List<Transfer> transferDestList;

    @Override
    public String toString() {
        return "" + id;
    }
}
