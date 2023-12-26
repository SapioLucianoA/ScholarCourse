package MindHub.MindHubBackEndCurse.Models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalTime startTime;
    private LocalTime endTime;
    private String courseName;

    private int totalStudents;
    @OneToMany(mappedBy = "courseSt")
    private Set<CourseStudent> courseStudents = new HashSet<>();
    @OneToMany(mappedBy = "course")
    private Set<CourseTeacher> courseTeachers = new HashSet<>();


    public Course() {
    }

    public Course(LocalTime startTime, LocalTime endTime, String courseName, int totalStudents) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseName = courseName;
        this.totalStudents = totalStudents;
    }

    public String getId() {
        return id;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Set<CourseTeacher> getCourseTeachers() {
        return courseTeachers;
    }

    public void setCourseTeachers(Set<CourseTeacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Set<CourseStudent> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(Set<CourseStudent> courseStudents) {
        this.courseStudents = courseStudents;
    }



}
