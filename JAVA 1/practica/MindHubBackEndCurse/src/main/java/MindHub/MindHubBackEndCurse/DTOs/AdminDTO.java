package MindHub.MindHubBackEndCurse.DTOs;

import MindHub.MindHubBackEndCurse.Models.Admin;

public class AdminDTO {
    private String id;

    private String name, lastName, email;

    public AdminDTO() {
    }

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.lastName = admin.getLastName();
        this.email = admin.getEmail();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
