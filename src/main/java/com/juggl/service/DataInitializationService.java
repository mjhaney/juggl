package com.juggl.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.juggl.domain.ExpertiseLevel;
import com.juggl.domain.Person;
import com.juggl.domain.Skill;
import com.juggl.repository.PersonRepository;
import com.juggl.repository.PersonSkillRepository;
import com.juggl.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataInitializationService implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final SkillRepository skillRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only initialize if no data exists
        if (personRepository.count() > 0) {
            return;
        }

        // Create skills
        Skill java = new Skill();
        java.setName("Java");
        skillRepository.save(java);

        Skill spring = new Skill();
        spring.setName("Spring Framework");
        skillRepository.save(spring);

        Skill react = new Skill();
        react.setName("React");
        skillRepository.save(react);

        Skill python = new Skill();
        python.setName("Python");
        skillRepository.save(python);

        Skill sql = new Skill();
        sql.setName("SQL");
        skillRepository.save(sql);

        Skill javascript = new Skill();
        javascript.setName("JavaScript");
        skillRepository.save(javascript);

        // Create persons
        Person alice = new Person();
        alice.setName("Alice Johnson");
        alice.addSkill(java, ExpertiseLevel.EXPERT);
        alice.addSkill(spring, ExpertiseLevel.ADVANCED);
        alice.addSkill(sql, ExpertiseLevel.INTERMEDIATE);
        personRepository.save(alice);

        Person bob = new Person();
        bob.setName("Bob Smith");
        bob.addSkill(java, ExpertiseLevel.ADVANCED);
        bob.addSkill(python, ExpertiseLevel.INTERMEDIATE);
        bob.addSkill(react, ExpertiseLevel.BEGINNER);
        personRepository.save(bob);

        Person charlie = new Person();
        charlie.setName("Charlie Brown");
        charlie.addSkill(javascript, ExpertiseLevel.ADVANCED);
        charlie.addSkill(react, ExpertiseLevel.EXPERT);
        charlie.addSkill(spring, ExpertiseLevel.INTERMEDIATE);
        personRepository.save(charlie);

        Person diana = new Person();
        diana.setName("Diana Prince");
        diana.addSkill(python, ExpertiseLevel.EXPERT);
        diana.addSkill(sql, ExpertiseLevel.ADVANCED);
        diana.addSkill(java, ExpertiseLevel.INTERMEDIATE);
        personRepository.save(diana);

        Person eve = new Person();
        eve.setName("Eve Wilson");
        eve.addSkill(javascript, ExpertiseLevel.INTERMEDIATE);
        eve.addSkill(react, ExpertiseLevel.ADVANCED);
        eve.addSkill(spring, ExpertiseLevel.BEGINNER);
        personRepository.save(eve);
    }
}