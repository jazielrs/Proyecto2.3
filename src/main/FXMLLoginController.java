/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Files;
import domain.linkedList.CircularLinkedList;
import domain.linkedList.ListException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.text.MaskFormatter;

/**
 * FXML Controller class
 *
 * @author Cristopher.za
 */
public class FXMLLoginController implements Initializable {

    CircularLinkedList managers = new CircularLinkedList();                     
    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;
    @FXML
    private Button btnLogin;
    @FXML
    private Text profileTxt;
    @FXML
    private Text txtUsername;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private Text txtPassword;
    @FXML
    private CheckBox checkButtonManager;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox checkButtonSuggestions;
    
    Files fileManage = new Files();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    private void loadPage(String page) throws IOException {
        Parent root = null;//Crea un nodo en la que almacena la pagina    
        root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        this.bp.setCenter(root);//Asigna la pagina al componente que la maneja
    }

    @FXML
    private void loginBtn(ActionEvent event) throws IOException, ListException {
        this.ap.setVisible(false);
        if (this.checkButtonManager.isSelected()) {//Si se selecciono el perfil de estudiante
            Files manage = new Files();
            if (!manage.existFile("managersFile.txt")) {
                try {
                    //Si no hay managers guardados
                    String password = this.passwordField.getText();
                    String keyWord = "keyWord";
                    String encryptedKeyWord = manage.encrypt(keyWord, "DR54");
                    String encryptedPassword = manage.encrypt(password, keyWord);
                    manage.addManager(this.txtFieldUsername.getText(), encryptedPassword, encryptedKeyWord);
                    loadPage("FXMLMenu");
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            } else {//Si ya existen managers
                if (verifyManagers()) {
                    loadPage("FXMLMenu");
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login");//Alert title
                    alert.setHeaderText("Error");//Alert header
                    alert.setContentText("Username or password is incorrect");
                    alert.showAndWait();
                    this.txtFieldUsername.setText("");
                    this.passwordField.setText("");
                    this.ap.setVisible(true);
                }
            }
        } else if (this.checkButtonSuggestions.isSelected()) {//Si se selecciono el perfil de estudiante
                    loadPage("FXMLMenuSuggestions");         
        }
    }

    @FXML
    private void managerSelection(ActionEvent event) {
        if (this.checkButtonManager.isSelected() && !this.checkButtonSuggestions.isSelected()) {//Seleccionado correcto
            txtUsername.setVisible(true);
            txtFieldUsername.setVisible(true);
            txtPassword.setVisible(true);
            passwordField.setVisible(true);
            btnLogin.setVisible(true);
        }
        if (!this.checkButtonManager.isSelected() && !this.checkButtonSuggestions.isSelected()) {//Ninguno seleccionado
            txtUsername.setVisible(false);
            txtFieldUsername.setVisible(false);
            txtPassword.setVisible(false);
            passwordField.setVisible(false);
            btnLogin.setVisible(false);
        }
        if (this.checkButtonManager.isSelected() && this.checkButtonSuggestions.isSelected()) {//2 seleccionados
            txtUsername.setVisible(false);
            txtFieldUsername.setVisible(false);
            txtPassword.setVisible(false);
            passwordField.setVisible(false);
            btnLogin.setVisible(false);
            btnLogin.setVisible(false);
        }
        if (!this.checkButtonManager.isSelected() && this.checkButtonSuggestions.isSelected()) {//Seleccionado correcto
            btnLogin.setVisible(true);
        }
    }
    public boolean verifyManagers() throws IOException {
        Files manage = new Files();
        boolean foundManager = false;
        if (manage.existFile("managersFile.txt")) {
                //Si hay managers
                if (manage.readManagers("managersFile.txt", this.txtFieldUsername.getText(), this.passwordField.getText())) {//Busca manager registrado
                    foundManager = true;
                }
        }
        return foundManager;
    }

    @FXML
    private void suggestions(ActionEvent event) {
          if (this.checkButtonSuggestions.isSelected() && !this.checkButtonManager.isSelected()) {
            btnLogin.setVisible(true);
        }
        if (!this.checkButtonManager.isSelected() && !this.checkButtonSuggestions.isSelected()) {
            txtUsername.setVisible(false);
            txtFieldUsername.setVisible(false);
            txtPassword.setVisible(false);
            passwordField.setVisible(false);
            btnLogin.setVisible(false);
            btnLogin.setVisible(false);
        }
        if (this.checkButtonManager.isSelected() && this.checkButtonSuggestions.isSelected()) {
            txtUsername.setVisible(false);
            txtFieldUsername.setVisible(false);
            txtPassword.setVisible(false);
            passwordField.setVisible(false);
            btnLogin.setVisible(false);
        }
        if (this.checkButtonManager.isSelected() && !this.checkButtonSuggestions.isSelected()) {//Seleccionado correcto
            txtUsername.setVisible(true);
            txtFieldUsername.setVisible(true);
            txtPassword.setVisible(true);
            passwordField.setVisible(true);
            btnLogin.setVisible(true);
        }
    }

}
