package com.plproject.cemsystem;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    
    
    private String name;
    private int age;
    private String password;
        
    private static final AtomicInteger staticid = new AtomicInteger(1000);

    public User(String name, int age, String password){
             
        staticid.incrementAndGet();
        this.name = name;
        this.age = age;
        this.password = password;
    }
        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 
    
    public void login() {
        
    }

    public void logout() {
        
    } 
}
