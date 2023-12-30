package MindHub.MindHubBackEndCurse.Records;


import java.time.LocalTime;

public record CourseRecord(LocalTime startTime, LocalTime endTime, String courseName, Integer totalStudents) {

}
