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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLModifyProductController implements Initializable {

    BST bstProduct;

    @FXML
    private Button btnClean;
    @FXML
    private Text txtPrice;
    @FXML
    private Text txtProductID;
    @FXML
    private Text txtProductName;
    @FXML
    private Text txtSupermarket;
    @FXML
    private Button btnModifyProduct;
    @FXML
    private TextField txtFieldProductID;
    @FXML
    private TextField txtFieldProductName;
    @FXML
    private ComboBox comboSupermarket;
    @FXML
    private ComboBox comboModifyProduct;
    @FXML
    private TextField txtFieldProductPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bstProduct = util.Utility.getBstProduct();

            ObservableList<String> list = FXCollections.observableArrayList(loadComboBox());
            comboModifyProduct.setItems(list);

            ObservableList<String> restaurantID = FXCollections.observableArrayList("Walmart", "Pali", "Perimercados");
            comboSupermarket.setItems(restaurantID);
        } catch (TreeException ex) {
            Logger.getLogger(FXMLModifyProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnModifyProduct(ActionEvent event) {
        try {
            Files file = new Files();
            String comboSelection = (String) this.comboModifyProduct.getSelectionModel().getSelectedItem();
            Product product;
            Product newProduct;
            String name = "";
            Double price = 0.0;
            int supermarketID = 0;
            boolean exist = false;

            if (!"".equals(this.txtFieldProductID.getText()) && !"".equals(this.txtFieldProductName.getText())
                    && !"".equals(this.txtFieldProductPrice.getText()) && this.comboSupermarket.getValue() != null
                    && comboModifyProduct.getValue() != null) {

                String treeData[] = new String[bstProduct.size()];
                String treeData1[] = new String[50];
                treeData = bstProduct.preOrderAux().split("\n");

                for (int i = 0; i < bstProduct.size(); i++) {
                    treeData1 = treeData[i].split(",");
                    name = treeData1[1];
                    price = Double.parseDouble(treeData1[2]);
                    supermarketID = Integer.parseInt(treeData1[3]);

                    if (comboSelection.equalsIgnoreCase(name)) {
                        product = new Product(name, price, supermarketID);
                        if (bstProduct.contains(product)) {
                            bstProduct.remove(product);
                            newProduct = new Product(this.txtFieldProductName.getText(), Double.parseDouble(this.txtFieldProductPrice.getText()), 0);
                            bstProduct.add(newProduct);
                            exist = true;
                        }
                    }
                }
                if (exist) {
                    file.modifyProduct(bstProduct);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Modify Product");
                    alert.setHeaderText("Success");
                    alert.setContentText("Product successfully modified");
                    alert.showAndWait();

                    ObservableList<String> list = FXCollections.observableArrayList(loadComboBox());
                    comboModifyProduct.setItems(list);

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Alert");
                    alert.setContentText("Product data doesn't exist");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Alert");
                alert.setContentText("Please, fill all the spaces");
                alert.showAndWait();
            }
        } catch (TreeException | IOException ex) {
            Logger.getLogger(FXMLModifyProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void comboModifyProduct(ActionEvent event) {
        try {
            String comboSelection = (String) this.comboModifyProduct.getSelectionModel().getSelectedItem();
            String name = "";
            Double price = 0.0;

            //Se visibilizan los componentes
            this.txtFieldProductID.setVisible(true);
            this.txtFieldProductName.setVisible(true);
            this.txtFieldProductPrice.setVisible(true);
            this.comboSupermarket.setVisible(true);
            this.txtSupermarket.setVisible(true);
            this.txtProductID.setVisible(true);
            this.txtProductName.setVisible(true);
            this.txtPrice.setVisible(true);
            this.btnModifyProduct.setVisible(true);
            this.btnClean.setVisible(true);

            //Se añaden los datos de las comida seleccionada al los textfield's
            String treeData[] = new String[bstProduct.size()];
            String treeData1[] = new String[50];
            treeData = bstProduct.preOrderAux().split("\n");

            for (int i = 0; i < treeData.length; i++) {
                treeData1 = treeData[i].split(",");

                name = treeData1[1];
                price = Double.parseDouble(treeData1[2]);
                try {
                    if (comboSelection.equalsIgnoreCase(name)) {
                        this.txtFieldProductID.setText(String.valueOf(i + 1));
                        this.txtFieldProductName.setText(name);
                        this.txtFieldProductPrice.setText(String.valueOf(price));
                    }
                } catch (NullPointerException ex) {
                }
            }
        } catch (TreeException ex) {
            Logger.getLogger(FXMLModifyProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnClean(ActionEvent event) {
        this.txtFieldProductName.setText("");
        this.txtFieldProductPrice.setText("");
        this.comboSupermarket.setValue("");
    }

    public void clean() {
        this.txtFieldProductName.setText("");
        this.txtFieldProductPrice.setText("");
        this.comboSupermarket.setValue("");
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
        this.txtFieldProductPrice.setTextFormatter(new TextFormatter<String>(filter));
    }

    @FXML
    private void textValidation(KeyEvent event) {//Validación para ingresar solo texto

        txtFieldProductName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtFieldProductName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    //Carga el nombre de la comidas en el combobox
    private String[] loadComboBox() throws TreeException {
        String[] a = new String[bstProduct.size()];

        String treeData[] = new String[bstProduct.size()];
        String treeData1[] = new String[50];
        treeData = bstProduct.preOrderAux().split("\n");

        for (int i = 0; i < treeData.length; i++) {
            treeData1 = treeData[i].split(",");
            treeData1 = treeData1[1].split(",");
            a[i] = treeData1[0];
        }
        return a;
    }

    

}
