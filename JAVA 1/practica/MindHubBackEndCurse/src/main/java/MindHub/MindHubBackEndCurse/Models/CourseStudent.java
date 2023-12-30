package MindHub.MindHubBackEndCurse.Models;

import jakarta.persistence.*;

@Entity
public class CourseStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Student student;
    private String courseName;
    private Double examGrade;
    private Double projectGrade;
    private Integer attendance;
    private Integer absence;
    private CourseStatus courseStatus;
    private Double finalNote;

    public CourseStudent() {
    }

    public CourseStudent(String courseName, Double examGrade, Double projectGrade, Integer attendance, Integer absence, CourseStatus courseStatus, Double finalNote) {
        this.courseName = courseName;
        this.examGrade = examGrade;
        this.projectGrade = projectGrade;
        this.attendance = attendance;
        this.absence = absence;
        this.courseStatus = courseStatus;
        this.finalNote = finalNote;
    }

    public String getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(Double examGrade) {
        this.examGrade = examGrade;
    }

    public Double getProjectGrade() {
        return projectGrade;
    }

    public void setProjectGrade(Double projectGrade) {
        this.projectGrade = projectGrade;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Integer getAbsence() {
        return absence;
    }

    public void setAbsence(Integer absence) {
        this.absence = absence;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Double getFinalNote() {
        return finalNote;
    }

    public void setFinalNote(Double finalNote) {
        this.finalNote = finalNote;
    }
}
