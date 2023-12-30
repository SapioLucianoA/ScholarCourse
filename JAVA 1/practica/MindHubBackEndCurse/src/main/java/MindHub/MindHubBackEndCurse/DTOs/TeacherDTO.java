package MindHub.MindHubBackEndCurse.DTOs;


import MindHub.MindHubBackEndCurse.Models.Teacher;



import java.util.Set;
import java.util.stream.Collectors;

public class TeacherDTO {
    private String id;
    private String names, LastName, email;


    private Set<String> courseNames;

    public TeacherDTO() {
    }

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.names = teacher.getNames();
        this.LastName = teacher.getLastName();
        this.email = teacher.getEmail();
        this.courseNames = teacher.getTeacherCourses().stream().map(course -> course.getCourseName()).collect(Collectors.toSet());
    }


    public String getId() {
        return id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(Set<String> courseNames) {
        this.courseNames = courseNames;
    }
}
