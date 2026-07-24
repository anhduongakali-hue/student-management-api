package com.example.tutor.Repository;

import com.example.tutor.Entity.Student;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    @EntityGraph(attributePaths = {"course"})
    List<Student> findByDeletedFalse();

    @EntityGraph(attributePaths = {"course"})
    Optional<Student> findByIdAndDeletedFalse(Long id);
}
