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
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 50)
    @Column(name = "id", unique = true, nullable = false)
    private BigInteger id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "is_passenger")
    private Boolean isPassenger;

    @Column(name = "password")
    private String password;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_passenger")
    private PassengerEntity passenger;
}
