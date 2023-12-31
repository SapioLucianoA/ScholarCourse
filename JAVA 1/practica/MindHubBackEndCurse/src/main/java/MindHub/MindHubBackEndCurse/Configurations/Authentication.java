package MindHub.MindHubBackEndCurse.Configurations;

import MindHub.MindHubBackEndCurse.Models.Admin;
import MindHub.MindHubBackEndCurse.Models.Person;
import MindHub.MindHubBackEndCurse.Models.Student;
import MindHub.MindHubBackEndCurse.Models.Teacher;
import MindHub.MindHubBackEndCurse.Repositories.AdminRepository;
import MindHub.MindHubBackEndCurse.Repositories.PersonRepository;
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

import java.util.Optional;

@Configuration
public class Authentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {

            Person person = personRepository.findByEmail(inputName);

            if (person instanceof Admin){
                return new User(person.getEmail(), person.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
            }
            if (person instanceof Teacher){
                return new User(person.getEmail(), person.getPassword(), AuthorityUtils.createAuthorityList("TEACHER"));
            }
            if (person instanceof Student){
                return new User(person.getEmail(), person.getPassword(), AuthorityUtils.createAuthorityList("STUDENT"));
            }

            throw new UsernameNotFoundException("User mail not found: " + inputName);
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

}

