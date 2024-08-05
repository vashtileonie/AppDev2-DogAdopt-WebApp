package com.doggo.dogadopt.model;


import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Dog implements Serializable {


    private Long id;
    private byte[] photo;
    private String name;
    private String breed;
    private int age;
    private Date doa;
    private String personality;
    private String status;
    private String gender;

    public Dog() {
    }

    public Dog(Long id, byte[] photo, String name, String breed, int age, Date doa, String personality, String status, String gender) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.doa = doa;
        this.personality = personality;
        this.status = status;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDoa() {
        return doa;
    }

    public void setDoa(Date doa) {
        this.doa = doa;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.photo);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.breed);
        hash = 79 * hash + Objects.hashCode(this.age);
        hash = 79 * hash + Objects.hashCode(this.doa);
        hash = 79 * hash + Objects.hashCode(this.personality);
        hash = 79 * hash + Objects.hashCode(this.status);
        hash = 79 * hash + Objects.hashCode(this.gender);
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
        final Dog other = (Dog) obj;
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.breed, other.breed)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        if (!Objects.equals(this.doa, other.doa)) {
            return false;
        }
        if (!Objects.equals(this.personality, other.personality)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dog {");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", photo='").append(photo).append('\'');
        sb.append(", breed=").append(breed);
        sb.append(", age=").append(age);
        sb.append(", date of arrival=").append(doa);
        sb.append(", personality=").append(personality);
        sb.append(", gender=").append(gender);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }

}