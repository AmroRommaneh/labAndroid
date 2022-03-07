package com.example.lab_android_project_amr_waseem;

public class Tenant {

    private  String first_name;
    private  String second_name;
    private String Gender;
    private  String Email;
    private String password;
    private String Nationality;
    private double GMS;
    private String Occupation;
    private int familySize;
    private String CRC;
    private  String City;
    private String phone;

    public Tenant(String first_name, String second_name, String gender, String email, String password, String nationality, double GMS, String occupation, int familySize, String CRC, String city, String phone) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.Gender = gender;
        this.Email = email;
        this.password = password;
        this.Nationality = nationality;
        this.GMS = GMS;
        this.Occupation = occupation;
        this.familySize = familySize;
        this.CRC = CRC;
        this.City = city;
        this.phone = phone;
    }

    public Tenant() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public double getGMS() {
        return GMS;
    }

    public void setGMS(double GMS) {
        this.GMS = GMS;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public String getCRC() {
        return CRC;
    }

    public void setCRC(String CRC) {
        this.CRC = CRC;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
