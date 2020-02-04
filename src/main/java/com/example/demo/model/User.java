package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


@Entity
public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;

    @Column(name = "age", nullable = true, unique = true)
    private Integer age;
    public Integer getId(){
        return id;
    } 

    public void setId(Integer id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String setEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}