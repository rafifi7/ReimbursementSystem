package com.revature.Models;

//Here's another common DTO - send a user back to the front end without any:
// -sensitive data (password)
// -redundant data (List of Cars)
public class OutgoingUserDTO {

    private int userId;
    private String username;
    private String role;

    //boilerplate--------------/

    public OutgoingUserDTO() {
    }

    public OutgoingUserDTO(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "OutgoingUserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}