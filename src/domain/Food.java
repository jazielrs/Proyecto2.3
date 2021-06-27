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
public class Food {
    
    private static int autoID;
    private String name;
    private double price;
    private int restaurantID;

    public Food(String name, double price, int restaurantID) {
        this.autoID = ++autoID;
        this.name = name;
        this.price = price;
        this.restaurantID = restaurantID;
    }

    public static int getAutoID() {
        return autoID;
    }

    public static void setAutoID(int autoID) {
        Food.autoID = autoID;
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

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    @Override
    public String toString() {
        return "Food{" + "autoID=" + autoID + ",name=" + name + ",price=" + price + ",restaurantID=" + restaurantID + '}';
    }  
}
