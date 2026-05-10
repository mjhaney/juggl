package com.juggl.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Skill {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    // getters/setters
}