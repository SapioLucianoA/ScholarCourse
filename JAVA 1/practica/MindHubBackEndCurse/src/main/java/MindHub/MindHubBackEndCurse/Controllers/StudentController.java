package MindHub.MindHubBackEndCurse.Controllers;

import MindHub.MindHubBackEndCurse.Models.CourseStudent;
import MindHub.MindHubBackEndCurse.Models.PasswordValidation;
import MindHub.MindHubBackEndCurse.Models.Student;
import MindHub.MindHubBackEndCurse.DTOs.StudentDTO;
import MindHub.MindHubBackEndCurse.Records.StudentRecord;
import MindHub.MindHubBackEndCurse.Repositories.CourseStudentRepository;
import MindHub.MindHubBackEndCurse.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CourseStudentRepository courseStudentRepository;


    @GetMapping("/students")
    public List<StudentDTO> getAllStudents () {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }
    @PostMapping("/create/student")
    public ResponseEntity<?> studentCreated(@RequestBody StudentRecord studentRecord){
        if (studentRecord.name().isBlank()){
            return new ResponseEntity<>("Please complete the Name", HttpStatus.FORBIDDEN);
        }
        if (studentRecord.lastName().isBlank()){
            return new ResponseEntity<>("Please complete the Last name", HttpStatus.FORBIDDEN);
        }
        if (studentRecord.email().isBlank()){
            return new ResponseEntity<>("Please complete the email", HttpStatus.FORBIDDEN);
        }
        if (studentRecord.password().isBlank()){
            return new ResponseEntity<>("Please complete the password", HttpStatus.FORBIDDEN);
        }
        if (!PasswordValidation.validate(studentRecord.password())){
            return new ResponseEntity<>("Your password needs an uppercase letter, a number, and at least 8 characters minimum", HttpStatus.FORBIDDEN);
        }
        Student student = new Student(studentRecord.name(), studentRecord.lastName(), studentRecord.email(), passwordEncoder.encode(studentRecord.password()));
        studentRepository.save(student);
        return  new ResponseEntity<>("Your profile is created", HttpStatus.CREATED);
    }
    @PostMapping("/remove/student")
    public ResponseEntity<?> deleteStudent (@RequestParam String id){

        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()){
            Student student = studentOptional.get();
            Set<CourseStudent> courseStudentSet =  student.getCourseStudents();
            courseStudentSet.forEach(courseStudent -> courseStudentRepository.deleteById(courseStudent.getId()));
            studentRepository.delete(student);
            return new ResponseEntity<>("student is delete", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Student not found", HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/edit/student")
    public ResponseEntity<?> changeStudent(@RequestParam String id,@RequestParam(required = false)String name,@RequestParam(required = false)String lastName, @RequestParam(required = false)String email,@RequestParam(required = false)String password) {
    Optional<Student> studentOptional = studentRepository.findById(id);
    if (studentOptional.isPresent()){
        Student student = studentOptional.get();
        if(name != null){
            student.setName(name);
        }
        if(lastName != null){
            student.setLastName(lastName);
        }
        if(email != null){
            student.setEmail(email);
        }
        if(password != null){
            if (PasswordValidation.validate(password)) {
                student.setPassword(passwordEncoder.encode(password));
            }else {
            return new ResponseEntity<>("Your password needs an uppercase letter, a number, and at least 8 characters minimum", HttpStatus.FORBIDDEN);}
        }
        studentRepository.save(student);
        return new ResponseEntity<>("Student saved", HttpStatus.OK);
    }else {
        return new ResponseEntity<>("Student not found by id:" + id, HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping("/found/students")
    public ResponseEntity<?> foundStudent(@RequestParam(required = false) String name, @RequestParam(required = false) String lastName) {
        if (name != null || lastName != null) {
            List<Student> studentList = studentRepository.findByNameAndLastNameContaining(name, lastName);
            List<StudentDTO> students = studentList.stream()
                    .map(StudentDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(students);
        }
        if (name != null) {
            List<Student> studentList = studentRepository.findByNameContaining(name);
            List<StudentDTO> students = studentList.stream()
                    .map(StudentDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(students);
        }
        if (lastName != null) {
            List<Student> studentList = studentRepository.findByLastNameContaining(lastName);
            List<StudentDTO> students = studentList.stream()
                    .map(StudentDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(students);
        }
           else {
                return new ResponseEntity<>("Students not found", HttpStatus.BAD_REQUEST);
            }

    }
    @GetMapping("find/students")
    public ResponseEntity<?> findByLastNameAndCourse(@RequestParam String lastName, @RequestParam String courseName){
        List<Student> studentList = studentRepository.findByLastNameAndCourseName(lastName, courseName);

        if (studentList.isEmpty()){
            return new ResponseEntity<>("No students found", HttpStatus.NOT_FOUND);
        }
        List<StudentDTO> studentDTOS = studentList.stream()
                .map(StudentDTO::new).toList();
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

}


