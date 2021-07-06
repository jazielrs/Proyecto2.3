/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.BST.BST;
import domain.BST.TreeException;
import domain.Files;
import domain.Food;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLRemoveFoodController implements Initializable {

    BST bstFood;

    @FXML
    private Button btnRemoveFood;
    @FXML
    private ComboBox foodCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bstFood = util.Utility.getBstFood();
            
            ObservableList<String> list = FXCollections.observableArrayList(loadComboBox());
            foodCombo.setItems(list);
        } catch (TreeException ex) {
            Logger.getLogger(FXMLRemoveFoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRemoveFood(ActionEvent event) throws TreeException, IOException {

        Files file = new Files();
        String comboSelection = (String) this.foodCombo.getSelectionModel().getSelectedItem();
        Food food;
        Food newFood;
        String name = "";
        Double price = 0.0;
        int restaurantID = 0;
        boolean exist = false;

        if (foodCombo.getValue()!=null) {

            String treeData[] = new String[bstFood.size()];
            String treeData1[] = new String[50];
            treeData = bstFood.preOrderAux().split("\n");

            for (int i = 0; i < bstFood.size(); i++) {
                treeData1 = treeData[i].split(",");
                name = treeData1[1];
                price = Double.parseDouble(treeData1[2]);
                restaurantID = Integer.parseInt(treeData1[3]);

                if (comboSelection.equalsIgnoreCase(name)) {
                    food = new Food(name, price, restaurantID);
                    if (bstFood.contains(food)) {
                        bstFood.remove(food);
                        exist = true;
                    }
                }
            }
            if (exist) {
                file.modifyFood(bstFood);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erase Food");
                alert.setHeaderText("Success");
                alert.setContentText("Food successfully removed");
                alert.showAndWait();

                ObservableList<String> list = FXCollections.observableArrayList(loadComboBox());
                foodCombo.setItems(list);

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Alert");
                alert.setContentText("Food data doesn't exist");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Please, select a food");
            alert.showAndWait();
        }
    }

    @FXML
    private void foodCombo(ActionEvent event) {
        this.btnRemoveFood.setVisible(true);
    }


    //Carga el nombre de la comidas en el combobox
    private String[] loadComboBox() throws TreeException {
        String[] a = new String[bstFood.size()];

        String treeData[] = new String[bstFood.size()];
        String treeData1[] = new String[50];
        treeData = bstFood.preOrderAux().split("\n");

        for (int i = 0; i < treeData.length; i++) {
            treeData1 = treeData[i].split(",");
            treeData1 = treeData1[1].split(",");
            a[i] = treeData1[0];
        }
        return a;
    }

}
