package com.example.tutor.Service;

import com.example.tutor.DTO.StudentReqtDTO;
import com.example.tutor.DTO.StudentRsponDTO;
import com.example.tutor.Entity.Course;
import com.example.tutor.Entity.Student;
import com.example.tutor.Repository.CourseRepo;
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

    @Autowired
    private CourseRepo courseRepo;

    @Transactional
    public StudentRsponDTO createStudent(StudentReqtDTO requestDTO){
        Student student = new Student();
        updateStudentData(student, requestDTO);

        if (requestDTO.getCourse_id() != null){
            Course course = courseRepo.findById(requestDTO.getCourse_id())
                    .orElseThrow(()->new RuntimeException("Không thấy khóa học Id "+requestDTO.getCourse_id()));
            student.setCourse(course);
        }

        Student saveStudent = studentRepo.save(student);
        return convertToDTO(saveStudent);
    }

    @Transactional
    public List<StudentRsponDTO> getAllStudent(){
        List<Student> students = studentRepo.findByDeletedFalse();

        return students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StudentRsponDTO getStudentById(Long id){
        Student student = studentRepo.findByIdAndDeletedFalse(id)
                .orElseThrow(()-> new RuntimeException("can't find id "+ id));

        return convertToDTO(student);
    }

    @Transactional
    public StudentRsponDTO updateStudent(Long id,StudentReqtDTO studentReqtDTO){
        Student student = studentRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("can't find id "+ id));

        updateStudentData(student , studentReqtDTO);

        if (studentReqtDTO.getCourse_id() != null){
            Course course = courseRepo.findById(studentReqtDTO.getCourse_id())
                    .orElseThrow(()->new RuntimeException("Không tìm thấy khóa học Id "+studentReqtDTO.getCourse_id()));
            student.setCourse(course);
        }else {
            student.setCourse(null);
        }

        Student updateStudent = studentRepo.save(student);
        return convertToDTO(updateStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepo.findByIdAndDeletedFalse(id)
                .orElseThrow(()-> new RuntimeException("Can't find Id " + id));

        student.setDeleted(true);
        studentRepo.save(student);
    }

    @Transactional
    public StudentRsponDTO restoreStudent(Long id){
        Student student = studentRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Can't find id "+id));
        student.setDeleted(false);

        Student restoredStudent = studentRepo.save(student);
        return convertToDTO(restoredStudent);
    }

    private StudentRsponDTO convertToDTO(Student student){
        String courseName = (student.getCourse() != null) ? student.getCourse().getName():"chưa có lớp";
        return new StudentRsponDTO(student.getId(), student.getName(), student.getMajor(), courseName);
    }

    private void updateStudentData(Student student , StudentReqtDTO requestDTO){
        student.setName(requestDTO.getName());
        student.setMajor(requestDTO.getMajor());
    }

}
