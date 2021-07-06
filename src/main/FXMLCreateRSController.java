/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Files;
import domain.Restaurant;
import domain.Supermarket;
import domain.graph.AdjacencyListGraph;
import domain.graph.GraphException;
import domain.linkedList.ListException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cristopher.za
 */
public class FXMLCreateRSController implements Initializable {
    AdjacencyListGraph list = util.Utility.getGraphList();
    @FXML
    private TextField nameObject;
    @FXML
    private TextField locationObject;
    @FXML
    private Button addButton;
    @FXML
    private CheckBox checkRestaurant;
    @FXML
    private CheckBox checkSuperMarket;
    Files files = new Files();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void add(ActionEvent event) throws GraphException, ListException, IOException {
        if(checkRestaurant.isSelected() && (checkSuperMarket.isSelected()==false)){ //Ingresar datos de selección.
            list.addVertex(new Restaurant(nameObject.getText(), locationObject.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Se ha agregado un nuevo Restaurante");
            alert.show();
            files.addRestaurant(new Restaurant(nameObject.getText(), locationObject.getText()));
        }
        if(checkSuperMarket.isSelected() && (checkRestaurant.isSelected()==false)){
            list.addVertex(new Supermarket(nameObject.getText(), locationObject.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Se ha agregado un nuevo Super Mercado");
            alert.show();
            files.addMarket(new Supermarket(nameObject.getText(), locationObject.getText()));
        }
        nameObject.setText("");
        locationObject.setText("");
        
    }
    
}
