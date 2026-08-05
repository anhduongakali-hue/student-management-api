package com.example.tutor.Service;

import com.example.tutor.DTO.CourseReqtDTO;
import com.example.tutor.DTO.CourseResponDTO;
import com.example.tutor.Entity.Course;
import com.example.tutor.Exception.InvalidOperationException;
import com.example.tutor.Exception.ResourceNotFoundException;
import com.example.tutor.Repository.CourseRepo;
import com.example.tutor.Repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    @Transactional
    public CourseResponDTO createCourse(CourseReqtDTO request){
        log.info("Đang tạo khóa học :{}",request.getName());
        Course course = new Course();
        course.setName(request.getName());

        Course savedCourse = courseRepo.save(course);
        log.info("tạo thành công khóa học ID: {}",savedCourse.getId());

        return convertToDTO(savedCourse);
    }

    @Transactional
    public List<CourseResponDTO> getAllCourse(){
        log.info("Đang lấy danh sách toàn bộ khóa học");

        List<Course> courses = courseRepo.findAllByDeletedFalse();
        log.info("Tìm thấy tổng {} khóa học ",courses.size());

        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CourseResponDTO getCourseById(Long id){
        log.info("Tìm khóa học ID {} ",courseRepo.findById(id));

        Course course = courseRepo.findByIdAndDeletedFalse(id)
                .orElseThrow(()->{
                    log.warn("Không tìm thấy khóa học ID {}",id);
                    return new ResourceNotFoundException("Không tìm thấy khóa học ID {}"+id);
                });
        log.info("Tìm thấy khóa học: {}",course.getName());
        return convertToDTO(course);
    }

    @Transactional
    public CourseResponDTO updateCourse(Long id,CourseReqtDTO request){
        log.info("Đang cập nhật thông tin khóa học ID {}",id);

        Course course = courseRepo.findByIdAndDeletedFalse(id)
                .orElseThrow(()->{
                    log.warn("Cập nhật thất bại . Không thấy khóa học ID : {}",id);
                    return new ResourceNotFoundException("Không thấy khóa học ID: " +id);
                });
        course.setName(request.getName());

        Course updateCourse = courseRepo.save(course);
        log.info("Cập nhật khóa học ID {} thành công  ",id);

        return convertToDTO(updateCourse);
    }

    @Transactional
    public void deleteCourse(Long id){
        log.info("Đang xóa khóa học ID: {}",id);

        Course course = courseRepo.findByIdAndDeletedFalse(id)
                .orElseThrow(()->{
                    log.warn("Xóa thất bại . Không tìm thấy khóa học ID = {}",id);
                    return new ResourceNotFoundException("Không tim thấy khóa học ID : "+ id);
                });
        if (studentRepo.existsByCourse_IdAndDeletedFalse(id)){
            log.warn("Xóa thất bại . Khóa học ID = {} đang có sinh viên tham gia ",id);
            throw new InvalidOperationException("Không thể xóa khóa học đang có sinh viên tham gia");
        }

        course.setDeleted(true);
        courseRepo.save(course);

        log.warn("Đã xóa khóa học ID = {} ",id);

    }

    private CourseResponDTO convertToDTO(Course course){
        return new CourseResponDTO(course.getId(), course.getName());
    }
}
