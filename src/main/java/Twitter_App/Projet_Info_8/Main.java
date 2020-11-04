package Twitter_App.Projet_Info_8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Classe qui lance l'application.
 *
 */
public class Main extends Application 
{
	/**
	 * Fonction qui lance la database et l'application.
	 * @param args
	 */
    public static void main( String[] args )
    {
    	DataBaseConnection db = new DataBaseConnection("db", "SA", "");
    	db.createTableUser(); 
		db.createTableFav();
    	Application.launch(args);
    }
    /**
     * Fonction qui affiche la premi�re page.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
    	AnchorPane mainAnchor = (AnchorPane) FXMLLoader.load(Main.class.getResource("PageAccueil.fxml"));
    	primaryStage.setScene(new Scene(mainAnchor));
    	primaryStage.show(); 
    	primaryStage.setTitle("TWITTOBS");
    	primaryStage.getIcons().add(new Image("file:///C:/Users/alexa/eclipse-workspace/ProjetInfo/img.png"));
    	
    }
}
