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
public class Supermarket {
    
    private static int autoID;
    private String name;
    private String location;

    public Supermarket(String name, String location) {
        this.autoID = ++autoID;
        this.name = name;
        this.location = location;
    }

    public static int getAutoID() {
        return autoID;
    }

    public static void setAutoID(int autoID) {
        Supermarket.autoID = autoID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Supermarket{" + "autoID=" + autoID + ", name=" + name + ", location=" + location + '}';
    }  
}
