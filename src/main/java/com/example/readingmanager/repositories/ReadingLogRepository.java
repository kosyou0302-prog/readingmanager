package com.example.readingmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.readingmanager.entities.ReadingLog;

@Repository
public interface ReadingLogRepository extends JpaRepository<ReadingLog, Long>,JpaSpecificationExecutor<ReadingLog> {
    List<ReadingLog> findByWork_Id(Long id);
    List<ReadingLog> findByWork_TitleContaining(String title);
}
