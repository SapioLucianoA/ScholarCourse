package MindHub.MindHubBackEndCurse.Models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @OneToMany(mappedBy = "teacher")
    private Set<Course> teacherCourses = new HashSet<Course>();


    public Teacher() {
    }

    public Teacher(String name, String lastName, String email, String password) {
        super(name, lastName, email, password);
    }

    public void addCourse(Course course) {
        this.teacherCourses.add(course);
        course.setTeacher(this);
    }

    public Set<Course> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(Set<Course> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public void removeCourse(Course course){
        this.teacherCourses.remove(course);
        course.setTeacher(null);
    }
}
