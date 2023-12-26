package MindHub.MindHubBackEndCurse.Models;

import jakarta.persistence.*;

@Entity
public class CourseTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String courseName;


    @ManyToOne
    private Course course;

    @ManyToOne
    private Teacher teacher;

    public CourseTeacher() {
    }

    public CourseTeacher(String courseName) {
        this.courseName = courseName;

    }

    public String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course courseTch) {
        this.course = courseTch;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
