package MindHub.MindHubBackEndCurse.DTOs;

import MindHub.MindHubBackEndCurse.Models.Student;

public class StudentDTO {
    private  String id;
    private String name, LastName, email;


    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.LastName = student.getLastName();
        this.email = student.getEmail();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
