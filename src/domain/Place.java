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
public class Place {

    private static int autoID;
    private String name;

    public Place(String name) {
        this.autoID = ++autoID;
        this.name = name;
    }

    public static int getAutoID() {
        return autoID;
    }

    public static void setAutoID(int autoID) {
        Place.autoID = autoID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Place{" + "autoID=" + autoID + ", name=" + name + '}';
    }   
}
