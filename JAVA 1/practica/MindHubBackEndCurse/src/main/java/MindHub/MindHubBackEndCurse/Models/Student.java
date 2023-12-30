package MindHub.MindHubBackEndCurse.Models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends Person {



    @OneToMany(mappedBy = "student")
    private Set<CourseStudent> courseStudents = new HashSet<>();


    public Student() {
    }

    public Student(String name, String lastName, String email, String password) {
        super(name, lastName, email, password);
    }

    public Set<CourseStudent> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(Set<CourseStudent> courseStudents) {
        this.courseStudents = courseStudents;
    }
}
