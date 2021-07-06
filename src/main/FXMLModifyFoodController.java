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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLModifyFoodController implements Initializable {

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
    @FXML
    private ComboBox comboModifyFood;
    @FXML
    private Text txtFoodID;
    @FXML
    private Text txtFoodName;
    @FXML
    private Text txtPrice;
    @FXML
    private Text txtRestaurantID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bstFood = util.Utility.getBstFood();

            ObservableList<String> list = FXCollections.observableArrayList(loadComboBox());
            comboModifyFood.setItems(list);

            ObservableList<String> restaurantID = FXCollections.observableArrayList("MCDonald's", "Pizza Hut", "Taco Bell");
            comboRestaurantID.setItems(restaurantID);
        } catch (TreeException ex) {
            Logger.getLogger(FXMLModifyFoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddFood(ActionEvent event) throws TreeException, IOException {

        Files file = new Files();
        String comboSelection = (String) this.comboModifyFood.getSelectionModel().getSelectedItem();
        Food food;
        Food newFood;
        String name = "";
        Double price = 0.0;
        int restaurantID = 0;
        boolean exist = false;

        if (!"".equals(this.txtFieldFoodID.getText()) && !"".equals(this.txtFieldFoodName.getText())
                && !"".equals(this.txtFieldFoodPrice.getText()) && this.comboRestaurantID.getValue() != null
                && comboModifyFood.getValue()!=null) {

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
                        newFood = new Food(this.txtFieldFoodName.getText(), Double.parseDouble(this.txtFieldFoodPrice.getText()), 0);
                        bstFood.add(newFood);
                        exist = true;
                    }
                }
            }
            if (exist) {
                file.modifyFood(bstFood);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modify Food");
                alert.setHeaderText("Success");
                alert.setContentText("Food successfully modified");
                alert.showAndWait();

                ObservableList<String> list = FXCollections.observableArrayList(loadComboBox());
                comboModifyFood.setItems(list);

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
    private void onKeyPressedType(KeyEvent event) {//Validación para ingresar solo números

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("\\d?")) {
                return change;
            }
            return null;
        };
        this.txtFieldFoodPrice.setTextFormatter(new TextFormatter<String>(filter));
    }

    @FXML
    private void textValidation(KeyEvent event) {//Validación para ingresar solo texto

        txtFieldFoodName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtFieldFoodName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    @FXML
    private void comboModifyFood(ActionEvent event) {
        try {
            String comboSelection = (String) this.comboModifyFood.getSelectionModel().getSelectedItem();
            String name = "";
            Double price = 0.0;

            //Se visibilizan los componentes
            this.txtFieldFoodID.setVisible(true);
            this.txtFieldFoodName.setVisible(true);
            this.txtFieldFoodPrice.setVisible(true);
            this.comboRestaurantID.setVisible(true);
            this.txtRestaurantID.setVisible(true);
            this.txtFoodID.setVisible(true);
            this.txtFoodName.setVisible(true);
            this.txtPrice.setVisible(true);
            this.btnAddFood.setVisible(true);
            this.btnClean.setVisible(true);

            //Se añaden los datos de las comida seleccionada al los textfield's
            String treeData[] = new String[bstFood.size()];
            String treeData1[] = new String[50];
            treeData = bstFood.preOrderAux().split("\n");

            for (int i = 0; i < treeData.length; i++) {
                treeData1 = treeData[i].split(",");

                name = treeData1[1];
                price = Double.parseDouble(treeData1[2]);
                try {
                    if (comboSelection.equalsIgnoreCase(name)) {
                        this.txtFieldFoodID.setText(String.valueOf(i + 1));
                        this.txtFieldFoodName.setText(name);
                        this.txtFieldFoodPrice.setText(String.valueOf(price));
                    }
                } catch (NullPointerException ex) {
                }
            }
        } catch (TreeException ex) {
            Logger.getLogger(FXMLModifyFoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
