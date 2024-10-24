package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.HistorialMedico;
@Repository
public interface IHistorialMedicoRepository extends JpaRepository<HistorialMedico, Integer> {

}
