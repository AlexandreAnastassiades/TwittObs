package Twitter_App.Projet_Info_8;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonSyntaxException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FavUser {

    @FXML
    private Label nbTweets;

    @FXML
    private Label nbFollowers;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label screenName;

    @FXML
    private ImageView imgBan;

    @FXML
    private Label userName;

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
    private BarChart<?, ?> hChart;

    @FXML
    private Label nbAbo;

    @FXML
    private ListView<String> tweetlist;
    
    private String userSelected = Fav.getUserSelected();
    /**
     * Foction qui se lance au d�marage de la page et affiche l'utilisateur favoris s�l�ctionn�.
     * @throws JsonSyntaxException
     * @throws Exception
     */
    @FXML
    public void initialize() throws JsonSyntaxException, Exception {

    	User u =  User.showUser(userSelected);
    	userName.setText("Nom d'utilisateur : ");
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
    	
    	if (u.getProfile_banner_url() != null) { // On regarde si on a une photo de banni�re
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
    	
    	Map<String, Integer> m =  Hashtag.favoriteHashtags(userSelected);
   	    
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
		Tweet[] t = Tweet.getMostFamousTweets(userSelected);
    	ObservableList<String> obj =FXCollections.observableArrayList();
    	for( int i = 0; i<t.length; i++) {
    		obj.addAll(t[i].getFull_text());
    	}
    	tweetlist.setItems(obj);

    }

}

