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
public class FXMLRemoveProductController implements Initializable {

    BST bstProduct;

    @FXML
    private Button btnRemoveProduct;
    @FXML
    private ComboBox productCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            bstProduct = util.Utility.getBstProduct();

            ObservableList<String> list = FXCollections.observableArrayList(loadComboBox());
            productCombo.setItems(list);
        } catch (TreeException ex) {
            Logger.getLogger(FXMLRemoveProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnRemoveProduct(ActionEvent event) {
        try {
            Files file = new Files();
            String comboSelection = (String) this.productCombo.getSelectionModel().getSelectedItem();
            Product product;
            Product newProduct;
            String name = "";
            Double price = 0.0;
            int supermaket = 0;
            boolean exist = false;

            if (productCombo.getValue() != null) {

                String treeData[] = new String[bstProduct.size()];
                String treeData1[] = new String[50];
                treeData = bstProduct.preOrderAux().split("\n");

                for (int i = 0; i < bstProduct.size(); i++) {
                    treeData1 = treeData[i].split(",");
                    name = treeData1[1];
                    price = Double.parseDouble(treeData1[2]);
                    supermaket = Integer.parseInt(treeData1[3]);

                    if (comboSelection.equalsIgnoreCase(name)) {
                        product = new Product(name, price, supermaket);
                        if (bstProduct.contains(product)) {
                            bstProduct.remove(product);
                            exist = true;
                        }
                    }
                }
                if (exist) {
                    file.modifyProduct(bstProduct);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erase Product");
                    alert.setHeaderText("Success");
                    alert.setContentText("Product successfully removed");
                    alert.showAndWait();

                    ObservableList<String> list = FXCollections.observableArrayList(loadComboBox());
                    productCombo.setItems(list);

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
                alert.setContentText("Please, select a product");
                alert.showAndWait();
            }
        } catch (TreeException | IOException ex) {
            Logger.getLogger(FXMLRemoveProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    private void productCombo(ActionEvent event) {
        this.btnRemoveProduct.setVisible(true);
    }

}
