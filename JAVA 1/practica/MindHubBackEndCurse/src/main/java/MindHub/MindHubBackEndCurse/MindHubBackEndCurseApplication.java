package MindHub.MindHubBackEndCurse;

import MindHub.MindHubBackEndCurse.Models.*;
import MindHub.MindHubBackEndCurse.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalTime;


@SpringBootApplication
public class MindHubBackEndCurseApplication {
@Autowired
private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MindHubBackEndCurseApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData (AdminRepository adminRepository, CourseRepository courseRepository, CourseStudentRepository courseStudentRepository, StudentRepository studentRepository, TeacherRepository teacherRepository){
		return args -> {
			System.out.println("App Loading");
			Admin admin1 = new Admin("Emanuel", "Sotelo", "123@123.com", passwordEncoder.encode("123"));
			adminRepository.save(admin1);


			Teacher teacher1 = new Teacher("Israel", "Lacosta", "234@234.com", passwordEncoder.encode("123"));

			teacherRepository.save(teacher1);

			Course course1 = new Course(LocalTime.of(17,30), LocalTime.of(19,0), "Java 2024", 34, CourseStatus.CURSED);
			Course course2 = new Course(LocalTime.of(17,30), LocalTime.of(19,0), "JavaScript 2024", 60, CourseStatus.CURSED);
			Course course3 = new Course(LocalTime.of(17,30), LocalTime.of(19,0), "HTML and CSS 2024", 90, CourseStatus.CURSED);
			Course course4 = new Course(LocalTime.of(17,30), LocalTime.of(19,0), "Introduction in to cumbias 2024", 34, CourseStatus.CURSED);

			courseRepository.save(course1);
			courseRepository.save(course2);
			courseRepository.save(course3);
			courseRepository.save(course4);

			Student student1 = new Student("Luciano", "Sapio", "lucky@123.com", passwordEncoder.encode("123"));
			Student student2 = new Student("Soledad", "mereles", "mereles24@gmial.com", passwordEncoder.encode("123"));
			Student student3= new Student("Matias", "Smith", "345@345", passwordEncoder.encode("123"));

			studentRepository.save(student1);
			studentRepository.save(student2);
			studentRepository.save(student3);

			System.out.println("App running");

		};

	}
}

