package MindHub.MindHubBackEndCurse.Configurations;

import MindHub.MindHubBackEndCurse.Models.Admin;
import MindHub.MindHubBackEndCurse.Models.Student;
import MindHub.MindHubBackEndCurse.Models.Teacher;
import MindHub.MindHubBackEndCurse.Repositories.AdminRepository;
import MindHub.MindHubBackEndCurse.Repositories.StudentRepository;
import MindHub.MindHubBackEndCurse.Repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Authentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {

            if (studentRepository.existsByEmail(inputName)){
                Student student = studentRepository.findByEmail(inputName);

                return new User(student.getEmail(), student.getPassword(),

                        AuthorityUtils.createAuthorityList("STUDENT"));
            }
            if (teacherRepository.existsByEmail(inputName)){
                Teacher teacher = teacherRepository.findByEmail(inputName);
                return new User(teacher.getEmail(), teacher.getPassword(),

                        AuthorityUtils.createAuthorityList("TEACHER"));
            }
            if (adminRepository.existsByEmail(inputName)){
                Admin admin = adminRepository.findByEmail(inputName);
                return new User(admin.getEmail(), admin.getPassword(),

                        AuthorityUtils.createAuthorityList("ADMIN"));
            }
            else {

                throw new UsernameNotFoundException("User mail not found: " + inputName);

            }

        });
    }
    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

}

