package com.example.readingmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.readingmanager.entities.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>,JpaSpecificationExecutor<Work>{
    List<Work> findByAuthor_Id(Long id);
    List<Work> findByTitleContaining(String title);
}
