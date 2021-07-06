/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.graph.AdjacencyMatrixGraph;
import domain.graph.Graph;
import domain.graph.GraphException;
import domain.graph.Vertex;
import domain.linkedList.ListException;
import domain.queue.QueueException;
import domain.stack.StackException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jaziel
 */
public class FXMLGraphPlacesController implements Initializable {

    @FXML
    private CheckBox cEscazú;
    @FXML
    private CheckBox cAserrí;
    @FXML
    private CheckBox cTarrazú;
    @FXML
    private CheckBox cSantaAna;
    @FXML
    private CheckBox cMoravia;
    @FXML
    private CheckBox cAcosta;
    @FXML
    private CheckBox cDota;
    @FXML
    private CheckBox cGoicochea;
    @FXML
    private CheckBox cCurridabat;
    @FXML
    private CheckBox cPuriscal;
    @FXML
    private CheckBox cTurrubares;
    @FXML
    private CheckBox cCoronado;
    @FXML
    private CheckBox cSanPedro;
    @FXML
    private CheckBox cAlajuelita;
    @FXML
    private Button buttonAdd;
    @FXML
    private CheckBox cSanJosé;
    @FXML
    private CheckBox cMora;
    @FXML
    private CheckBox cDesamparados;
    @FXML
    private CheckBox cTibas;
    
    
    //@FXML
    @FXML
    private AnchorPane apRoot;
    @FXML
    private Pane apChild;
    @FXML
    private Button btnCenter;
    //@FXML
    private TextArea txtToString;
    
    AdjacencyMatrixGraph graph;//Matriz de adjacencia
    
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
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vertexes = new String[20];
//        counter = 0;
        btnCenter.setLayoutX(300);
        btnCenter.setLayoutY(180);
        btnCenter.setVisible(false);
        
//        try {
//            start();
//        } catch (GraphException | ListException ex) {
//            Logger.getLogger(FXMLPageController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }   
    
    //Matrix
    
    
     public void drawVertices(Graph graph){//Dibuja los vertices
        
        for (float i = 0; i < 360f-0.1; i+=360f/n) {
            
            btn = new Button();
            btnArray[counter]=btn;
            btn.setId(String.valueOf(i));
            apRoot.getChildren().add(btn);
            btn.setText(graph.getVertexByIndex(counter).data.toString());
            counter++;
            
            
            
            if(i>=0&&i<90){ 
                auxI=i;
                double x=Math.sin(Math.toRadians(auxI))*hip;
                double y=Math.cos(Math.toRadians(auxI))*hip;

                btn.setLayoutX(btnCenter.getLayoutX()+y*1.5);
                btn.setLayoutY(btnCenter.getLayoutY()-x);
            }
            if(i>=90&&i<180){ 
                auxI=i-90;
                double x=Math.sin(Math.toRadians(auxI))*hip;
                double y=Math.cos(Math.toRadians(auxI))*hip;
                
                btn.setLayoutX(btnCenter.getLayoutX()-x*1.5);
                btn.setLayoutY(btnCenter.getLayoutY()-y);
            }
            if(i>=180&&i<270){ 
                 auxI=i-180;
                double x=Math.sin(Math.toRadians(auxI))*hip;
                double y=Math.cos(Math.toRadians(auxI))*hip;
                
                btn.setLayoutX(btnCenter.getLayoutX()-y*1.5);
                btn.setLayoutY(btnCenter.getLayoutY()+x);
            }
            if(i>=270&&i<360){ 
                 auxI=i-270;
                double x=Math.sin(Math.toRadians(auxI))*hip;
                double y=Math.cos(Math.toRadians(auxI))*hip;
                
                btn.setLayoutX(btnCenter.getLayoutX()+x*1.5);
                btn.setLayoutY(btnCenter.getLayoutY()+y);
            }
            
        }
    
    
    }
    
    public void drawEdges(Graph graph) throws GraphException, ListException{//Dibuja las aristas
    int i;
    int j;
        
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (btnArray[j].getText() != btnArray[i].getText()) {
                    //Arista hacia otro vertice    
                    if (graph.containsEdge(btnArray[i].getText(), btnArray[j].getText())) {
                        //drawArrow(btnArray[j], btnArray[i]);
                        //Dibuja las aristas
                        lne = new Line();
                        lne.setStrokeWidth(1);
                        apRoot.getChildren().add(lne);
                        lne.setLayoutX(btnArray[j].getLayoutX());
                        lne.setLayoutY(btnArray[j].getLayoutY());
                        lne.toBack();
                        lne.setStartX(15);
                        lne.setStartY(15);
                        lne.setEndX((btnArray[i]).getLayoutX() - btnArray[j].getLayoutX() + 15);
                        lne.setEndY((btnArray[i]).getLayoutY() - btnArray[j].getLayoutY() + 15);
                        lne.setStrokeWidth(1);
                        lne.setId(btnArray[i].getText() + " , " + btnArray[j].getText());
                        
                        //POOOO
                        Text txt = new Text("" + graph.getMatrix()[graph.indexOfValue(btnArray[i].getText())][graph.indexOfValue(btnArray[j].getText())]);
                        txt.setLayoutX(15 + btnArray[j].getLayoutX() + (btnArray[i].getLayoutX() - btnArray[j].getLayoutX() + 15) / 2);
                        txt.setLayoutY(btnArray[j].getLayoutY() + (btnArray[i].getLayoutY() - btnArray[j].getLayoutY() + 15) / 2);
                        txt.setFill(Paint.valueOf("black"));
                        apRoot.getChildren().add(txt);
                    }
                }

            }
        }
        
        


    }
    
    private void start() throws GraphException, ListException{
         try {
            n=10;
        } catch (NumberFormatException e) {
        }
        
        graph = new AdjacencyMatrixGraph(n); 
        
        //Agrega vertices
        for (int i = 0; i < n; i++) {
            
            String vertex = vertexes[i]; 
            graph.addVertex(vertex); //Agrega vertices
            
        }
        
        //Agrega aristas
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i!=j){//Evita que se creen aristas a si mismas (dirigido)
                    if (util.Utility.random(5) == 1) {//Dibuja una de cada 5 aristas posibles
                        graph.addEdge(graph.getVertexByIndex(i).data, graph.getVertexByIndex(j).data);
                    }
                }
            }
        }
        
        //Agrega pesos
        double weight = 0;
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                String v1 = new String((String) graph.getVertexByIndex(i).data);
                String v2 = new String((String) graph.getVertexByIndex(j).data);
                if(graph.containsEdge(v1, v2)){
                    double randomValue = Math.random()*(30-1)+1;
                    DecimalFormat df = new DecimalFormat("#.0");
                    graph.addWeight(v1, v2, df.format(randomValue));
                }
            }
        }
        
        
        apRoot.getChildren().clear();
        apRoot.getChildren().add(btnCenter);       
//        apRoot.getChildren().add(txtToString);
        hip=150;//longitud del centro a cada vertice
        counter = 0;
        btnArray = new Button[n];
        drawVertices(graph);
        drawEdges(graph);  

        
//        //DFS
//        if(graph.isEmpty()){//Solo eliminar
//        }else{
//            try {
//                txtDFS.setText(graph.dfs());
//            } catch (GraphException | StackException ex) {
//                Logger.getLogger(FXMLPageController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//        //BFS
//        if(graph.isEmpty()){//Solo eliminar
//        }else{
//            try {
//                txtBFS.setText(graph.bfs());
//            } catch (GraphException | QueueException | ListException ex) {
//                Logger.getLogger(FXMLPageController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
    
     

    
    
    
    
    
    
    
    
    @FXML
    private void buttonAdd(ActionEvent event) {
        CheckBox[] checkList = {cAcosta,cAlajuelita,cAserrí,cCoronado,cCurridabat,cDesamparados,cDota,
        cEscazú,cGoicochea,cMora,cMoravia,cPuriscal,cSanJosé,cSanPedro,cSantaAna,cTarrazú,cTibas,
        cTurrubares};
        
        int counter=0; int counterAdded=0;
        
        while(counter<checkList.length){
           if(checkList[counter].isSelected()){
               counterAdded++;
           }
            counter++;
        } 
        
        int indexPos = 0;
        if(counterAdded>=10){//Completa lista indices
            for(int i = 0; i < checkList.length;i++){
                if (checkList[i].isSelected()) {
                    this.vertexes[indexPos]=checkList[i].getText();
                    indexPos++;
                }
            }
        }
        
        if(counterAdded>=10){
            //Agrega al array de vertices para dibujar
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Selection");//Alert title
           alert.setHeaderText("Success");//Alert header
           alert.setContentText("Vertexes added");
           alert.showAndWait();
           
           //Invisibilizar
           apChild.setVisible(false);
           this.btnCenter.setVisible(false);//*********
            try {
                start();//Crea grafo
            } catch (GraphException | ListException ex) {
                Logger.getLogger(FXMLGraphPlacesController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
        }else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Selection");//Alert title
           alert.setHeaderText("Error");//Alert header
           alert.setContentText("Please select at least 10 options");
           alert.showAndWait();
        }  
         
        
      }
       
    }
 

