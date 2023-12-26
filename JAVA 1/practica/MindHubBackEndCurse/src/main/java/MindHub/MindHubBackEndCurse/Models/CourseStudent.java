package MindHub.MindHubBackEndCurse.Models;

import jakarta.persistence.*;

@Entity
public class CourseStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private Course courseSt;
    @ManyToOne
    private Student student;
    private String courseName;
    private double examGrade;
    private double projectGrade;
    private Integer attendance;
    private Integer absence;
    private CourseStatus courseStatus;
    private double finalNote;

    public CourseStudent() {
    }

    public CourseStudent(String courseName, double examGrade, double projectGrade, Integer attendance, Integer absence, CourseStatus courseStatus, double finalNote) {
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

    public void setFinalNote(double finalNote) {
        this.finalNote = finalNote;
    }



    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getFinalNote() {
        return finalNote;
    }

    public void setFinalNote(Double finalNote) {
        this.finalNote = finalNote;
    }

    public Course getCourseSt() {
        return courseSt;
    }

    public void setCourseSt(Course courseSt) {
        this.courseSt = courseSt;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(double examGrade) {
        this.examGrade = examGrade;
    }

    public double getProjectGrade() {
        return projectGrade;
    }

    public void setProjectGrade(double projectGrade) {
        this.projectGrade = projectGrade;
    }


}
