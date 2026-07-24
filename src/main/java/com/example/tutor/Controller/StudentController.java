package com.example.tutor.Controller;

import com.example.tutor.DTO.StudentReqtDTO;
import com.example.tutor.DTO.StudentRsponDTO;
import com.example.tutor.Repository.StudentRepo;
import com.example.tutor.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentRsponDTO> createStudent(@Valid @RequestBody StudentReqtDTO requestDTO){
        StudentRsponDTO result = studentService.createStudent(requestDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<StudentRsponDTO>> getALlStudent(){
        List<StudentRsponDTO> result = studentService.getAllStudent();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentRsponDTO> getStudentById(@PathVariable Long id){
        StudentRsponDTO result = studentService.getStudentById(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentRsponDTO> updateStudent(@PathVariable Long id,@Valid @RequestBody StudentReqtDTO studentReqtDTO){
        StudentRsponDTO result = studentService.updateStudent(id,studentReqtDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<StudentRsponDTO> restoreStudent(@PathVariable Long id) {
        StudentRsponDTO result = studentService.restoreStudent(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
