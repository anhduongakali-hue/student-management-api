package com.example.tutor.Repository;

import com.example.tutor.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    List<Student> findByDeletedFalse();

    Optional<Student> findByIdAndDeletedFalse(Long id);
}
