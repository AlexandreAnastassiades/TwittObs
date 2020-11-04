package Twitter_App.Projet_Info_8;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * 
 * Classe qui controle la page d'Accueil
 *
 */
public class Accueil {
	
	
	private static String nomTwitterConnex;
    @FXML
    private PasswordField pdwSaisit;

    @FXML
    private TextField emailSaisit;

    @FXML
    private Button connexionButton;
    
    @FXML
    private Text textError;
    
    /**
     * Getter qui retourne le nom twitter de l'utilisateur qui se connecte à l'application.
     * @return String
     */
    
    public static String getNomTwitterConnex() {
    	return nomTwitterConnex;
    }
    
    
    /**
     * Getter qui retourne le pseudo de l'utilisateur qui se connecte à l'application.
     * @return String
     */
    public static String getUserNamebdd() {
    	return pseudo;
    }
    private static String pseudo = "";
    /**
     * Fonction qui permet de se connecter à l'application.
     * @param event
     * @throws IOException
     */
    @FXML
    void Connection(ActionEvent event) throws IOException {
    	
    	DataBaseConnection db = new DataBaseConnection("db", "SA", "");
    	int a = db.connectionUser(emailSaisit.getText(), pdwSaisit.getText());
    	if (a==0) {
    		String test = emailSaisit.getText();
    		pseudo =test;
    		nomTwitterConnex = db.getTwitterName(emailSaisit.getText());
    		System.out.println(nomTwitterConnex);
    		Parent pageAccueilParent = FXMLLoader.load(getClass().getResource("PageBob.fxml"));
    		Scene pageHastag = new Scene(pageAccueilParent);
    		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    		window.setScene(pageHastag);
    		window.show();
    	}
    	else {
    		textError.setVisible(true);
    		textError.setText("Nom utilisateur ou mot de passe faux");
    		
    	}
    }
    /**
     * Fonction qui permet de passer à la page inscription de l'application.
     * @param event
     * @throws IOException
     */
    @FXML
    void GoToInscription(ActionEvent event) throws IOException {
    	Parent pageAccueilParent = FXMLLoader.load(getClass().getResource("PageInscription.fxml"));
    	Scene pageHastag = new Scene(pageAccueilParent);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(pageHastag);
    	window.show();

    }

   /**
    * Fonction qui permet de changer de page pour celle d'Alice.
    * @param event
    * @throws IOException
    */
    @FXML
    void GoToAlice(ActionEvent event) throws IOException {
    	Parent pageAccueilParent = FXMLLoader.load(getClass().getResource("PageSearchUser.fxml"));
    	Scene pageHastag = new Scene(pageAccueilParent);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(pageHastag);
    	window.show();
    }
    

}
