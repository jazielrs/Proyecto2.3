package domain.graph;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jaziel
 */
public class Place {

    private int id;
    private String name;
    private static int idCount;

    public Place(String name) {
        this.id = ++idCount;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        Place.idCount = idCount;
    }

    @Override
    public String toString() {
        //return "Place{" + "id=" + id + ", name=" + name + '}';
        return name;
    }
    
}
