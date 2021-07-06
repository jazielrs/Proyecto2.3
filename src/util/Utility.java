/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.BST.BST;
import domain.Food;
import domain.Product;
import domain.Restaurant;
import domain.Supermarket;
import domain.graph.AdjacencyListGraph;
import domain.linkedList.CircularDoublyLinkedList;
import domain.linkedList.CircularLinkedList;
import domain.linkedList.ListException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Profesor Lic. Gilberth Chaves Avila
 */
public class Utility {

    private static boolean loginState = true;
    private static CircularLinkedList circularList = new CircularLinkedList();
    private static CircularDoublyLinkedList circularDoublyList = new CircularDoublyLinkedList();
    private static BST bstFood = new BST();
    private static BST bstProduct = new BST();
//////////////////////////////////////////////////////////////////////////////7 Cris
    private static AdjacencyListGraph graphList = new AdjacencyListGraph(10);

    public static String wkIst(Object a) {
        if (a instanceof Restaurant) {
            return "restaurant";
        }
        if (a instanceof Supermarket) {
            return "supermarket";
        }
        return "unknown";
    }

    public static AdjacencyListGraph getGraphList() {
        return graphList;
    }

    //////////////////////////////////////////////////////////
    public static BST getBstProduct() {
        return bstProduct;
    }

    public static void setBstProduct(BST bstProduct) {
        Utility.bstProduct = bstProduct;
    }

    public static BST getBstFood() {
        return bstFood;
    }

    public static void setBstFood(BST bstFood) {
        Utility.bstFood = bstFood;
    }

    public static CircularLinkedList getCircularList() {
        return circularList;
    }

    public static void setCircularList(CircularLinkedList list) {
        if (Utility.circularList.isEmpty()) {
            Utility.circularList = list;
        } else {
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.circularList.contains(list.getNode(i).data)) {
                        Utility.circularList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static CircularDoublyLinkedList getCircularDoublyList() {
        return circularDoublyList;
    }

    public static void setCircularDoublyList(CircularDoublyLinkedList list) {
        if (Utility.circularDoublyList.isEmpty()) {
            Utility.circularDoublyList = list;
        } else {
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.circularDoublyList.contains(list.getNode(i).data)) {
                        Utility.circularDoublyList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    public static CircularDoublyLinkedList getEnrollmentList() {
//        return enrollmentList;
//    }
    public static void setEnrollmentList(CircularDoublyLinkedList list) {
        if (Utility.circularDoublyList.isEmpty()) {
            Utility.circularDoublyList = list;
        } else {
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.circularDoublyList.contains(list.getNode(i).data)) {
                        Utility.circularDoublyList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int random() {
        return 1 + (int) Math.floor(Math.random() * 99);
    }

    public static int random(int bound) {
        //return 1+random.nextInt(bound);
        return 1 + (int) Math.floor(Math.random() * bound);
    }

    public static int random(int boundLow, int boundMax) {
        //return 1+random.nextInt(bound);
        return (int) ((Math.random() * (boundMax + 1 - boundLow)) + boundLow);
    }

    public static String format(double value) {
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }

    public static String $format(double value) {
        return new DecimalFormat("$###,###,###,###.##")
                .format(value);
    }

    public static String perFormat(double value) {
        //#,##0.00 '%'
        return new DecimalFormat("#,##0.00'%'")
                .format(value);
    }

    //Puede funcionar para estudiante y curso
    public static boolean equals(Object a, Object b) {

        switch (instanceOf(a, b)) {//Devuelve String
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x.equals(y);
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                // return s1.compareTo(s2)==0; Op1
                return s1.equalsIgnoreCase(s2); //Op2
            case "food":
                Food f1 = (Food) a;
                Food f2 = (Food) b;
                return f1.toString().equals(f2.toString());
            case "product":
                Product p1 = (Product) a;
                Product p2 = (Product) b;
                return p1.toString().equals(p2.toString());
            case "restaurant":
                Restaurant r1 = (Restaurant) a;
                Restaurant r2 = (Restaurant) b;
                return r1.getName().equalsIgnoreCase(r2.getName()) && r1.getLocation().equalsIgnoreCase(r2.getLocation());
            case "supermarket":
                Supermarket sp1 = (Supermarket) a;
                Supermarket sp2 = (Supermarket) b;
                return sp1.getName().equalsIgnoreCase(sp2.getName()) && sp1.getLocation().equalsIgnoreCase(sp2.getLocation());
        }
        return false;//En cualquier otro caso retorne false;
    }

    private static String instanceOf(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return "integer";
        }
        if (a instanceof String && b instanceof String) {
            return "string";
        }
        if (a instanceof Food && b instanceof Food) {
            return "food";
        }
        if (a instanceof Product && b instanceof Product) {
            return "product";
        }
        if (a instanceof Restaurant && b instanceof Restaurant) {
            return "restaurant";
        }
        if (a instanceof Supermarket && b instanceof Supermarket) {
            return "supermarket";
        }
        return "unknown";
    }

    public static boolean lessT(Object a, Object b) {
        switch (instanceOf(a, b)) {//Devuelve String
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x < y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) < 0;//Primero mayor que segundo
            case "food":
                Food f1 = (Food) a;
                Food f2 = (Food) b;
                return f1.toString().compareTo(f2.toString()) < 0;
            case "product":
                Product p1 = (Product) a;
                Product p2 = (Product) b;
                return p1.toString().compareTo(p2.toString()) < 0;

        }
        return false;//En cualquier otro caso retorne false;
    }

    public static boolean greaterT(Object a, Object b) {
        switch (instanceOf(a, b)) {//Devuelve String
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x > y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) > 0;//Primero mayor que segundo
            case "food":
                Food f1 = (Food) a;
                Food f2 = (Food) b;
                return f1.toString().compareTo(f2.toString()) > 0;
            case "product":
                Product p1 = (Product) a;
                Product p2 = (Product) b;
                return p1.toString().compareTo(p2.toString()) > 0;
        }
        return false;//En cualquier otro caso retorne false;
    }

    public static Date dateFormat(String date) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.parse(date);

    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static int months(String month) {
        switch (month.toLowerCase()) {
            case "jan":
                return 1;
            case "feb":
                return 2;
            case "mar":
                return 3;
            case "abr":
                return 4;
            case "may":
                return 5;
            case "jun":
                return 6;
            case "jul":
                return 7;
            case "aug":
                return 8;
            case "sep":
                return 9;
            case "nov":
                return 11;
            case "dec":
                return 12;
            case "oct":
                return 10;
        }
        return -1;
    }

    //Puede funcionar para estudiante y curso
    public static String dateFormat(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static boolean getStateLogin() {
        return loginState;
    }

    public static void setStateLogin(boolean state) {
        loginState = state;
    }
}
