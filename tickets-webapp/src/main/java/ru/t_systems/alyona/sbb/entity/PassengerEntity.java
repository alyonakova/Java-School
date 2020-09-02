package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "passenger")
public class PassengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passenger_id_gen")
    @SequenceGenerator(name = "passenger_id_gen", sequenceName = "passenger_id_seq", allocationSize = 50)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @OneToOne(mappedBy = "passenger")
    private UserEntity user;

    @OneToMany(mappedBy = "passenger")
    private List<TicketEntity> tickets;

}
