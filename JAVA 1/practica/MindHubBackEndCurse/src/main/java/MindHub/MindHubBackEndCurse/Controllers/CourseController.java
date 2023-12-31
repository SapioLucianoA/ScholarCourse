package MindHub.MindHubBackEndCurse.Controllers;

import MindHub.MindHubBackEndCurse.Models.Course;
import MindHub.MindHubBackEndCurse.DTOs.CourseDTO;
import MindHub.MindHubBackEndCurse.Models.CourseStatus;
import MindHub.MindHubBackEndCurse.Models.Teacher;
import MindHub.MindHubBackEndCurse.Records.CourseRecord;
import MindHub.MindHubBackEndCurse.Repositories.CourseRepository;
import MindHub.MindHubBackEndCurse.Repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @GetMapping("/courses")
    public List<CourseDTO> getAllCourses (){
        List<Course> courseList = courseRepository.findAll();
        return courseList.stream().map(CourseDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/create/course")
    public ResponseEntity<?> createCourse(@RequestBody CourseRecord courseR){
        if (courseR.courseName().isBlank()){
            return new ResponseEntity<>("Please Complete the name", HttpStatus.FORBIDDEN);
        }
        if (courseR.totalStudents().toString().isBlank()){
            return new ResponseEntity<>("Please Complete the Total Students",HttpStatus.FORBIDDEN);
        }
        if (courseR.startTime().toString().isBlank()){
            return new ResponseEntity<>("Please complete the start of the Course", HttpStatus.FORBIDDEN);
        }
        if (courseR.endTime().toString().isBlank()){
            return new ResponseEntity<>("Please complete the end of the course", HttpStatus.FORBIDDEN);
        }
        Course course = new Course(courseR.startTime(), courseR.endTime(), courseR.courseName(), courseR.totalStudents(), CourseStatus.CURSED);
        courseRepository.save(course);
        return new ResponseEntity<>("Course created!", HttpStatus.CREATED);

    }

    @PutMapping("/edit/course")
    public ResponseEntity<?> editCourse(@RequestParam String id, @RequestParam(required = false) String courseName, @RequestParam(required = false) Integer totalStudents, @RequestParam(required = false)LocalTime startTime, @RequestParam(required = false) LocalTime endTime, @RequestParam(required = false) String teacherId){
        Optional<Course> course = courseRepository.findById(id);
        if (!course.isPresent()){
            return new ResponseEntity<>("Course not found", HttpStatus.BAD_REQUEST);
        }

        Course course1 = course.get();
        if (courseName != null){
            course1.setCourseName(courseName);
        }
        if (totalStudents != null){
            course1.setTotalStudents(totalStudents);
        }
        if (startTime != null){
            course1.setStartTime(startTime);
        }
        if (endTime != null){
            course1.setEndTime(endTime);
        }
        if (teacherId != null){
            Optional<Teacher> teacher = teacherRepository.findById(teacherId);
            if (!teacher.isPresent()){
                return new ResponseEntity<>("Teacher not found", HttpStatus.BAD_REQUEST);
            }
            course1.setTeacher(teacher.get());
        }

        courseRepository.save(course1);
        return new ResponseEntity<>("Changes saved!", HttpStatus.OK);
    }
    @PutMapping("/remove/course")
    public  ResponseEntity<?> deleteCourse(@RequestParam String id){
        Optional<Course> course = courseRepository.findById(id);
        if (!course.isPresent()){
            return new ResponseEntity<>("Course not found", HttpStatus.BAD_REQUEST);

        }
        Course course1 = course.get();
        course1.setCourseStatus(CourseStatus.FINALIZED);
        courseRepository.save(course1);
        return new ResponseEntity<>("Course delete", HttpStatus.OK);
    }

    @PutMapping("/delete/teacher/course")
    public ResponseEntity<?> deleteTeacher(@RequestParam String teacherId, @RequestParam String courseId){
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        Optional<Course> course =courseRepository.findById(courseId);
        if (!teacher.isPresent()) {
            return new ResponseEntity<>("Teacher not found", HttpStatus.BAD_REQUEST);
        }

        if (!course.isPresent()) {
            return new ResponseEntity<>("Course not found", HttpStatus.BAD_REQUEST);
        }

        Teacher teacher1 = teacher.get();
        Course course1 = course.get();

        teacher1.removeCourse(course1);
        course1.setTeacher(null);
        teacherRepository.save(teacher1);
        courseRepository.save(course1);
        return new ResponseEntity<>("Teacher removed!", HttpStatus.OK);

    }

}
