package com.example.coursach.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")

    Integer id;

    @Column(name = "first_name")
    String first_name;

    @Column(name = "last_name")
    String last_name;

    @Column(name = "birthday")
    Date birthday;

    @Column(name = "phone")
    Long phone;

    @Column(name = "address")
    String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Card> cardList;

    @Override
    public String toString() {
        return last_name +
                ", phone = " + phone;
    }
}
