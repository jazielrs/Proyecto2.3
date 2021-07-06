/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.BST.BST;
import domain.BST.TreeException;
import domain.Files;
import domain.Product;
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

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLAddProductController implements Initializable {

    BST bstProduct;

    @FXML
    private Button btnClean;
    @FXML
    private Button btnAddProduct;
    @FXML
    private TextField txtFieldProductID;
    @FXML
    private TextField txtFieldProductName;
    @FXML
    private TextField txtFieldProductPrice;
    @FXML
    private ComboBox comboSupermarketID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bstProduct = util.Utility.getBstProduct();

        txtFieldProductID.setText(String.valueOf(domain.Product.getAutoID() + 1));

        ObservableList<String> restaurantID = FXCollections.observableArrayList("Walmart", "Pali", "Perimercados");
        comboSupermarketID.setItems(restaurantID);
    }

    @FXML
    private void btnAddProduct(ActionEvent event) {
        try {
            Files file = new Files();
            int count = 0;

            if (!"".equals(this.txtFieldProductID.getText()) && !"".equals(this.txtFieldProductName.getText())
                    && !"".equals(this.txtFieldProductPrice.getText()) && this.comboSupermarketID.getValue() != null) {

                Product product = new Product(this.txtFieldProductName.getText(), Double.parseDouble(this.txtFieldProductPrice.getText()), 0);

                if (!bstProduct.isEmpty()) {

                    boolean existProduct = false;

                    String treeData[] = new String[count];
                    treeData = bstProduct.preOrderAux().split("\n");

                    for (int i = 0; i < treeData.length; i++) {

                        if (util.Utility.equals(treeData[i], product.toString())) {
                            existProduct = true;
                            int id = product.getAutoID();
                            product.setAutoID(id - 1);
                        }
                    }
                    if (!existProduct) {
                        bstProduct.add(product);
                        file.addProduct(product);
                        ++count;
                        clean();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setHeaderText("Alert");
                        alert.setContentText("New product added");
                        alert.showAndWait();
                        txtFieldProductID.setText(String.valueOf(domain.Product.getAutoID() + 1));
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setHeaderText("Alert");
                        alert.setContentText("Product already exists");
                        alert.showAndWait();
                    }

                } else {
                    bstProduct.add(product);
                    file.addProduct(product);
                    ++count;
                    clean();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Alert");
                    alert.setContentText("New product added");
                    alert.showAndWait();
                    txtFieldProductID.setText(String.valueOf(domain.Product.getAutoID() + 1));
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Alert");
                alert.setContentText("Please, fill all the spaces");
                alert.showAndWait();
            }
        } catch (TreeException ex) {
            Logger.getLogger(FXMLAddProductController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnClean(ActionEvent event) {
        this.txtFieldProductName.setText("");
        this.txtFieldProductPrice.setText("");
        this.comboSupermarketID.setValue("");
    }

    public void clean() {
        this.txtFieldProductName.setText("");
        this.txtFieldProductPrice.setText("");
        this.comboSupermarketID.setValue("");
    }

    @FXML
    private void onKeyPressedType(KeyEvent event) {

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("\\d?")) {
                return change;
            }
            return null;
        };
        this.txtFieldProductPrice.setTextFormatter(new TextFormatter<String>(filter));
    }

    @FXML
    private void textValidation(KeyEvent event) {

        txtFieldProductName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtFieldProductName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

}
