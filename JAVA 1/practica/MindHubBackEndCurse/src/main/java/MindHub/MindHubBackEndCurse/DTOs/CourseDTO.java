package MindHub.MindHubBackEndCurse.DTOs;


import MindHub.MindHubBackEndCurse.Models.Course;

import java.time.LocalTime;

public class CourseDTO {
    private String id;
    private LocalTime startTime;
    private LocalTime endTime;
    private String courseName;

    private int totalStudents;

    private String teacherId;

    public CourseDTO() {
    }

    public CourseDTO(Course course) {
        this.id = getId();
        this.startTime = getStartTime();
        this.endTime = getEndTime();
        this.courseName = getCourseName();
        this.totalStudents = getTotalStudents();
        this.teacherId = course.getTeacher().getId();
    }

    public String getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public String getTeacherId() {
        return teacherId;
    }
}
