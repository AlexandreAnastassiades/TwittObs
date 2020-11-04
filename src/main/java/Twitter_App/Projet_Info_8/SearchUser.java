package Twitter_App.Projet_Info_8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonSyntaxException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.ObservableArray;
import javafx.collections.FXCollections;

public class SearchUser {

    
    
    @FXML
    private Label nbTweets;

    @FXML
    private Text top5T;
    
	@FXML
    private Label nbAbo;

    @FXML
    private Label nbFollowers;
	
	@FXML
	private TextField textSearchHashtag;
	@FXML
	private TextField textSearchUser;
	
    @FXML
    private ImageView imgProfile;

    @FXML
    private Label screenName;

    @FXML
    private Label userName;
    
    @FXML
    private ImageView imgBan;
    
    @FXML
    private Label screenName2;

    @FXML
    private Label nbAbo2;

    @FXML
    private Label userName2;

    @FXML
    private Label nbFollowers2;

    @FXML
    private Label nbTweet2;
    
    @FXML
    private Label nbTweetH;

    @FXML
    private Label nbUsrH;

    @FXML
    private Label nbTweetH2;

    @FXML
    private Label nbUsrH2;
   
    @FXML
    private NumberAxis axisY;
    
    @FXML
    private BarChart<?, ?> hChart;
    
    @FXML
    private BarChart<?, ?> hChart1;
    
    @FXML
    private ListView<String> searchUser;
    
    @FXML
    private ListView<String> tweetlist;
    

    
	
    @FXML
    public void initialize() {
    	top5T.setVisible(false);
    	hChart.setVisible(false); 	
    	searchUser.setVisible(false);
    	hChart1.setVisible(false); 
    	tweetlist.setVisible(false);
    }
    @FXML
    void GoToAccueil(ActionEvent event) throws IOException {
    	Parent pageAccueilParent = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
    	Scene pageHastag = new Scene(pageAccueilParent);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(pageHastag);
    	window.show();

    }
	
	@FXML
    void GoToStatHashtag(ActionEvent event) throws JsonSyntaxException, Exception {
    	Tweet[] a = Tweet.searchHashtags(textSearchHashtag.getText());
    	Integer taille = a.length;
    	Integer nbU = User.differentUsers(a);
    	nbTweetH.setText("Nombre de tweets :");
    	nbUsrH.setText("Nombre d'utilisateurs :");
    	nbTweetH2.setText(taille.toString());
    	nbUsrH2.setText(nbU.toString());
    	
    	hChart1.setVisible(true);
    	hChart1.getData().clear();
    	Map<String, Integer> h = Tweet.mostLinkedHashtags(a,textSearchHashtag.getText());
    	Set<String> cles = h.keySet();
		Iterator<String> i = cles.iterator();
		XYChart.Series set = new XYChart.Series();
		for (int j = 0;j<10;j++) {
			
			Object cle = i.next();
			Object valeur = h.get(cle);
			set.getData().add(new XYChart.Data(cle, valeur));
		}
		hChart1.getData().addAll(set);
		
    	
    }

    @FXML
    void GoToConnexion(ActionEvent event) throws IOException {
    	Parent pageAccueilParent = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
    	Scene pageHastag = new Scene(pageAccueilParent);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(pageHastag);
    	window.show();
    }

    @FXML
    void GoToStatUser(MouseEvent event) throws JsonSyntaxException, Exception {
    	top5T.setVisible(true);
    	tweetlist.setVisible(true); 
    	searchUser.setVisible(false);
    	textSearchUser.setText(searchUser.getSelectionModel().getSelectedItem());
    	User u =  User.showUser(searchUser.getSelectionModel().getSelectedItem());
    	userName.setText("Nom d'utilisateurs : ");
    	userName2.setText(u.getName());
    	nbTweets.setText("Nombre de Tweets : ");
    	nbTweet2.setText(u.getStatuses_count());
    	screenName.setText("Surnom : ");
    	screenName2.setText(u.getScreen_name());
    	if (u.getProfile_image_url_https() != null) { // On regarde si on a une photo de profil
        	Image image1 = new Image(u.getProfile_image_url_https());
        	imgProfile.setImage(image1);
    		}
    	else { // On set l'image en null si il n'y en a pas
    		imgProfile.setImage(null);
    	}
    	
    	if (u.getProfile_banner_url() != null) { // On regarde si on a une photo de bannière
    		Image image2 = new Image(u.getProfile_banner_url());
    		imgBan.setImage(image2);
    		}
    	else { // On set l'image en null si il n'y en a pas
    		imgBan.setImage(null);
    	}
       	nbFollowers.setText("Nombre de Followers: ");
       	nbFollowers2.setText(u.getFollowers_count());
    	nbAbo.setText("Nombre d'abonnements : ");
    	nbAbo2.setText(u.getFriends_count());
    	
    	Map<String, Integer> m =  Hashtag.favoriteHashtags(textSearchUser.getText());
   	    
   	    Set<String> cles = m.keySet();
		Iterator<String> a = cles.iterator();
		
		XYChart.Series set = new XYChart.Series();
		hChart.setVisible(true);
		hChart.getData().clear();
		while (a.hasNext()) {
			
			Object cle = a.next();
			Object valeur = m.get(cle);
			set.getData().add(new XYChart.Data(cle, valeur));
			
		}
		hChart.getData().addAll(set);
		
		
		//String newLine = System.getProperty("line.separator");
		Tweet[] t = Tweet.getMostFamousTweets(textSearchUser.getText());
    	ObservableList<String> obj =FXCollections.observableArrayList();
    	for( int i = 0; i<t.length; i++) {
    		obj.addAll(t[i].getFull_text());
    	}
    	tweetlist.setItems(obj);

		
		


    }
    
    @FXML
    void GoToPropose(ActionEvent event) throws JsonSyntaxException, Exception {
    	searchUser.setVisible(true);
    	User[] userList = User.searchUser(textSearchUser.getText());
    	ObservableList<String> items =FXCollections.observableArrayList();
    	for( int i = 0; i<userList.length; i++) {
    		items.add(userList[i].getScreen_name());
    	}
    	searchUser.setItems(items);
    }
    
    @FXML
    void clearHashtag(ActionEvent event) {
    	nbTweetH.setText("");
    	nbUsrH.setText("");
    	nbTweetH2.setText("");
    	nbUsrH2.setText("");
    	textSearchHashtag.setText("");
    	hChart1.setVisible(false);
    }

    @FXML
    void clearTweet(ActionEvent event) {
    	top5T.setVisible(false);
		tweetlist.setVisible(false);
		imgBan.setImage(null);
		imgProfile.setImage(null);
		userName.setText("");
    	userName2.setText("");
    	nbTweets.setText("");
    	nbTweet2.setText("");
    	screenName.setText("");
    	screenName2.setText("");
       	nbFollowers.setText("");
       	nbFollowers2.setText("");
    	nbAbo.setText("");
    	nbAbo2.setText("");// On efface tous les champs des stats utilisateur
    	hChart.setVisible(false);
    	textSearchUser.setText("");
    	
    }
    	 
    	
}


