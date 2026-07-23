package com.example.tutor.Service;

import com.example.tutor.DTO.StudentReqtDTO;
import com.example.tutor.DTO.StudentRsponDTO;
import com.example.tutor.Entity.Student;
import com.example.tutor.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Transactional
    public StudentRsponDTO createStudent(StudentReqtDTO requestDTO){
        Student student = new Student();

        student.setName(requestDTO.getName());
        student.setMajor(requestDTO.getMajor());

        Student saveStudent = studentRepo.save(student);

        return new StudentRsponDTO(saveStudent.getId(), saveStudent.getName(), saveStudent.getMajor());
    }


    public List<StudentRsponDTO> getAllStudent(){
        List<Student> students = studentRepo.findByDeletedFalse();
        return students.stream().map(student -> {
            return new StudentRsponDTO(student.getId(),student.getName(), student.getMajor());
        }).collect(Collectors.toList());
    }

    public StudentRsponDTO getStudentById(Long id){
        Student student = studentRepo.findByIdAndDeletedFalse(id)
                .orElseThrow(()-> new RuntimeException("can't find id "+ id));
        return new StudentRsponDTO(student.getId(), student.getName(), student.getMajor());
    }

    @Transactional
    public StudentRsponDTO updateStudent(Long id,StudentReqtDTO studentReqtDTO){
        Student student = studentRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("can't find id "+ id));

        student.setName(studentReqtDTO.getName());
        student.setMajor(studentReqtDTO.getMajor());

        Student updateStudent = studentRepo.save(student);
        return new StudentRsponDTO(updateStudent.getId(), updateStudent.getName(), updateStudent.getMajor());
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepo.findByIdAndDeletedFalse(id)
                .orElseThrow(()-> new RuntimeException("Can't find Id " + id));

        student.setDeleted(true);
        studentRepo.save(student);
    }
}
