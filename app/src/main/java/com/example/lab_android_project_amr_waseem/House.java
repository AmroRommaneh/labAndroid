package com.example.lab_android_project_amr_waseem;

public class House {
    private int id;
    private int Age ;
    private String City;
    private double size ;
    private int numberOfRooms ;
    private double price ;
    private String status;
    private boolean balcony;
    private boolean garden ;

    public House(int id, int age, String city, double size, int numberOfRooms, double price, String status, boolean balcony, boolean garden) {
        this.id = id;
        Age = age;
        City = city;
        this.size = size;
        this.numberOfRooms = numberOfRooms;
        this.price = price;
        this.status = status;
        this.balcony = balcony;
        this.garden = garden;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }



    public House() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", Age=" + Age +
                ", City='" + City + '\'' +
                ", size=" + size +
                ", numberOfRooms=" + numberOfRooms +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", balcony=" + balcony +
                ", garden=" + garden +
                '}';
    }
}
