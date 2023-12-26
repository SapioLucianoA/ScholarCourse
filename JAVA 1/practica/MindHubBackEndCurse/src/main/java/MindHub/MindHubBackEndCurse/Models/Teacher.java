package MindHub.MindHubBackEndCurse.Models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String names, LastName, email,password;

    @OneToMany(mappedBy = "teacher")
    private Set<CourseTeacher> courseTeachers = new HashSet<>();

    public Teacher() {
    }

    public Teacher(String names, String lastName, String email, String password) {
        this.names = names;
        LastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<CourseTeacher> getCourseTeachers() {
        return courseTeachers;
    }

    public void setCourseTeachers(Set<CourseTeacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }
}
