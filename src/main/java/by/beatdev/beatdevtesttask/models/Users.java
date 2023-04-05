package by.beatdev.beatdevtesttask.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name ="users")
@NoArgsConstructor
@AllArgsConstructor

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surName", nullable = false)
    private String surName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "uri", nullable = false)
    private String uri;
    @Column(name = "status")
    private String status = "offline";



}
