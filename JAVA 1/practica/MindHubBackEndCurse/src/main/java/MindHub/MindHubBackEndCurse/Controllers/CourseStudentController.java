package MindHub.MindHubBackEndCurse.Controllers;

import MindHub.MindHubBackEndCurse.DTOs.StudentDTO;
import MindHub.MindHubBackEndCurse.Models.Course;
import MindHub.MindHubBackEndCurse.Models.CourseStatus;
import MindHub.MindHubBackEndCurse.Models.CourseStudent;
import MindHub.MindHubBackEndCurse.Models.Student;
import MindHub.MindHubBackEndCurse.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseStudentController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;

    @PostMapping("/inscription")
    public ResponseEntity<?> inscriptionStudent(@RequestParam String studentId, @RequestParam String courseId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (!studentOptional.isPresent()){
            return new ResponseEntity<>("Student not found", HttpStatus.BAD_REQUEST);
        }

        if (!courseOptional.isPresent()){
            return new ResponseEntity<>("Course not found", HttpStatus.BAD_REQUEST);
        }

        Student student = studentOptional.get();
        Course course = courseOptional.get();

        CourseStudent courseStudent = new CourseStudent(course.getCourseName(), 0.00,0.00,0,0, CourseStatus.CURSED,0.00);
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);

        // Agrega la entidad CourseStudent a las colecciones en Student y Course
        student.getCourseStudents().add(courseStudent);
        course.getCourseStudents().add(courseStudent);

        courseStudentRepository.save(courseStudent);
        studentRepository.save(student);
        courseRepository.save(course);

        return new ResponseEntity<>("Inscription saved!", HttpStatus.OK);
    }


    @PutMapping("/notes")
    public ResponseEntity<?> changesNotesAndAssistStudent(@RequestParam String CSId, @RequestParam(required = false) Double examGrade,@RequestParam(required = false) Double projectGrade,@RequestParam(required = false) Integer attendance,@RequestParam(required = false) Integer absence,@RequestParam(required = false)CourseStatus courseStatus, @RequestParam(required = false) Double finalNote){
        Optional<CourseStudent> optionalCourseStudent = courseStudentRepository.findById(CSId);

        if (!optionalCourseStudent.isPresent()){
            return new ResponseEntity<>("not Student in this course", HttpStatus.BAD_REQUEST);

        }
        CourseStudent courseStudent = optionalCourseStudent.get();

        if (examGrade != null){
            courseStudent.setExamGrade(examGrade);
        }
        if (projectGrade != null){
            courseStudent.setProjectGrade(projectGrade);
        }
        if (attendance != null){
            courseStudent.setAttendance(attendance);
        }
        if (absence != null){
            courseStudent.setAbsence(absence);
        }
        if (courseStatus != null){
            courseStudent.setCourseStatus(courseStatus);
        }
        if (finalNote != null){
            courseStudent.setFinalNote(finalNote);
        }

        courseStudentRepository.save(courseStudent);
        return new ResponseEntity<>("Changes saved!", HttpStatus.OK);
    }


}
