package com.example.demo.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import com.example.demo.model.Phone;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private Date dateOfBirth;

    @Column(name = "age", nullable = true, unique = true)
    private Integer age;
    @OneToMany
    private List<Phone> phones = new ArrayList<Phone>();

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt = new Date(); // initialize created date

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    private Boolean active;

    private String roles = "";

    private String permissions = "";
    private String password;

    // public Integer getId() {
    // return id;
    // }

    // public void setId(Integer id) {
    // this.id = id;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public String getEmail() {
    // return email;
    // }

    // public void setEmail(String email) {
    // this.email = email;
    // }

    // public Integer getAge() {
    // return age;
    // }

    // public void setAge(Integer age) {
    // this.age = age;
    // }

    // public Date getDateOfBirth() {
    // return dateOfBirth;

    // }

    // public void setDateOfBirth(Date dateOfBirth) {
    // this.dateOfBirth = dateOfBirth;
    // }

    // public Date getCreatedAt(){
    // return createdAt;
    // }

    // public void setCreatedAt(){
    // this.createdAt = new Date();
    // }

    // public Date getUpdatedAt(){
    // return updatedAt;
    // }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = new Date();
    }

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}