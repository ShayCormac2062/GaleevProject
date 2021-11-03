package ru.itis.forms;

public class UserForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    public UserForm() {
    }

    public UserForm(String firstName, String lastName, String email, String password) {
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
