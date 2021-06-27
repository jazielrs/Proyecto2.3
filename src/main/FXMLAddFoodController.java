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
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLAddFoodController implements Initializable {

    BST bstFood;

    @FXML
    private Button btnAddFood;
    @FXML
    private Button btnClean;
    @FXML
    private TextField txtFieldFoodID;
    @FXML
    private TextField txtFieldFoodName;
    @FXML
    private TextField txtFieldFoodPrice;
    @FXML
    private ComboBox comboRestaurantID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bstFood = util.Utility.getBstFood();
        txtFieldFoodID.setText(String.valueOf(domain.Food.getAutoID() + 1));

        ObservableList<String> restaurantID = FXCollections.observableArrayList("MCDonald's", "Pizza Hut", "Taco Bell");
        comboRestaurantID.setItems(restaurantID);
    }

    @FXML
    private void btnAddFood(ActionEvent event) throws TreeException, IOException {

        Files file = new Files();
        int count = 0;

        if (!"".equals(this.txtFieldFoodID.getText()) && !"".equals(this.txtFieldFoodName.getText())
                && !"".equals(this.txtFieldFoodPrice.getText()) && this.comboRestaurantID.getValue() != null) {

            Food food = new Food(this.txtFieldFoodName.getText(), Double.parseDouble(this.txtFieldFoodPrice.getText()), 0);

            if (!bstFood.isEmpty()) {

                boolean existFood = false;

                String treeData[] = new String[count];
                treeData = bstFood.preOrderAux().split("\n");

                for (int i = 0; i < treeData.length; i++) {

                    if (util.Utility.equals(treeData[i], food.toString())) {
                        existFood = true;
                        int id = food.getAutoID();
                        food.setAutoID(id - 1);
                    }
                }
                if (!existFood) {
                    bstFood.add(food);
                    file.addFood(food);
                    ++count;
                    clean();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Alert");
                    alert.setContentText("New food added");
                    alert.showAndWait();
                    txtFieldFoodID.setText(String.valueOf(domain.Food.getAutoID() + 1));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Alert");
                    alert.setContentText("Food already exists");
                    alert.showAndWait();
                }
            } else {
                bstFood.add(food);
                file.addFood(food);
                ++count;
                clean();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Alert");
                alert.setContentText("New food added");
                alert.showAndWait();
                txtFieldFoodID.setText(String.valueOf(domain.Food.getAutoID() + 1));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Please, fill all the spaces");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnClean(ActionEvent event) {
        this.txtFieldFoodName.setText("");
        this.txtFieldFoodPrice.setText("");
        this.comboRestaurantID.setValue("");
    }

    public void clean() {
        this.txtFieldFoodName.setText("");
        this.txtFieldFoodPrice.setText("");
        this.comboRestaurantID.setValue("");
    }

    @FXML
    private void onKeyPressedType(KeyEvent event) {

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("\\d?")) {
                return change;
            }
            return null;
        } ;
         this.txtFieldFoodPrice.setTextFormatter(new TextFormatter<String>(filter));  
    }


    @FXML
    private void textValidation(KeyEvent event) {
        
        txtFieldFoodName.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            txtFieldFoodName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
    }
            

}