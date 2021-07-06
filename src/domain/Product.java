/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author PaoVega
 */
public class Product {
    
    private static int autoID;
    private String name;
    private double price;
    private int supermarketID;

    public Product(String name, double price, int supermarketID) {
        this.autoID = ++autoID;
        this.name = name;
        this.price = price;
        this.supermarketID = supermarketID;
    }

    public static int getAutoID() {
        return autoID;
    }

    public static void setAutoID(int autoID) {
        Product.autoID = autoID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSupermarketID() {
        return supermarketID;
    }

    public void setSupermarketID(int supermarketID) {
        this.supermarketID = supermarketID;
    }

    @Override
    public String toString() {
        return autoID + "," + name + "," + price + "," + supermarketID;
    } 
}
