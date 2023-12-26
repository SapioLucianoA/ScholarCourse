package MindHub.MindHubBackEndCurse.RecordsAndDTOs;

public class StudentDTO {
    private  String id;
    private String name, LastName, email;


    public StudentDTO(String id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        LastName = lastName;
        this.email = email;
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
