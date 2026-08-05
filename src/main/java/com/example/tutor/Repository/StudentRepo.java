package com.example.tutor.Repository;

import com.example.tutor.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    @EntityGraph(attributePaths = {"course"})
    List<Student> findByDeletedFalse();

    @EntityGraph(attributePaths = {"course"})
    Optional<Student> findByIdAndDeletedFalse(Long id);

    @Query("SELECT s FROM Student s WHERE s.deleted = false ")
    Page<Student> findAllActive(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE LOWER (s.name) LIKE LOWER(CONCAT('%',:name,'%')) AND s.deleted=false")
    Page<Student> searchByName(@Param("name") String name , Pageable pageable);

    boolean existsByCourse_IdAndDeletedFalse(Long coursedId);
}
