package com.project.roku.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    // define fields
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String userName;


    // define constructors
    public User(){
    }

    public User(String userName) {
        this.userName = userName;
    }
    public User(int id){
        this.id = id;
    }
    // getters setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // to string method

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
