/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.BST.BST;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jaziel
 */
public class FXMLMenuController implements Initializable {

    BST bstFood;
    BST bstProduct;

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private AnchorPane apCenter;
    @FXML
    private Menu Home;
    @FXML
    private Menu Exit;
    @FXML
    private MenuItem managePlaces;
    @FXML
    private MenuItem maintenance;
    @FXML
    private MenuItem createRS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Files files = new Files();
        bstFood = util.Utility.getBstFood();
        bstProduct = util.Utility.getBstProduct();
        
        try {
            files.loadFoodsBSTrees("foodFile.txt", bstFood);
            files.loadProductsBSTrees("productFile.txt", bstProduct);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Home(ActionEvent event) {
        this.bp.setCenter(apCenter);
    }
    
    @FXML
    private void managePlaces(ActionEvent event) {
        loadPage("FXMLGraphPlaces");
    }

    @FXML
    private void addFood(ActionEvent event) {
        loadPage("FXMLAddFood");
    }

    @FXML
    private void modifyFood(ActionEvent event) {
        loadPage("FXMLModifyFood");
    }

    @FXML
    private void eraseFood(ActionEvent event) {
        loadPage("FXMLRemoveFood");
    }

    @FXML
    private void showFood(ActionEvent event) {
        loadPage("FXMLShowFood");
    }
    
    @FXML
    private void addProduct(ActionEvent event) {
        loadPage("FXMLAddProduct");
    }

    @FXML
    private void modifyProduct(ActionEvent event) {
        loadPage("FXMLModifyProduct");
    }

    @FXML
    private void eraseProduct(ActionEvent event) {
        loadPage("FXMLRemoveProduct");
    }

    @FXML
    private void showProduct(ActionEvent event) {
        loadPage("FXMLShowProduct");
    }

    @FXML
    private void Exit(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
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
   

    @FXML
    private void maintenance(ActionEvent event) {
        loadPage("FXMLMaintenance");
    }

    @FXML
    private void createRS(ActionEvent event) {
        loadPage("FXMLCreateRS");
    }
  
}
