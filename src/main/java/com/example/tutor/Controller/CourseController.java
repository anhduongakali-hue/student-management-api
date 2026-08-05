package com.example.tutor.Controller;

import com.example.tutor.DTO.CourseReqtDTO;
import com.example.tutor.DTO.CourseResponDTO;
import com.example.tutor.Entity.Course;
import com.example.tutor.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CourseResponDTO> createCourse(@Valid @RequestBody CourseReqtDTO request){
        CourseResponDTO result = courseService.createCourse(request);
        return new ResponseEntity<>(courseService.createCourse(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponDTO>> getAllCourses(){
        log.info("API[GET /api/courses]: Nhận request lấy danh sách toàn bộ khóa học");
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponDTO> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponDTO> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseReqtDTO request) {
        CourseResponDTO result = courseService.updateCourse(id, request);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
