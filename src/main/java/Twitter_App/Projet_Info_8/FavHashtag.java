package Twitter_App.Projet_Info_8;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonSyntaxException;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
/**
 * Classe qui contr�le l'affichage d'un hashtag favoris
 *
 */
public class FavHashtag {

    @FXML
    private Label nbTweetH;

    @FXML
    private Label nbUsrH;

    @FXML
    private Label nbTweetH2;

    @FXML
    private Label nbUsrH2;

    @FXML
    private BarChart<?, ?> hChart1;
    
    private String hashtagSelected = Fav.getHashtagSelected();
    
    /**
     * Foction qui se lance au d�marage de la page et affiche le hashtag favoris s�l�ctionn�.
     * @throws JsonSyntaxException
     * @throws Exception
     */

    public void initialize() throws JsonSyntaxException, Exception {
    	Tweet[] a = Tweet.searchHashtags(hashtagSelected);
    	Integer taille = a.length;
    	Integer nbU = User.differentUsers(a);
    	nbTweetH.setText("Nombre de tweets :");
    	nbUsrH.setText("Nombre d'utilisateurs :");
    	nbTweetH2.setText(taille.toString());
    	nbUsrH2.setText(nbU.toString());
    	
    	hChart1.setVisible(true);
    	hChart1.getData().clear();
    	Map<String, Integer> h = Tweet.mostLinkedHashtags(a,hashtagSelected);
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
}
