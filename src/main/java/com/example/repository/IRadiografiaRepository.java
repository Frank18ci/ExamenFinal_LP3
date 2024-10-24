package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Radiografia;
@Repository
public interface IRadiografiaRepository extends JpaRepository<Radiografia, Integer> {

}
