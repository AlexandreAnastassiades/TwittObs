package Twitter_App.Projet_Info_8;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Classe qui controle la page Inscription
 */

public class Inscription {

    @FXML
    private TextField textPseudo;

    @FXML
    private TextField textTwitter;

    @FXML
    private PasswordField textConfMpd;

    @FXML
    private PasswordField textMdp;

    @FXML
    private Text textError;
    
    @FXML
    private Text textError1;
   
    /**
     * Fonction qui renvois à la page accueil si les informations sont correctes.
     * @param event
     * @throws IOException
     */
    @FXML
    void GoToAccueil(ActionEvent event) throws IOException {
    	DataBaseConnection db = new DataBaseConnection("db", "SA", "");
    	if (!(textMdp.getText().equals( textConfMpd.getText())) ){
    		textError.setText("Les mots de passes ne correspondent pas");
    		
    	}
    	String user_name = textPseudo.getText();
    	String pwd = textMdp.getText();
    	String twitter_name = textTwitter.getText();
    	int verif = db.ajoutUser(user_name,twitter_name,pwd);
    	if(verif==-1) {
    		textError1.setText("Le nom d'utilisateur existe déjà");
    	}
    	else {
    		Parent pageAccueilParent = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
        	Scene pageHastag = new Scene(pageAccueilParent);
        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	window.setScene(pageHastag);
        	window.show();
    	}
    }

}
