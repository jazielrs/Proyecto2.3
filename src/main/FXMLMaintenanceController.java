/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Restaurant;
import domain.Supermarket;
import domain.graph.AdjacencyListGraph;
import domain.graph.GraphException;
import domain.graph.Vertex;
import domain.linkedList.ListException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Cristopher.za
 */
public class FXMLMaintenanceController implements Initializable {

    AdjacencyListGraph list;
    Restaurant restaurant;
    Supermarket supermarket;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button modifyBtn;
    @FXML
    private TableView<List<String>> outTable;
    @FXML
    private TableColumn<List<String>, String> idColumn;
    @FXML
    private TableColumn<List<String>, String> nameColumn;
    @FXML
    private TableColumn<List<String>, String> locationColumn;
    @FXML
    private Button selectButton;
    @FXML
    private TextField nameObj;
    @FXML
    private TextField locationObj;
    @FXML
    private Button actionChange;
    int idRS = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = util.Utility.getGraphList();
        // TODO
        try {
            System.out.println(list.toString());
            this.idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(0));
                }
            });

            this.nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(1));
                }
            });

            this.locationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(2));
                }
            });

            this.outTable.setItems(getData());
        } catch (ListException ex) {
            Logger.getLogger(FXMLMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void deleteBtn(ActionEvent event) throws GraphException, ListException {
        if (util.Utility.wkIst(list.getVertexByIndex(idRS).data).equals("restaurant")) {
            list.removeVertex((Restaurant) list.getVertexByIndex(idRS).data);
        } else {
            list.removeVertex((Supermarket) list.getVertexByIndex(idRS).data);
        }

        this.outTable.setItems(getData());
        System.out.println(list.toString());
    }

    @FXML
    private void modifyBtn(ActionEvent event) {
        actionChange.setDisable(false);
        nameObj.setDisable(false);
        locationObj.setDisable(false);
    }

    public ObservableList<List<String>> getData() throws ListException {

        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                List<String> arrayList = new ArrayList<>();
                if (util.Utility.wkIst(list.getVertexByIndex(i).data).equals("restaurant")) {
                    restaurant = (Restaurant) list.getVertexByIndex(i).data;
                    arrayList.add(String.valueOf(i + 1));
                    arrayList.add(String.valueOf(restaurant.getName()));
                    arrayList.add(String.valueOf(restaurant.getLocation()));
                    data.add(arrayList);
                } else {
                    supermarket = (Supermarket) list.getVertexByIndex(i).data;
                    arrayList.add(String.valueOf(i + 1));
                    arrayList.add(String.valueOf(supermarket.getName()));
                    arrayList.add(String.valueOf(supermarket.getLocation()));
                    data.add(arrayList);
                }
            }
        }
        return data;
    }

    @FXML
    private void selectButton(ActionEvent event) {
        boolean existStudent = false;
        Object selection = outTable.getSelectionModel().selectedItemProperty().get();

        ArrayList<String> array = new ArrayList();

        array = (ArrayList) selection;
        idRS = Integer.parseInt(array.get(0)) - 1;
    }

    @FXML
    private void actionChange(ActionEvent event) throws ListException {
        if (util.Utility.wkIst(list.getVertexByIndex(idRS).data).equals("restaurant")) {
            ((Restaurant) list.getVertexByIndex(idRS).data).setName(nameObj.getText());
            ((Restaurant) list.getVertexByIndex(idRS).data).setLocation(locationObj.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Se ha Modificado la información del Restaurante seleccionado");
            alert.show();
            this.outTable.setItems(getData());
        } else {
            ((Supermarket) list.getVertexByIndex(idRS).data).setName(nameObj.getText());
            ((Supermarket) list.getVertexByIndex(idRS).data).setLocation(locationObj.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Se ha Modificado la información del Supermercado seleccionado");
            alert.show();
            this.outTable.setItems(getData());
        }
        nameObj.setText("");
        locationObj.setText("");
        nameObj.setDisable(true);
        locationObj.setDisable(true);

    }

}
