package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Alergia;
@Repository
public interface IAlergiaRepository extends JpaRepository<Alergia, Integer> {

}
