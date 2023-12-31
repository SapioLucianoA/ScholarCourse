package MindHub.MindHubBackEndCurse.DTOs;


import MindHub.MindHubBackEndCurse.Models.CourseStatus;
import MindHub.MindHubBackEndCurse.Models.CourseStudent;

public class CourseStudentDTO {
    private String id;
    private String courseName;
    private String StudentNameAndLastName;
    private Double examGrade;
    private Double projectGrade;
    private Integer attendance;
    private Integer absence;
    private CourseStatus courseStatus;
    private Double finalNote;

    public CourseStudentDTO() {
    }
    public CourseStudentDTO(CourseStudent courseStudent){
        this.id = courseStudent.getId();
        this.courseName = courseStudent.getCourseName();
        this.StudentNameAndLastName = courseStudent.getStudent().getName() + " " + courseStudent.getStudent().getLastName();
        this.courseStatus = courseStudent.getCourseStatus();
        this.examGrade = courseStudent.getExamGrade();
        this.projectGrade = courseStudent.getProjectGrade();
        this.finalNote = courseStudent.getFinalNote();
        this.attendance = courseStudent.getAttendance();
        this.absence = courseStudent.getAbsence();
    }

    public String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getStudentNameAndLastName() {
        return StudentNameAndLastName;
    }

    public Double getExamGrade() {
        return examGrade;
    }

    public Double getProjectGrade() {
        return projectGrade;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public Integer getAbsence() {
        return absence;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public Double getFinalNote() {
        return finalNote;
    }
}
