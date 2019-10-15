package com.example.flatmanagement;

public class Member {
    private String name,phone,email,permanent_address;



    public Member(String name, String phone, String email, String permanent_address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.permanent_address = permanent_address;

    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

}
