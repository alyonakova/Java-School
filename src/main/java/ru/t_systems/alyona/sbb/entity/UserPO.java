package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserPO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "login")
    private String login;

    @Column(name = "is_passenger")
    private Boolean isPassenger;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_passenger")
    private PassengerPO passenger;
}
