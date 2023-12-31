package MindHub.MindHubBackEndCurse.Controllers;

import MindHub.MindHubBackEndCurse.DTOs.TeacherDTO;
import MindHub.MindHubBackEndCurse.Models.PasswordValidation;
import MindHub.MindHubBackEndCurse.Models.Teacher;
import MindHub.MindHubBackEndCurse.Records.TeacherRecord;
import MindHub.MindHubBackEndCurse.Repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/teacher")
    public List<TeacherDTO> getTeachers (){
        List<Teacher> teacherList = teacherRepository.findAll();
        return teacherList.stream().map(TeacherDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/create/teacher")
    public ResponseEntity<?> createTeacher(@RequestBody TeacherRecord teacherRecord){
        if (teacherRecord.name().isBlank()){
            return new ResponseEntity<>("Please complete the Name", HttpStatus.FORBIDDEN);
        }
        if (teacherRecord.lastName().isBlank()){
            return new ResponseEntity<>("Please complete the Last name", HttpStatus.FORBIDDEN);
        }
        if (teacherRecord.email().isBlank()){
            return new ResponseEntity<>("Please complete the email", HttpStatus.FORBIDDEN);
        }
        if (teacherRecord.password().isBlank()){
            return new ResponseEntity<>("Please complete the password", HttpStatus.FORBIDDEN);
        }
        if (!PasswordValidation.validate(teacherRecord.password())){
            return new ResponseEntity<>("Your password needs an uppercase letter, a number, and at least 8 characters minimum", HttpStatus.FORBIDDEN);
        }
        Teacher teacher = new Teacher(teacherRecord.name(), teacherRecord.lastName(), teacherRecord.email(), passwordEncoder.encode(teacherRecord.password()));

       teacherRepository.save(teacher);
       return new ResponseEntity<>("Teacher created", HttpStatus.CREATED);
    }
    @PutMapping("/edit/teacher")
    public ResponseEntity<?> editTeacher(@RequestParam String teacherId, @RequestParam(required = false)String name,@RequestParam(required = false)String lastName, @RequestParam(required = false)String email,@RequestParam(required = false)String password){
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (!Optional.empty().isPresent()){
            return new ResponseEntity<>("Teacher not found", HttpStatus.BAD_REQUEST);
        }
        Teacher teacher = teacherOptional.get();

        if (name != null){
            teacher.setName(name);
        }
        if (lastName != null){
            teacher.setLastName(lastName);
        }
        if (email != null){
            teacher.setEmail(email);
        }
        if (password != null){
            teacher.setPassword(password);
        }

        teacherRepository.save(teacher);
        return new ResponseEntity<>("Changes saved!", HttpStatus.OK);

    }
    @PostMapping("/remove/teacher")
    public ResponseEntity<?> deleteTeacher(@RequestParam String teacherId){
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (!teacherOptional.isPresent()){
            return new ResponseEntity<>("Teacher not found", HttpStatus.BAD_REQUEST);
        }

        teacherRepository.deleteById(teacherId);
        return new ResponseEntity<>("Teacher deleted!", HttpStatus.OK);
    }


}
