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

    private Integer totalStudents;
    private CourseStatus courseStatus;
    @OneToMany(mappedBy = "course")
    private Set<CourseStudent> courseStudents = new HashSet<>();
    @ManyToOne
    private Teacher teacher;



    public Course() {
    }

    public Course(LocalTime startTime, LocalTime endTime, String courseName, Integer totalStudents, CourseStatus courseStatus) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseName = courseName;
        this.totalStudents = totalStudents;
        this.courseStatus = courseStatus;
    }

    public String getId() {
        return id;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
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

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

}
