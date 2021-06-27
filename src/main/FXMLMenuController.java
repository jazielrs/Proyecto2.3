/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Files;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Jaziel
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Files files = new Files();        
        try {
            files.loadFoodsBSTrees("foodFile.txt", util.Utility.getBstFood());
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    

    @FXML
    private void addFood(ActionEvent event) {
        loadPage("FXMLAddFood");
    }

    @FXML
    private void modifyFood(ActionEvent event) {
    }

    @FXML
    private void eraseFood(ActionEvent event) {
        loadPage("FXMLRemoveFood");
    }

    @FXML
    private void showFood(ActionEvent event) {
    }
    
    private void loadPage(String page) {//Realiza una carga de una pagina generica
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bp.setCenter(root);//Realiza la asignacion de la nueva pagina al menu principal
    }
}
    

