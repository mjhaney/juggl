package com.juggl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juggl.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}