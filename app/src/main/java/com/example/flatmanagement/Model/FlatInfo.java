package com.example.flatmanagement.Model;

public class FlatInfo {
    private String flat_no;
    private String house_no;
    private int flat_rent;
    private int gass_bill;
    private int electric_bill;
    private int washman_bill;
    private int service_charge;

    public FlatInfo(String flat_no, String house_no, int flat_rent, int gass_bill, int electric_bill, int washman_bill, int service_charge) {
        this.flat_no = flat_no;
        this.house_no = house_no;
        this.flat_rent = flat_rent;
        this.gass_bill = gass_bill;
        this.electric_bill = electric_bill;
        this.washman_bill = washman_bill;
        this.service_charge = service_charge;
    }

    public String getFlat_no() {
        return flat_no;
    }

    public String getHouse_no() {
        return house_no;
    }

    public int getFlat_rent() {
        return flat_rent;
    }

    public int getGass_bill() {
        return gass_bill;
    }

    public int getElectric_bill() {
        return electric_bill;
    }

    public int getWashman_bill() {
        return washman_bill;
    }

    public int getService_charge() {
        return service_charge;
    }
}
