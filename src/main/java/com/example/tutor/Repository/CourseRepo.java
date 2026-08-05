package com.example.tutor.Repository;

import com.example.tutor.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long > {
    List<Course> findAllByDeletedFalse();
    Optional<Course> findByIdAndDeletedFalse(Long id);
}
