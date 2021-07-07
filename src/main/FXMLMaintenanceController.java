/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Files;
import domain.Restaurant;
import domain.Supermarket;
import domain.graph.AdjacencyListGraph;
import domain.graph.EdgeWeight;
import domain.graph.Graph;
import domain.graph.GraphException;
import domain.graph.Vertex;
import domain.linkedList.ListException;
import java.io.IOException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Cristopher.za
 */
public class FXMLMaintenanceController implements Initializable {

    Files files = new Files();
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
    Button btn;
    float auxI;
    int hip;
    int n;
    Button btnArray[];
    int counter;
    Line lne;
    Line a1;
    Line a2;
    String[] vertexes;
    @FXML
    private Button btnCenter;
    @FXML
    private AnchorPane apRoot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        vertexes = new String[20];
//        counter = 0;
        btnCenter.setVisible(false);
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
        try {
            start();
        } catch (GraphException ex) {
            Logger.getLogger(FXMLMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
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
        start();
    }

    @FXML
    private void modifyBtn(ActionEvent event)  {

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
    private void actionChange(ActionEvent event) throws ListException, IOException, GraphException, GraphException {
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
//        files.addMarket(supermarket);
//        files.addRestaurant(restaurant);
        nameObj.setText("");
        locationObj.setText("");
        nameObj.setDisable(true);
        locationObj.setDisable(true);
        start();
    }

    public void drawEdges(Graph graph) throws ListException {//Dibuja las aristas
        int i;
        int j;
        String a;
        String b;
        EdgeWeight edge;
        String val;
        for (i = 0; i < graph.size(); i++) {//indices
            //Obtengo vertice
            a = btnArray[i].getText();

            if (!graph.getVertexList()[i].edgesList.isEmpty()) {//Verifica que existan aristas previas 
                for (j = 1; j <= graph.getVertexList()[i].edgesList.size(); j++) {//Lista enlazada
                    edge = (EdgeWeight) graph.getVertexList()[i].edgesList.getNode(j).data;
                    if (edge.getEdge() instanceof Supermarket) {
                        b = ((Supermarket) edge.getEdge()).getName();
                    } else {
                        b = ((Restaurant) edge.getEdge()).getName();
                    }

                    //Obtengo aristas
                    int indexI = 0;
                    int indexJ = 0;
                    int index = 0;
                    while (btnArray[indexI].getText().equals(a) == false) {
                        indexI = index;
                        if (btnArray[indexI].getText().equals(a) == false) {
                            index++;
                        }
                    }
                    index = 0;
                    while (btnArray[indexJ].getText().equals(b) == false) {
                        indexJ = index;
                        if (btnArray[indexI].getText().equals(b) == false) {
                            index++;
                        }
                    }

                    lne = new Line();
                    apRoot.getChildren().add(lne);
                    lne.setLayoutX(btnArray[indexJ].getLayoutX());
                    lne.setLayoutY(btnArray[indexJ].getLayoutY());
                    lne.toBack();
                    lne.setStartX(15);
                    lne.setStartY(15);
                    lne.setEndX((btnArray[indexI]).getLayoutX() - btnArray[indexJ].getLayoutX() + 15);
                    lne.setEndY((btnArray[indexI]).getLayoutY() - btnArray[indexJ].getLayoutY() + 15);
                    lne.setStrokeWidth(4);
                    lne.setId(btnArray[indexI].getText() + " , " + btnArray[indexJ].getText());

                }
            }
        }

    }

    public void drawVertices(Graph graph) {//Dibuja los vertices

        for (float i = 0; i < 360f - 0.1; i += 360f / n) {

            btn = new Button();
            btnArray[counter] = btn;
            btn.setId(String.valueOf(i));
            apRoot.getChildren().add(btn);
            if(graph.getVertexByIndex(counter).data instanceof Supermarket){
                btn.setText(((Supermarket)graph.getVertexByIndex(counter).data).getName());
            }else{
                btn.setText(((Restaurant)graph.getVertexByIndex(counter).data).getName());
            }
            counter++;

            if (i >= 0 && i < 90) {
                auxI = i;
                double x = Math.sin(Math.toRadians(auxI)) * hip;
                double y = Math.cos(Math.toRadians(auxI)) * hip;

                btn.setLayoutX(btnCenter.getLayoutX() + y * 1.5);
                btn.setLayoutY(btnCenter.getLayoutY() - x);
            }
            if (i >= 90 && i < 180) {
                auxI = i - 90;
                double x = Math.sin(Math.toRadians(auxI)) * hip;
                double y = Math.cos(Math.toRadians(auxI)) * hip;

                btn.setLayoutX(btnCenter.getLayoutX() - x * 1.5);
                btn.setLayoutY(btnCenter.getLayoutY() - y);
            }
            if (i >= 180 && i < 270) {
                auxI = i - 180;
                double x = Math.sin(Math.toRadians(auxI)) * hip;
                double y = Math.cos(Math.toRadians(auxI)) * hip;

                btn.setLayoutX(btnCenter.getLayoutX() - y * 1.5);
                btn.setLayoutY(btnCenter.getLayoutY() + x);
            }
            if (i >= 270 && i < 360) {
                auxI = i - 270;
                double x = Math.sin(Math.toRadians(auxI)) * hip;
                double y = Math.cos(Math.toRadians(auxI)) * hip;

                btn.setLayoutX(btnCenter.getLayoutX() + x * 1.5);
                btn.setLayoutY(btnCenter.getLayoutY() + y);
            }

        }

    }

    private void start() throws GraphException, ListException {
        n = list.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {//Evita que se creen aristas a si mismas (dirigido)
                    if (util.Utility.random(5) == 1) {//Dibuja una de cada 5 aristas posibles
                        if (!list.containsEdge(list.getVertexByIndex(i).data, list.getVertexByIndex(j).data)) {
                            list.addEdge(list.getVertexByIndex(i).data, list.getVertexByIndex(j).data);
                        }
                    }
                }
            }
        }
        System.out.println(list.toString());
        apRoot.getChildren().clear();
        apRoot.getChildren().add(btnCenter);
        apRoot.getChildren().add(outTable);
        apRoot.getChildren().add(selectButton);
        apRoot.getChildren().add(deleteBtn);
        apRoot.getChildren().add(actionChange);
        apRoot.getChildren().add(modifyBtn);
        apRoot.getChildren().add(nameObj);
        apRoot.getChildren().add(locationObj);
        hip = 150;//longitud del centro a cada vertice
        counter = 0;
        btnArray = new Button[n];
        drawVertices(list);
        drawEdges(list);

    }

}
