package Twitter_App.Projet_Info_8;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * <b>User est la classe qui représente les utilisateurs et leurs informations.</b>
 * <p>
 * La classe User est caractérisée par les attributs suivantes :
 * </p>
 * <ul>
 * <li>Un identifiant unique.</li>
 * <li>Un nom.</li>
 * <li>Un nom d'écran, le nom d'écran correspond au pseudo de l'utilisateur qui précède @.</li>
 * <li>Une description de l'utilisateur.</li>
 * <li>Un nombre d'utilisateurs qui "follow" l'utilisateur.</li>
 * <li>Un nombre d'utilisateurs qui sont amis avec l'utilisateur.</li>
 * <li>Un nombre de groupe dont l'utilisateur fait partie.</li>
 * <li>Un nombre de compte que l'utilisateur follow.</li>
 * <li>Un nombre de tweets que l'utilisateur a écrits.</li>
 * <li>Une date qui correspond à la création du compte.</li>
 * <li>Une image de profil.</li>
 * <li>Une image de bannière.</li>
 * </ul>
 * 
 * <p>
 * On peut ainsi récuperer toutes les informations d'un utilisateur que l'on recherche.
 * </p>
 * 
 * @see Tweet
 * 
 * @author ProjetInfo8
 * @version 1.0
 */
public class User {
	
    /**
     * L'identifiant de l'utilisateur.
     * 
     * @see User#getId_str()
     */
	private String id_str;
	
    /**
     * Le nom de l'utilisateur.
     * 
     * @see User#getName()
     */
	private String name;
	
    /**
     * Le nom d'écran de l'utilisateur.
     * 
     * @see User#getScreen_name()
     */
	private String screen_name;
	
    /**
     * La description de l'utilisateur.
     * 
     * @see User#getDescription()
     */
	private String description;
	
    /**
     * Le nombre de follower de l'utilisateur.
     * 
     * @see User#getFollowers_count()
     */
	private Integer followers_count;
	
    /**
     * Le nombre d'amis de l'utilisateur.
     * 
     * @see User#getFriends_count()
     */
	private Integer friends_count;
	
    /**
     * Le nombre de groupes de l'utilisateur.
     * 
     * @see User#getListed_count()
     */
	private Integer listed_count;
	
    /**
     * Le nombre de follow de l'utilisateur.
     * 
     * @see User#getFavourites_count()
     */
	private Integer favourites_count;
	
    /**
     * Le nombre de tweets écrits par l'utilisateur.
     * 
     * @see User#getStatuses_count()
     */
	private Integer statuses_count;
	
    /**
     * La date de création du compte de l'utilisateur.
     * 
     * @see User#getCreated_at()
     */
	private String created_at;
	
    /**
     * L'image de profile de l'utilisateur.
     * 
     * @see User#getProfile_image_url_https()
     */
	private String profile_image_url_https;
	
    /**
     * L'image de banière de l'utilisateur.
     * 
     * @see User#getProfile_banner_url()
     */
	private String profile_banner_url;
	
    /**
     * Retourne l'identifiant de l'utilisateur.
     * 
     * @return Un String qui correspond à l'identifiant de l'utilisateur. 
     */
	public String getId_str() {
		return id_str;
	}
	
    /**
     * Retourne le nom de l'utilisateur.
     * 
     * @return Un String qui correspond au nom de l'utilisateur. 
     */	
	public String getName() {
		return name;
	}
	
    /**
     * Retourne le nom d'écran de l'utilisateur.
     * 
     * @return Un String qui correspond au nom d'écran de l'utilisateur. 
     */	
	public String getScreen_name() {
		return screen_name;
	}
	
    /**
     * Retourne la description de l'utilisateur.
     * 
     * @return Un String qui correspond à la description de l'utilisateur. 
     */	
	public String getDescription() {
		return description;
	}
	
    /**
     * Retourne le nombre de followers de l'utilisateur.
     * 
     * @return Un String qui correspond au nombre de followers de l'utilisateur. 
     */	
	public String getFollowers_count() {
		if(followers_count>999) {
			
			DecimalFormat format; 

			if(followers_count>999999)
			{
				format = new DecimalFormat("000,000,000" ); 
			}
			else {
				format = new DecimalFormat("000,000" ); 
			}
			DecimalFormatSymbols s = format.getDecimalFormatSymbols();
			s.setGroupingSeparator('.');
			format.setDecimalFormatSymbols(s);
			String followers_count_string = format.format(followers_count);
			while(followers_count_string.charAt(0) == '0') {
				followers_count_string = followers_count_string.substring(1);
			}
			return followers_count_string;
		}
		else {
			return followers_count.toString();
		}
	}
	/**
     * Retourne le nombre de followers de l'utilisateur.
     * 
     * @return Un String qui correspond au nombre de followers de l'utilisateur. 
     */	
	public String getFollowers_count_pointless() {
		return followers_count.toString();
	}
	
    /**
     * Retourne le nombre d'amis de l'utilisateur.
     * 
     * @return Un String qui correspond au nombre d'amis de l'utilisateur. 
     */	
	public String getFriends_count() {
		
		if(friends_count>999) {
			
			DecimalFormat format; 

			if(friends_count>999999)
			{
				format = new DecimalFormat("000,000,000" ); 
			}
			else {
				format = new DecimalFormat("000,000" ); 
			}
			DecimalFormatSymbols s = format.getDecimalFormatSymbols();
			s.setGroupingSeparator('.');
			format.setDecimalFormatSymbols(s);
			String friends_count_string = format.format(friends_count);
			while(friends_count_string.charAt(0) == '0') {
				friends_count_string = friends_count_string.substring(1);
			}
			return friends_count_string;
		}
		else {
			return friends_count.toString();
		}
	}
	/**
     * Retourne le nombre d'amis de l'utilisateur.
     * 
     * @return Un String qui correspond au nombre d'amis de l'utilisateur. 
     */	
	public String getFriends_count_pointless() {
		return friends_count.toString();
	}
	
    /**
     * Retourne le nombre de groupes de l'utilisateur.
     * 
     * @return Un Integer qui correspond au nombre de groupes de l'utilisateur. 
     */	
	public Integer getListed_count() {
		return listed_count;
	}
	
    /**
     * Retourne le nombre de favoris de l'utilisateur.
     * 
     * @return Un String qui correspond au nombre de favoris de l'utilisateur. 
     */	
	public String getFavourites_count() {
		if(favourites_count>999) {
			
			DecimalFormat format; 

			if(favourites_count>999999)
			{
				format = new DecimalFormat("000,000,000" ); 
			}
			else {
				format = new DecimalFormat("000,000" ); 
			}
			DecimalFormatSymbols s = format.getDecimalFormatSymbols();
			s.setGroupingSeparator('.');
			format.setDecimalFormatSymbols(s);
			String favourites_count_string = format.format(favourites_count);
			while(favourites_count_string.charAt(0) == '0') {
				favourites_count_string = favourites_count_string.substring(1);
			}
			return favourites_count_string;
		}
		else {
			return favourites_count.toString();
		}
	}
	
    /**
     * Retourne le nombre de tweets de l'utilisateur.
     * 
     * @return Un String qui correspond au nombre de tweets de l'utilisateur. 
     */	
	public String getStatuses_count() {
		if(statuses_count>999) {
			
			DecimalFormat format; 

			if(statuses_count>999999)
			{
				format = new DecimalFormat("000,000,000" ); 
			}
			else {
				format = new DecimalFormat("000,000" ); 
			}
			DecimalFormatSymbols s = format.getDecimalFormatSymbols();
			s.setGroupingSeparator('.');
			format.setDecimalFormatSymbols(s);
			String statuses_count_string = format.format(statuses_count);
			while(statuses_count_string.charAt(0) == '0') {
				statuses_count_string = statuses_count_string.substring(1);
			}
			return statuses_count_string;
		}
		else {
			return statuses_count.toString();
		}
	}
	
    /**
     * Retourne la date de création du compte de l'utilisateur.
     * 
     * @return Un String qui correspond à la date de création du compte de l'utilisateur. 
     */	
	public String getCreated_at() {
		return created_at;
	}
	
    /**
     * Retourne l'url de l'image de profile de l'utilisateur.
     * 
     * @return Un String qui correspond à l'url de l'image de profile de l'utilisateur. 
     */	
	public String getProfile_image_url_https() {
		return profile_image_url_https;
	}
	
    /**
     * Retourne l'url de la banière de l'utilisateur.
     * 
     * @return Un String qui correspond à l'url de la banière de l'utilisateur. 
     */	
	public String getProfile_banner_url() {
		return profile_banner_url;
	}
	
    /**
     * Redefinition de la méthode hashCode() pour la classe User.
     * 
     * @return Un entier qui équivaut à la valeur de hachage de l'instance créée
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((favourites_count == null) ? 0 : favourites_count.hashCode());
		result = prime * result + ((followers_count == null) ? 0 : followers_count.hashCode());
		result = prime * result + ((friends_count == null) ? 0 : friends_count.hashCode());
		result = prime * result + ((id_str == null) ? 0 : id_str.hashCode());
		result = prime * result + ((listed_count == null) ? 0 : listed_count.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((profile_banner_url == null) ? 0 : profile_banner_url.hashCode());
		result = prime * result + ((profile_image_url_https == null) ? 0 : profile_image_url_https.hashCode());
		result = prime * result + ((screen_name == null) ? 0 : screen_name.hashCode());
		result = prime * result + ((statuses_count == null) ? 0 : statuses_count.hashCode());
		return result;
	}
	
	 /**
     * Compare cet objet à un autre objet.
     * Le resultat est True que si l'autre objet n'est pas null et l'autre User contient les mêmes valeurs.
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
		User other = (User) obj;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (favourites_count == null) {
			if (other.favourites_count != null)
				return false;
		} else if (!favourites_count.equals(other.favourites_count))
			return false;
		if (followers_count == null) {
			if (other.followers_count != null)
				return false;
		} else if (!followers_count.equals(other.followers_count))
			return false;
		if (friends_count == null) {
			if (other.friends_count != null)
				return false;
		} else if (!friends_count.equals(other.friends_count))
			return false;
		if (id_str == null) {
			if (other.id_str != null)
				return false;
		} else if (!id_str.equals(other.id_str))
			return false;
		if (listed_count == null) {
			if (other.listed_count != null)
				return false;
		} else if (!listed_count.equals(other.listed_count))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (profile_banner_url == null) {
			if (other.profile_banner_url != null)
				return false;
		} else if (!profile_banner_url.equals(other.profile_banner_url))
			return false;
		if (profile_image_url_https == null) {
			if (other.profile_image_url_https != null)
				return false;
		} else if (!profile_image_url_https.equals(other.profile_image_url_https))
			return false;
		if (screen_name == null) {
			if (other.screen_name != null)
				return false;
		} else if (!screen_name.equals(other.screen_name))
			return false;
		if (statuses_count == null) {
			if (other.statuses_count != null)
				return false;
		} else if (!statuses_count.equals(other.statuses_count))
			return false;
		return true;
	}

	 /**
     * Retourne un String qui contient toutes les informations du User.
     * 
     * @return Un String qui contient tous les attributs de User.
     */
	@Override
	public String toString() {
		return "User [id_str=" + id_str + ", name=" + name + ", screen_name=" + screen_name + ", description="
				+ description + ", followers_count=" + followers_count + ", friends_count=" + friends_count
				+ ", listed_count=" + listed_count + ", favourites_count=" + favourites_count + ", statuses_count="
				+ statuses_count + ", created_at=" + created_at + ", profile_image_url_https=" + profile_image_url_https
				+"progile_banner_url="+profile_banner_url+ "]";
	}
	
    /**
     * Constructeur User.
     */
	public User() {
		super();
	}
	
	 /**
     * Récupère un utilisateur précis à partir de son nom d'écran pour extraire ses informations.
     * 
     * @param screen_name
     * Le nom d'écran de l'utilisateur dont on veut obtenir les informations.
     * 
     * @return Un User dont le nom d'écran correspond à celui passé en paramètre.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	public static User showUser(String screen_name) throws JsonSyntaxException, Exception {
		String url= new String("https://api.twitter.com/1.1/users/show.json");
		Map<String,String> query_parameters=new LinkedHashMap<String,String>();
		query_parameters.put("screen_name=",screen_name);
		Gson gson = new Gson();
		User new_user = gson.fromJson(HttpRequestManager.generateJSON(url,query_parameters), User.class);
		return new_user;
	}
	
	 /**
     * Récupère un tableau d'utilisateurs à partir de leur nom d'écran pour récuperer un utilisateur précis.
     * 
     * @param q
     * Une approximation de nom d'écran pour récupérer une liste d'utilisateur potentiellement celui recherché.
     * 
     * @return Un tableau de User dont le nom d'écran correspond à peu près à celui passé en paramètre.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	public static User[] searchUser(String q) throws JsonSyntaxException, Exception {
		String url=new String("https://api.twitter.com/1.1/users/search.json");
		Map<String,String> query_parameters=new LinkedHashMap<String,String>();
		query_parameters.put("q=",q);
        Gson gson = new Gson(); 
        User[] searchUser = gson.fromJson(HttpRequestManager.generateJSON(url,query_parameters), User[].class);
        return searchUser;
	}
	
	// 
	 /**
     * Retourne un nombre d'utilisateurs différents ayant tweeté avec un même hashtag.
     * 
     * @param tweets
     * Un tableau de tweets sur lequel on va examiner combien d'utilisateurs différents ont tweeté.
     * 
     * @return Un Integer qui correspond au nombre d'utilisateurs différents ayant tweeté.
     * 
     */
	public static Integer differentUsers(Tweet[] tweets) {
		ArrayList<User> users = new ArrayList<User>();
		for (int i=0;i<tweets.length;i++)
		{
			if (i==0)
			{
				users.add(tweets[i].getUser());
			}
			else 
			{
				
				for (int j=0;j<users.size();j++)
				{
					if (tweets[i].getUser().equals(users.get(j)))
					{
						break;
					}
					if (j==users.size()-1)
					{
						users.add(tweets[i].getUser());
					}
				}

			}
		}
		return users.size();
	}
}
