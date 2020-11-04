package Twitter_App.Projet_Info_8;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonSyntaxException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * 
 * Classe qui controle la page de Bob.
 *
 */
public class PageBob {

	
    @FXML
    private TextField textSearchUser;

    @FXML
    private ListView<String> searchUser;

    @FXML
    private TextField textSearchUser1;
    
    @FXML
    private TextField favHashtagText;

    @FXML
    private ListView<String> searchUser1;
    
    @FXML
    private Text compaNbFollower;
    
    @FXML
    private Text compaNbAbo;
    
    @FXML
    private Text nbAbo;

    @FXML
    private Text nbFollowers;

    @FXML
    private Text nbAbo1;

    @FXML
    private Text nbFollowers1;

    @FXML
    private Text nbTweet;

    @FXML
    private Text nbTweet1;

    @FXML
    private Text screenname;

    @FXML
    private Text username;

    @FXML
    private Text screenname1;

    @FXML
    private Text username1;

    @FXML
    private ImageView imgBan;

    @FXML
    private ImageView imgProfile;
    
    @FXML
    private ImageView imgBan1;

    @FXML
    private ImageView imgProfile1;

    @FXML
    private BarChart<?, ?> hChart;
    
    @FXML
    private BarChart<?, ?> hChart1;
    
    @FXML
    private Text nbAbo2;

    @FXML
    private Text nbFollowers2;

    @FXML
    private Text nbAbo21;

    @FXML
    private Text nbFollowers21;

    @FXML
    private Text nbTweet2;

    @FXML
    private Text nbTweet21;

    @FXML
    private Text screenname2;

    @FXML
    private Text username2;

    @FXML
    private Text screenname21;

    @FXML
    private Text username21;
    
    @FXML
    private Text textErrorFav;
    
    @FXML
    private ListView<String> tweetlist;
    
    @FXML
    private ListView<String> tweetlist1;
    
    @FXML
    private Text top5T;

    @FXML
    private Text top5T1;

    private String nomTwitterConnexion = Accueil.getNomTwitterConnex();
    private String userNameBdd = Accueil.getUserNamebdd();
    
    /**
     * Fonction qui permet de mettre un point tous les 3 chiffres.
     * @param Integer
     * @return String
     */
    public String point(Integer a) {
		if(a>999) {
			
			
			DecimalFormat format; 

			if(a>999999)
			{
				format = new DecimalFormat("000,000,000" ); 
			}
			else {
				format = new DecimalFormat("000,000" ); 
			}
			DecimalFormatSymbols s = format.getDecimalFormatSymbols();
			s.setGroupingSeparator('.');
			format.setDecimalFormatSymbols(s);
			String a_string = format.format(a);
			while(a_string.charAt(0) == '0') {
				a_string = a_string.substring(1);
			}
			return a_string;
		}
		else {
			return a.toString();
		}
    }
    /**
     *Fonction qui se lance au démarage et initialise la page en affichant le profil de l'utilisateur qui s'est connecté.
     *
     * @throws JsonSyntaxException
     * @throws Exception
     */
    @FXML
    public void initialize() throws JsonSyntaxException, Exception {
    	
    	top5T1.setVisible(false);
    	searchUser.setVisible(false);
    	searchUser1.setVisible(false);
    	tweetlist1.setVisible(false);
    	hChart1.setVisible(false);
    	
    	textSearchUser.setText(nomTwitterConnexion);
    	
    	User u =  User.showUser(nomTwitterConnexion);
    	username.setText("Nom d'utilisateur : ");
    	username1.setText(u.getName());
    	
    	nbTweet.setText("Nombre de Tweets : ");
    	nbTweet1.setText(u.getStatuses_count());
    	screenname.setText("Surnom : ");
    	screenname1.setText(u.getScreen_name());
    	
    	nbFollowers.setText("Nombre de Followers: ");
       	nbFollowers1.setText(u.getFollowers_count());
    	nbAbo.setText("Nombre d'abonnements : ");
    	nbAbo1.setText(u.getFriends_count());
    	
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
    	
    	Map<String, Integer> m =  Hashtag.favoriteHashtags(textSearchUser.getText());
   	    
   	    Set<String> cles = m.keySet();
		Iterator<String> b = cles.iterator();
		
		XYChart.Series set = new XYChart.Series();
		hChart.setVisible(true);
		hChart.getData().clear();
		while (b.hasNext()) {
			
			Object cle = b.next();
			Object valeur = m.get(cle);
			set.getData().add(new XYChart.Data(cle, valeur));
			
		}
		hChart.getData().addAll(set);
		
		Tweet[] t = Tweet.getMostFamousTweets(textSearchUser.getText());
    	ObservableList<String> obj =FXCollections.observableArrayList();
    	for( int i = 0; i<t.length; i++) {
    		obj.addAll(t[i].getFull_text());
    	}
    	tweetlist.setItems(obj);
    }
    
    
    /**
     * Fonction qui, sur l'action d'un bouton, propose une liste d'utilisateurs.
     * @param event
     * @throws JsonSyntaxException
     * @throws Exception
     */
    @FXML
    void GoToPropose1(ActionEvent event) throws JsonSyntaxException, Exception {
    	searchUser.setVisible(true);
    	User[] userList = User.searchUser(textSearchUser.getText());
    	ObservableList<String> items =FXCollections.observableArrayList();
    	for( int i = 0; i<userList.length; i++) {
    		items.add(userList[i].getScreen_name());
    	}
    	searchUser.setItems(items);
    }
    /**
     * Fonction qui, sur l'action d'un bouton, propose une liste d'utilisateurs.
     * @param event
     * @throws JsonSyntaxException
     * @throws Exception
     */
    @FXML
    void GoToPropose2(ActionEvent event) throws JsonSyntaxException, Exception {
    	searchUser1.setVisible(true);
    	User[] userList = User.searchUser(textSearchUser1.getText());
    	ObservableList<String> items =FXCollections.observableArrayList();
    	for( int i = 0; i<userList.length; i++) {
    		items.add(userList[i].getScreen_name());
    	}
    	searchUser1.setItems(items);
    }
    /**
     * Fonction qui change le texte de la barre de recherche en cliquant sur une proposition d'utilisateur.
     * @param event
     */
    @FXML
    void setText1(MouseEvent event) {
    	searchUser.setVisible(false);
    	textSearchUser.setText(searchUser.getSelectionModel().getSelectedItem());
    }
    /**
     * Fonction qui change le texte de al barre de recherche en cliquant sur une proposition d'utilisateur.
     * @param event
     */
    @FXML
    void settext2(MouseEvent event) {
    	searchUser1.setVisible(false);
    	textSearchUser1.setText(searchUser1.getSelectionModel().getSelectedItem());
    }
    
    /**
     * Fonction qui compare les deux utilisateurs rentr�s.
     * @param event
     * @throws JsonSyntaxException
     * @throws Exception
     */
    @FXML
    void GoToCompare(ActionEvent event) throws JsonSyntaxException, Exception {
    	top5T1.setVisible(true);
    	String name = "";
    	String name1 = "";
    	
    	User u =  User.showUser(textSearchUser.getText());
    	User u1 =  User.showUser(textSearchUser1.getText()); 
    	
    	Integer compareFol = Integer.parseInt(u.getFollowers_count_pointless());
    	Integer compareFol1 = Integer.parseInt(u1.getFollowers_count_pointless());
    	Integer resultCompareFol;
    	
    	if (compareFol > compareFol1) {
    		resultCompareFol = compareFol-compareFol1;
    		name = textSearchUser.getText();
    		name1 = textSearchUser1.getText();
    	}
    	else {
    		resultCompareFol = compareFol1-compareFol;
    		name = textSearchUser1.getText();
    		name1 =textSearchUser.getText();
    	}
    	
    	String finalCompareFol = point(resultCompareFol);
    	compaNbFollower.setText(name+" a "+finalCompareFol+" followers de plus que "+name1);
    	
    	Integer compareAbo = Integer.parseInt(u.getFriends_count_pointless());
    	Integer compareAbo1 = Integer.parseInt(u1.getFriends_count_pointless());
    	Integer resultCompareAbo;
    	if (compareAbo > compareAbo1) {
    		resultCompareAbo = compareAbo-compareAbo1;
    		name = textSearchUser.getText();
    		name1 = textSearchUser1.getText();
    	}
    	else {
    		resultCompareAbo = compareAbo1-compareAbo;
    		name = textSearchUser1.getText();
    		name1 =textSearchUser.getText();
    	}
    	String finalCompareAbo = point(resultCompareAbo);
    	compaNbAbo.setText(name+" a "+finalCompareAbo+" abonnements de plus que "+name1);
    	
    	if (!(screenname1.getText().equals(textSearchUser.getText()))){
    		username.setText("Nom d'utilisateur :");
        	username1.setText(u.getName());
        	
        	nbTweet.setText("Nombre de Tweets : ");
        	nbTweet1.setText(u.getStatuses_count());
        	screenname.setText("Surnom : ");
        	screenname1.setText(u.getScreen_name());
        	
        	nbFollowers.setText("Nombre de Followers: ");
           	nbFollowers1.setText(u.getFollowers_count());
        	nbAbo.setText("Nombre d'abonnements : ");
        	nbAbo1.setText(u.getFriends_count());
        	
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
    		
    		Tweet[] t = Tweet.getMostFamousTweets(textSearchUser.getText());
        	ObservableList<String> obj =FXCollections.observableArrayList();
        	for( int i = 0; i<t.length; i++) {
        		obj.addAll(t[i].getFull_text());
        	}
        	tweetlist.setItems(obj);
    		
    	}
    	
    	if (!screenname21.getText().equals(textSearchUser1.getText())){
    		username2.setText("Nom d'utilisateur : ");
        	username21.setText(u1.getName());
        	
        	nbTweet2.setText("Nombre de Tweets : ");
        	nbTweet21.setText(u1.getStatuses_count());
        	screenname2.setText("Surnom : ");
        	screenname21.setText(u1.getScreen_name());
        	
        	nbFollowers2.setText("Nombre de Followers: ");
           	nbFollowers21.setText(u1.getFollowers_count());
        	nbAbo2.setText("Nombre d'abonnement : ");
        	nbAbo21.setText(u1.getFriends_count());
        	
        	Map<String, Integer> m1 =  Hashtag.favoriteHashtags(textSearchUser1.getText());
       	    
       	    Set<String> cles1 = m1.keySet();
    		Iterator<String> a1 = cles1.iterator();
    		
    		if (u1.getProfile_image_url_https() != null) { // On regarde si on a une photo de profil
            	Image image1 = new Image(u1.getProfile_image_url_https());
            	imgProfile1.setImage(image1);
        		}
        	else { // On set l'image en null si il n'y en a pas
        		imgProfile1.setImage(null);
        	}
        	
        	if (u1.getProfile_banner_url() != null) { // On regarde si on a une photo de banni�re
        		Image image2 = new Image(u1.getProfile_banner_url());
        		imgBan1.setImage(image2);
        		}
        	else { // On set l'image en null si il n'y en a pas
        		imgBan1.setImage(null);
        	}
    		
    		XYChart.Series set1 = new XYChart.Series();
    		hChart1.setVisible(true);
    		hChart1.getData().clear();
    		while (a1.hasNext()) {
    			
    			Object cle1 = a1.next();
    			Object valeur1 = m1.get(cle1);
    			set1.getData().add(new XYChart.Data(cle1, valeur1));
    			
    		}
    		hChart1.getData().addAll(set1);
    		
    		Tweet[] t1 = Tweet.getMostFamousTweets(textSearchUser1.getText());
        	ObservableList<String> obj1 =FXCollections.observableArrayList();
        	for( int i = 0; i<t1.length; i++) {
        		obj1.addAll(t1[i].getFull_text());
        	}
        	tweetlist1.setItems(obj1);
        	tweetlist1.setVisible(true);
    		
    	}
    }
    /**
     * Fonction qui permet d'ajouter un utilisateur rentré dans le deuxième champ.
     * @param event
     */
    @FXML
    void favUser(ActionEvent event) {
		DataBaseConnection db = new DataBaseConnection("db", "SA", "");
    	String userFav = textSearchUser1.getText();
    	int testExist = db.ajoutFavUser(userNameBdd,userFav);
    	if (testExist == -1) {
    		textErrorFav.setText("Utilisateur déjà enregistré en favori");
    	}
    	else {
    		textErrorFav.setText("Utilisateur bien enregistré");
    	}
    	
    }
    /**
     * Fonction qui permet d'ajouter un hashtag rentr� dans le deuxi�me champ.
     * @param event
     */
    @FXML
    void favHashtag(ActionEvent event) {
		DataBaseConnection db = new DataBaseConnection("db", "SA", "");
    	String hashtagFav = favHashtagText.getText();
    	int testExist = db.ajoutFavHashtag(userNameBdd,hashtagFav);
    	if (testExist == -1) {
    		textErrorFav.setText("Hashtag déjà enregistré en favori");
    	}
    	else {
    		textErrorFav.setText("Hashtag bien enregistré");
    	}
    }
    /**
     * Fonction qui permet de revenir sur la page d'accueil.
     * @param event
     * @throws IOException
     */
    @FXML
    void LogOut(ActionEvent event) throws IOException {
    	Parent pageAccueilParent = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
    	Scene pageHastag = new Scene(pageAccueilParent);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(pageHastag);
    	window.show();

    }
    /**
     * Fonction qui affiche la page favoris sans fermer la page de Bob.
     * @param event
     * @throws IOException
     */
    @FXML
    void GoToAffFav(ActionEvent event) throws IOException {
    	Parent pageAccueilParent = FXMLLoader.load(getClass().getResource("PageFav.fxml"));
    	Stage stage = new Stage();
    	stage.setScene(new Scene(pageAccueilParent));
    	stage.setTitle("TWITTOBS");
    	stage.getIcons().add(new Image("file:///C:/Users/alexa/eclipse-workspace/ProjetInfo/img.png"));
    	stage.show();
    }
    

}
