package MindHub.MindHubBackEndCurse.DTOs;


import MindHub.MindHubBackEndCurse.Models.Teacher;



import java.util.Set;
import java.util.stream.Collectors;

public class TeacherDTO {
    private String id;
    private String name, LastName, email;


    private Set<String> courseNames;

    public TeacherDTO() {
    }

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.LastName = teacher.getLastName();
        this.email = teacher.getEmail();
        this.courseNames = teacher.getTeacherCourses().stream().map(course -> course.getCourseName()).collect(Collectors.toSet());
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getCourseNames() {
        return courseNames;
    }
}
