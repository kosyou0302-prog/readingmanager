package com.example.readingmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.readingmanager.entities.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>{
}
