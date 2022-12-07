package com.payload.request;


public class SignupRequest {

    private String username;
    private String password;
    private String email;
//    private Set<String> role;

    private String passwordConfirm;

    public String getUsername() {       return username;    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {        return passwordConfirm;    }

    public void setPasswordConfirm(String passwordConfirm) {        this.passwordConfirm = passwordConfirm;    }

//    public Set<String> getRole() {
//        return this.role;
//    }
//
//    public void setRole(Set<String> role) {
//        this.role = role;
//    }
}