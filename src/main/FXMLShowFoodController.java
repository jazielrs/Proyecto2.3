/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.BST.BST;
import domain.BST.TreeException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLShowFoodController implements Initializable {

    BST bstFood;

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private TableView<List<String>> tableFood;
    @FXML
    private TableColumn<List<String>, String> cName;
    @FXML
    private TableColumn<List<String>, String> cPrice;
    @FXML
    private TableColumn<List<String>, String> cRestaurant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bstFood = util.Utility.getBstFood();

        this.cName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0));
            }
        });
        
        this.cPrice.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1));
            }
        });
        
        this.cRestaurant.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2));
            }
        });
        
        this.tableFood.setItems(getData());
    }

    public ObservableList<List<String>> getData() {

        ObservableList<List<String>> data = FXCollections.observableArrayList();
        try {
            if (!bstFood.isEmpty()) {

                String treeData[] = new String[bstFood.size()];
                String treeData1[] = new String[50];
                treeData = bstFood.preOrderAux().split("\n");

                for (int i = 0; i < bstFood.size(); i++) {

                    List<String> arrayList = new ArrayList<>();
                    treeData1 = treeData[i].split(",");

                    arrayList.add(treeData1[1]);
                    arrayList.add(treeData1[2]);
                    arrayList.add(treeData1[3]);

                    data.add(arrayList);
                }

            }
        } catch (TreeException ex) {
            Logger.getLogger(FXMLShowFoodController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

}
