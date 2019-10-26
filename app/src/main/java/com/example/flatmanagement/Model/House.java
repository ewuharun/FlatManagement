package com.example.flatmanagement.Model;

public class House {
    private String houseName;
    private int totalFlat;
    private String managerName;
    private String managerContact;

    public House(String houseName, int totalFlat, String managerName, String managerContact) {
        this.houseName = houseName;
        this.totalFlat = totalFlat;
        this.managerName = managerName;
        this.managerContact = managerContact;
    }

    public String getHouseName() {
        return houseName;
    }

    public int getTotalFlat() {
        return totalFlat;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerContact() {
        return managerContact;
    }
}
