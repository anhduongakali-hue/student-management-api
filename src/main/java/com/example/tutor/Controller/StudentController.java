package com.example.tutor.Controller;

import com.example.tutor.DTO.StudentReqtDTO;
import com.example.tutor.DTO.StudentRsponDTO;
import com.example.tutor.Service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<StudentRsponDTO> createStudent(@Valid @RequestBody StudentReqtDTO requestDTO){
        StudentRsponDTO result = studentService.createStudent(requestDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Page<StudentRsponDTO>> getALlStudent(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String name
    ){
        log.info("API[GET /students]: Nhận request lấy danh sách sinh viên.Từ khóa:'{}',Trang:{},Số lượng:{} ",name , page,size);

        Page<StudentRsponDTO> result = studentService.getAllStudent(name,page,size,sortBy,sortDir);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentRsponDTO> getStudentById(@PathVariable Long id){
        StudentRsponDTO result = studentService.getStudentById(id);
        return ResponseEntity.ok(result);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StudentRsponDTO> updateStudent(@PathVariable Long id,@Valid @RequestBody StudentReqtDTO studentReqtDTO){
        StudentRsponDTO result = studentService.updateStudent(id,studentReqtDTO);
        return ResponseEntity.ok(result);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}/restore")
    public ResponseEntity<StudentRsponDTO> restoreStudent(@PathVariable Long id) {
        StudentRsponDTO result = studentService.restoreStudent(id);
        return ResponseEntity.ok(result);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
