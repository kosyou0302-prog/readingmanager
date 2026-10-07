package com.example.readingmanager.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.example.readingmanager.entities.ReadingLog;
import com.example.readingmanager.entities.Work;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Join;

public class WorkSpecifications {

    public static Specification<Work> titleContains(String title) {
        return (root, query, cb) -> {
            if (title == null || title.isEmpty()) return null;
            return cb.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<Work> authorIdEquals(Long authorId) {
        return (root, query, cb) -> {
            if (authorId == null) return null;
            return cb.equal(root.join("author", JoinType.INNER).get("id"), authorId);
        };
    }

    public static Specification<Work> isReadStatus(Integer status) {
        return (root, query, cb) -> {
            if (status == null) return null;
            query.distinct(true);
            Join<Work, ReadingLog> logJoin = root.join("readingLogs", JoinType.LEFT);
            if (status == 1) {return cb.isNotNull(logJoin.get("finishedDate"));}
            if (status == 2) {return cb.or(
                cb.isNull(logJoin.get("id")),
                cb.isNull(logJoin.get("finishedDate"))
        );}
            return null;
        };
    }
}
