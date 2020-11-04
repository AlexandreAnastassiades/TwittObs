package Twitter_App.Projet_Info_8;


import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Fav {

    @FXML
    private ListView<String> favUser;

    @FXML
    private ListView<String> favHashtag;
    
    private String userNameBdd = Accueil.getUserNamebdd();
    
    private static String userSelected;
    
    private static String hashtagSelected;
    /**
     * Getter qui retourne l'utilisatateur favoris qui a été sélectionn
     *@return String
     */
    public static String getUserSelected() {
    	return userSelected;
    }
    /**
     * Getter qui retourne le hashtag favoris qui a été sélectionn
     *@return String
     */
    public static String getHashtagSelected() {
    	return hashtagSelected;
    }
    /**
     * Fonction qui se lance au démarage de la page.
     */
    @FXML
    public void initialize() {
		DataBaseConnection db = new DataBaseConnection("db", "SA", ""); 
    	ArrayList<String> t = db.afficherFavUser(userNameBdd);
    	ObservableList<String> obj =FXCollections.observableArrayList();
    	for(String fav: t) {
    		obj.addAll(fav);
    	}
    	favUser.setItems(obj);
    	
    	ArrayList<String> h = db.afficherFavHashtag(userNameBdd);
    	ObservableList<String> obj2 =FXCollections.observableArrayList();
    	for(String fav: h) {
    		obj2.addAll(fav);
    	}
    	favHashtag.setItems(obj2);
    }
    /**
     * Fonction qui permet d'afficher la page qui montre le statisques d'un hashtag en favoris sans fermer les autres.
     * @param event
     * @throws IOException
     */
    @FXML
    void goToFavHashtag(MouseEvent event) throws IOException {
    	hashtagSelected = favHashtag.getSelectionModel().getSelectedItem();
    	Parent pageAccueilParent = FXMLLoader.load(getClass().getResource("PageFavHashtag.fxml"));
    	Stage stage = new Stage();
    	stage.setScene(new Scene(pageAccueilParent));
    	stage.setTitle("TWITTOBS");
    	stage.getIcons().add(new Image("file:///C:/Users/alexa/eclipse-workspace/ProjetInfo/img.png"));
    	stage.show();

    }
    
    /**
     * Fonction qui permet d'afficher la page qui monttre le statisques d'un utilisateur en favoris sans fermer les autres.
     * @param event
     * @throws IOException
     */
    @FXML
    void goToFavUser(MouseEvent event) throws IOException {
    	userSelected = favUser.getSelectionModel().getSelectedItem();
    	Parent pageAccueilParent = FXMLLoader.load(getClass().getResource("PageFavUser.fxml"));
    	Stage stage = new Stage();
    	stage.setScene(new Scene(pageAccueilParent));
    	stage.setTitle("TWITTOBS");
    	stage.getIcons().add(new Image("file:///C:/Users/alexa/eclipse-workspace/ProjetInfo/img.png"));
    	stage.show();

    }

}
