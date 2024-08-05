package com.doggo.dogadopt.model;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {

    private Long myId;
    private String firstName;
    private String lastName;
    private String myAddress;
    private String contactNumber;
    private String username;
    private String password;
    private String role;
    private int age;

    public Account() {
    }

    public Account(Long myId, String firstName, String lastName, String myAddress, String contactNumber, int age, String username, String password, String role) {
        this.myId = myId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.myAddress = myAddress;
        this.contactNumber = contactNumber;
        this.age = age;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getMyId() {
        return myId;
    }

    public void setMyId(Long myId) {
        this.myId = myId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMyAddress() {
        return myAddress;
    }

    public void setMyAddress(String myAddress) {
        this.myAddress = myAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.myId);
        hash = 79 * hash + Objects.hashCode(this.firstName);
        hash = 79 * hash + Objects.hashCode(this.lastName);
        hash = 79 * hash + Objects.hashCode(this.myAddress);
        hash = 79 * hash + Objects.hashCode(this.contactNumber);
        hash = 79 * hash + Objects.hashCode(this.age);
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.role);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.myAddress, other.myAddress)) {
            return false;
        }
        if (!Objects.equals(this.contactNumber, other.contactNumber)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        return Objects.equals(this.myId, other.myId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account {");
        sb.append(" myId=").append(myId);
        sb.append(", firstName=").append(firstName).append('\'');
        sb.append(", lastName=").append(lastName).append('\'');
        sb.append(", myAddress=").append(myAddress).append('\'');
        sb.append(", contactNumber=").append(contactNumber).append('\'');
        sb.append(", age=").append(age).append('\'');
        sb.append(", username=").append(username).append('\'');
        sb.append(", password=").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }

}
