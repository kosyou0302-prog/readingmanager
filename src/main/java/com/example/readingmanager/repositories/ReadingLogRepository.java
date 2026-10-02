package com.example.readingmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.readingmanager.entities.ReadingLog;

@Repository
public interface ReadingLogRepository extends JpaRepository<ReadingLog, Long> {
    List<ReadingLog> findByWorkId(Long id);
}
