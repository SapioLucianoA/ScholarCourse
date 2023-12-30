package MindHub.MindHubBackEndCurse.Controllers;

import MindHub.MindHubBackEndCurse.Models.Admin;
import MindHub.MindHubBackEndCurse.Models.PasswordValidation;
import MindHub.MindHubBackEndCurse.Records.AdminRecord;
import MindHub.MindHubBackEndCurse.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/create/admin")
    public ResponseEntity<?> CreateAdmin(AdminRecord adminRecord, Authentication authentication){
        if (!adminRepository.existsByEmail(authentication.getName())){
            return new ResponseEntity<>("Action only for Admins", HttpStatus.FORBIDDEN);
        }
        if (adminRecord.name().isBlank()){
            return new ResponseEntity<>("Please complete the Name", HttpStatus.FORBIDDEN);
        }
        if (adminRecord.lastName().isBlank()){
            return new ResponseEntity<>("Please complete the Last name", HttpStatus.FORBIDDEN);
        }
        if (adminRecord.email().isBlank()){
            return new ResponseEntity<>("Please complete the email", HttpStatus.FORBIDDEN);
        }
        if (adminRecord.password().isBlank()){
            return new ResponseEntity<>("Please complete the password", HttpStatus.FORBIDDEN);
        }
        if (!PasswordValidation.validate(adminRecord.password())){
            return new ResponseEntity<>("Your password needs an uppercase letter, a number, and at least 8 characters minimum", HttpStatus.FORBIDDEN);
        }

        Admin admin = new Admin(adminRecord.name(), adminRecord.lastName(), adminRecord.email(), passwordEncoder.encode(adminRecord.password()));

        adminRepository.save(admin);
        return new ResponseEntity<>("Admin Created", HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/delete/admin")
    public ResponseEntity<?> deleteAdmin(@RequestParam String name, @RequestParam String lastName){
           Admin admin = adminRepository.findByEmailAndLastName(name, lastName);

           adminRepository.delete(admin);
           return new ResponseEntity<>("Admin delete", HttpStatus.OK);
    }

    @PatchMapping("/patch/admin/")
    public ResponseEntity<?> changePasswordAdmin (@RequestParam String id, @RequestParam(required = false)String name,@RequestParam(required = false) String lastName, @RequestParam(required = false) String email, @RequestParam(required = false) String password){

        Optional<Admin> adminOptional = adminRepository.findById(id);
        if(adminOptional.isPresent()){
            Admin admin = adminOptional.get();

        if(name != null){
            admin.setName(name);
        }
        if(lastName != null){
            admin.setLastName(lastName);
        }
        if(email != null){
            admin.setEmail(email);
        }
        if(password != null){
            if (PasswordValidation.validate(password)){
                admin.setPassword(passwordEncoder.encode(password));
            }else {
                return new  ResponseEntity<>("Your password needs an uppercase letter, a number, and at least 8 characters minimum", HttpStatus.FORBIDDEN);
            }

        }
        adminRepository.save(admin);
        return new ResponseEntity<>("Admin change", HttpStatus.I_AM_A_TEAPOT);
    }else {
            return new ResponseEntity<>("Admin not found for" + id, HttpStatus.FORBIDDEN);
        }
    }
}
