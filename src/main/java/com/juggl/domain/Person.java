package com.juggl.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(
        mappedBy = "person",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<PersonSkill> skills = new ArrayList<>();

    // convenience method
    public void addSkill(Skill skill, ExpertiseLevel level) {
        PersonSkill ps = new PersonSkill(this, skill, level);
        skills.add(ps);
    }

    // getters/setters
}
