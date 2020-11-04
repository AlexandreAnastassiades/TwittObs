package Twitter_App.Projet_Info_8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * <b>Hashtag est la classe qui représente les hashtags utilisés.</b>
 * <p>
 * La classe Hashtag est seulement caractérisée par le texte qu'il contient.
 * </p>
 * <p>
 * On peut ainsi faire des statistiques sur les hashtags utilisés
 * </p>
 * 
 * @author ProjetInfo8
 * @version 1.0
 */
public class Hashtag {
    /**
     * Le texte contenu dans un hashtag.
     * 
     * @see Hashtag#getText()
     */
	private String text;

    /**
     * Retourne le texte d'un hashtag.
     * 
     * @return String qui correspond au texte d'un hashtag. 
     */
	public String getText() {
		return text;
	}

    /**
     * Redefinition de la méthode hashCode() pour la classe Hashtag.
     * 
     * @return Un entier qui équivaut à la valeur de hachage de l'instance créée
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	 /**
     * Compare cet objet à un autre objet.
     * Le resultat est True que si l'autre objet n'est pas null et l'autre Hashtag contient les mêmes valeurs.
     * 
     * @param obj
     * L'objet avec lequel on va comparer.
     * 
     * @return True si les objets sont identiques sinon False.
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hashtag other = (Hashtag) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	 /**
     * Retourne le texte contenu dans le Hashtag.
     * 
     * @return Un String qui correspond au texte du Hashtag.
     */
	@Override
	public String toString() {
		return "Hashtag [text=" + text + "]";
	}

    /**
     * Constructeur Hashtag.
     */
	public Hashtag() {
		super();
	}
	
	 /**
     * Récupère un dictionnaire de tous les hashtags d'un utilisateur précis.
     * 
     * @param screen_name
     * Le nom d'écran de l'utilisateur dont on veut obtenir tous les hashtags.
     * 
     * @return Un dictionnaire de Hashtag avec le nombre d'occurence pour un utilisateur.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	public static Map<Hashtag,Integer> listHashtags(String screen_name) throws JsonSyntaxException, Exception {
		String url=new String("https://api.twitter.com/1.1/statuses/user_timeline.json");
		Map<String,String> query_parameters=new LinkedHashMap<String,String>();
		Tweet[] listTweets=new Tweet[]{};
		query_parameters.put("screen_name=",screen_name);
		query_parameters.put("count=","200");
		query_parameters.put("include_rts=","false");
		query_parameters.put("tweet_mode=","extended");
		String max_id=new String();
		int lengthArrayTweets=1;
		while (lengthArrayTweets!=0) {
			Gson gson = new Gson(); 
	        Tweet[] listTweetsSpecifiedPage = gson.fromJson(HttpRequestManager.generateJSON(url,query_parameters), Tweet[].class);
	        lengthArrayTweets=listTweetsSpecifiedPage.length;
	        listTweets=Utils.concat(listTweets, listTweetsSpecifiedPage);
			if (query_parameters.containsKey("max_id=")) {
				query_parameters.remove("max_id=", max_id);
	        }
	        if (lengthArrayTweets>0){
	        	max_id=listTweetsSpecifiedPage[lengthArrayTweets-1].getId_str();
	        	Long num = (Long.parseLong(max_id)-1);
	        	query_parameters.put("max_id=", num.toString());
        	}
		}
		Map<Hashtag,Integer> listHashtags=new LinkedHashMap<Hashtag,Integer>();
		for (int i=0;i<listTweets.length;i++)
		{
			Hashtag[] hashtags=new Hashtag[]{};
			hashtags=listTweets[i].getEntities().getHashtags();
			for (int j=0;j<hashtags.length;j++)
			{
				if (listHashtags.containsKey(hashtags[j])) {
					Integer count = listHashtags.get(hashtags[j]);
					listHashtags.replace(hashtags[j], count+1); 
		        } else {
		        	listHashtags.put(hashtags[j],1);
		        }
			}
		}
        return listHashtags;
	}
	
	 /**
     * Récupère un dictionnaire des textes des 10 hashtags (maximum) les plus populaires d'un utilisateur précis.
     * 
     * @param screen_name
     * Le nom d'écran de l'utilisateur dont on veut obtenir les hashtags les plus populaires.
     * 
     * @return Un dictionnaire de String avec le nombre d'occurence pour un utilisateur correspondant aux textes des hashtags les plus populaires.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	public static Map<String,Integer> favoriteHashtags(String screen_name) throws JsonSyntaxException, Exception {
		Map<Hashtag,Integer> listHashtags=new LinkedHashMap<Hashtag,Integer>();
		listHashtags=Hashtag.listHashtags(screen_name);
		Map<String,Integer> hashtagsUnsorted=new LinkedHashMap<String,Integer>();
		for (HashMap.Entry<Hashtag,Integer> entry : listHashtags.entrySet())
		{
		   
			hashtagsUnsorted.put(entry.getKey().getText(),entry.getValue());
		   
		}
		Map<String, Integer> hashtagsSorted = Utils.sortByComparator(hashtagsUnsorted, false);
		Map<String,Integer> favoriteHashtags=new LinkedHashMap<String,Integer>();
		Integer count=0;
		for (HashMap.Entry<String,Integer> entry : hashtagsSorted.entrySet())
		{
		   count++;
		   favoriteHashtags.put(entry.getKey(),entry.getValue());
		   if (count==10) {
			   break;
		   }
		   
		}
		return favoriteHashtags;
	}
	
	 /**
     * Affiche le top 10 des hashtags d'un utilisateur.
     * 
     * @param screen_name
     * Le nom d'écran de l'utilisateur dont on veut voir les 10 hashtags les plus populaires.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	public void displayFavoriteHashtags(String screen_name) throws JsonSyntaxException, Exception {
		Map<String, Integer> ht = new HashMap<String, Integer>();
		ht = favoriteHashtags(screen_name);
		Set<String> cles = ht.keySet();
		Iterator<String> i = cles.iterator();
		while (i.hasNext()) {
			Object cle = i.next();
			Object valeur = ht.get(cle);
			System.out.println("#"+cle+" : "+valeur);
		}
	}
}