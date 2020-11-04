package Twitter_App.Projet_Info_8;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * <b>Tweet est la classe qui représente les tweets réalisés et leurs informations.</b>
 * * <p>
 * La classe Tweet est caractérisée par les attributs suivantes :
 * </p>
 * <ul>
 * <li>Une date de création.</li>
 * <li>Un identifiant unique.</li>
 * <li>Un texte qu'il contient.</li>
 * <li>Une source qui a écrit le tweet (application, web...).</li>
 * <li>Un identifiant correspondant au tweet auquel ce nouveau tweet répond.</li>
 * <li>Un identifiant correspondant à l'utilisateur auquel le tweet répond.</li>
 * <li>Un nom correspondant à l'utilisateur auquel le tweet répond.</li>
 * <li>Un identifiant correspondant au tweet cité.</li>
 * <li>Un nombre de fois où le tweet a été cité.</li>
 * <li>Un nombre de fois où le tweet a été commenté.</li>
 * <li>Un nombre de fois où le tweet a été retweeté.</li>
 * <li>Une approximation du nombre de fois où le tweet a été aimé.</li>
 * <li>Un booléen représentant si l'utilisateur a mis le tweet en favori.</li>
 * <li>Un booléen représentant si l'utilisateur a retweeté son propre tweet.</li>
 * <li>Une langue.</li>
 * <li>Des entités qui correspondent au hashtags utilisés.</li>
 * <li>Un utilisateur qui est l'auteur du tweet.</li>
 * </ul>
 * 
 * <p>
 * On peut ainsi récuperer toutes les informations d'un tweet que l'on recherche
 * </p>
 * 
 * @see Entity
 * @see User
 * 
 * @author ProjetInfo8
 * @version 1.0
 */
public class Tweet {
	
    /**
     * La date de création du tweet.
     * 
     * @see Tweet#getCreated_at()
     */
	private String created_at;
	
    /**
     * L'identifiant du tweet.
     * 
     * @see Tweet#getId_str()
     */
	private String id_str;
	
    /**
     * Le texte contenu dans le tweet.
     * 
     * @see Tweet#getFull_text()
     */
	private String full_text;
	
    /**
     * Le type de source d'où provient le tweet.
     * 
     * @see Tweet#getSource()
     */
	private String source;
	
    /**
     * L'identifiant correspondant au tweet auquel ce tweet répond.
     * 
     * @see Tweet#getIn_reply_to_status_id_str()
     */
	private String in_reply_to_status_id_str;
	
    /**
     * L'identifiant correspondant à l'utilisateur auquel le tweet répond.
     * 
     * @see Tweet#getIn_reply_to_user_id_str()
     */
	private String in_reply_to_user_id_str;
	
    /**
     * Le nom correspondant à l'utilisateur auquel le tweet répond.
     * 
     * @see Tweet#getIn_reply_to_screen_name()
     */
	private String in_reply_to_screen_name;
	
    /**
     * L'identifiant correspondant au tweet cité.
     * 
     * @see Tweet#getQuoted_status_id_str()
     */
	private String quoted_status_id_str;
	
    /**
     * Le nombre de fois où le tweet a été cité.
     * 
     * @see Tweet#getQuote_count()
     */
	private Integer quote_count;
	
    /**
     * Le nombre de fois où le tweet a été commenté.
     * 
     * @see Tweet#getReply_count()
     */
	private Integer reply_count;
	
    /**
     * Le nombre de fois où le tweet a été retweeté.
     * 
     * @see Tweet#getRetweet_count()
     */
	private Integer retweet_count;
	
    /**
     * L'approximation du nombre de fois où le tweet a été aimé.
     * 
     * @see Tweet#getFavorite_count()
     */
	private Integer favorite_count;
	
    /**
     * Le booléen représentant si l'utilisateur a mis le tweet en favori.
     * 
     * @see Tweet#isFavorited()
     */
	private boolean favorited;
	
    /**
     * Le booléen représentant si l'utilisateur a retweeté son propre tweet.
     * 
     * @see Tweet#isRetweeted()
     */
	private boolean retweeted;
	
    /**
     * La langue.
     * 
     * @see Tweet#getLang()
     */
	private String lang;
	
    /**
     * Les hashtags utilisés.
     * 
     * @see Tweet#getEntities()
     */
	private Entity entities;
	
    /**
     * L'auteur du tweet.
     * 
     * @see Tweet#getUser()
     */
	private User user;
	
    /**
     * Retourne la date de création du tweet.
     * 
     * @return Un String qui correspond à la date de création du tweet. 
     */
	public String getCreated_at() {
		return created_at;
	}

    /**
     * Retourne l'identifiant du tweet.
     * 
     * @return Un String qui correspond à l'identifiant du tweet. 
     */
	public String getId_str() {
		return id_str;
	}

    /**
     * Retourne le texte contenu dans le tweet.
     * 
     * @return Un String qui correspond au texte contenu dans le tweet. 
     */
	public String getFull_text() {
		return full_text;
	}

    /**
     * Retourne le type de source d'où provient le tweet.
     * 
     * @return Un String qui correspond au type de source d'où provient le tweet. 
     */
	public String getSource() {
		return source;
	}

    /**
     * Retourne l'identifiant correspondant au tweet auquel ce tweet répond.
     * 
     * @return Un String qui correspond au tweet auquel ce tweet répond. 
     */
	public String getIn_reply_to_status_id_str() {
		return in_reply_to_status_id_str;
	}

    /**
     * Retourne l'identifiant correspondant à l'utilisateur auquel le tweet répond.
     * 
     * @return Un String qui correspond à l'utilisateur auquel le tweet répond. 
     */
	public String getIn_reply_to_user_id_str() {
		return in_reply_to_user_id_str;
	}

    /**
     * Retourne le nom correspondant à l'utilisateur auquel le tweet répond.
     * 
     * @return Un String qui correspond à l'utilisateur auquel le tweet répond. 
     */
	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}

    /**
     * Retourne l'identifiant correspondant au tweet cité.
     * 
     * @return Un String qui correspond à l'identifiant du tweet cité. 
     */
	public String getQuoted_status_id_str() {
		return quoted_status_id_str;
	}

    /**
     * Retourne le nombre de fois où le tweet a été cité.
     * 
     * @return Un Integer qui correspond au nombre de fois où le tweet a été cité. 
     */
	public Integer getQuote_count() {
		return quote_count;
	}

    /**
     * Retourne le nombre de fois où le tweet a été commenté.
     * 
     * @return Un Integer qui correspond au nombre de fois où le tweet a été commenté. 
     */
	public Integer getReply_count() {
		return reply_count;
	}

    /**
     * Retourne le nombre de fois où le tweet a été retweeté.
     * 
     * @return Un Integer qui correspond au nombre de fois où le tweet a été retweeté. 
     */
	public Integer getRetweet_count() {
		return retweet_count;
	}

    /**
     * Retourne le nombre de fois où le tweet a été aimé.
     * 
     * @return Un Integer qui correspond au nombre de fois où le tweet a été aimé. 
     */
	public Integer getFavorite_count() {
		return favorite_count;
	}

    /**
     * Retourne le booléen représentant si l'utilisateur a mis son tweet en favori.
     * 
     * @return Un Booleen qui représente si l'utilisateur a mis son tweet en favori. 
     */
	public boolean isFavorited() {
		return favorited;
	}

    /**
     * Retourne le booléen représentant si l'utilisateur a retweeté son propre tweet.
     * 
     * @return Un Booleen qui représente si l'utilisateur a retweeté son propre tweet. 
     */
	public boolean isRetweeted() {
		return retweeted;
	}

    /**
     * Retourne la langue.
     * 
     * @return Un String qui correspond à la langue. 
     */
	public String getLang() {
		return lang;
	}

    /**
     * Retourne les hashtags utilisés.
     * 
     * @return Un Entity qui représente les hashtags utilisés. 
     */
	public Entity getEntities() {
		return entities;
	}

    /**
     * Retourne l'auteur du tweet.
     * 
     * @return Un User qui représente l'auteur du tweet. 
     */
	public User getUser() {
		return user;
	}
	
    /**
     * Redefinition de la méthode hashCode() pour la classe Tweet.
     * 
     * @return Un entier qui équivaut à la valeur de hachage de l'instance créée
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((entities == null) ? 0 : entities.hashCode());
		result = prime * result + ((favorite_count == null) ? 0 : favorite_count.hashCode());
		result = prime * result + (favorited ? 1231 : 1237);
		result = prime * result + ((full_text == null) ? 0 : full_text.hashCode());
		result = prime * result + ((id_str == null) ? 0 : id_str.hashCode());
		result = prime * result + ((in_reply_to_screen_name == null) ? 0 : in_reply_to_screen_name.hashCode());
		result = prime * result + ((in_reply_to_status_id_str == null) ? 0 : in_reply_to_status_id_str.hashCode());
		result = prime * result + ((in_reply_to_user_id_str == null) ? 0 : in_reply_to_user_id_str.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((quote_count == null) ? 0 : quote_count.hashCode());
		result = prime * result + ((quoted_status_id_str == null) ? 0 : quoted_status_id_str.hashCode());
		result = prime * result + ((reply_count == null) ? 0 : reply_count.hashCode());
		result = prime * result + ((retweet_count == null) ? 0 : retweet_count.hashCode());
		result = prime * result + (retweeted ? 1231 : 1237);
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	 /**
     * Compare cet objet à un autre objet.
     * Le resultat est True que si l'autre objet n'est pas null et l'autre Tweet contient les mêmes valeurs.
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
		Tweet other = (Tweet) obj;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (entities == null) {
			if (other.entities != null)
				return false;
		} else if (!entities.equals(other.entities))
			return false;
		if (favorite_count == null) {
			if (other.favorite_count != null)
				return false;
		} else if (!favorite_count.equals(other.favorite_count))
			return false;
		if (favorited != other.favorited)
			return false;
		if (full_text == null) {
			if (other.full_text != null)
				return false;
		} else if (!full_text.equals(other.full_text))
			return false;
		if (id_str == null) {
			if (other.id_str != null)
				return false;
		} else if (!id_str.equals(other.id_str))
			return false;
		if (in_reply_to_screen_name == null) {
			if (other.in_reply_to_screen_name != null)
				return false;
		} else if (!in_reply_to_screen_name.equals(other.in_reply_to_screen_name))
			return false;
		if (in_reply_to_status_id_str == null) {
			if (other.in_reply_to_status_id_str != null)
				return false;
		} else if (!in_reply_to_status_id_str.equals(other.in_reply_to_status_id_str))
			return false;
		if (in_reply_to_user_id_str == null) {
			if (other.in_reply_to_user_id_str != null)
				return false;
		} else if (!in_reply_to_user_id_str.equals(other.in_reply_to_user_id_str))
			return false;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (quote_count == null) {
			if (other.quote_count != null)
				return false;
		} else if (!quote_count.equals(other.quote_count))
			return false;
		if (quoted_status_id_str == null) {
			if (other.quoted_status_id_str != null)
				return false;
		} else if (!quoted_status_id_str.equals(other.quoted_status_id_str))
			return false;
		if (reply_count == null) {
			if (other.reply_count != null)
				return false;
		} else if (!reply_count.equals(other.reply_count))
			return false;
		if (retweet_count == null) {
			if (other.retweet_count != null)
				return false;
		} else if (!retweet_count.equals(other.retweet_count))
			return false;
		if (retweeted != other.retweeted)
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	 /**
     * Retourne un String qui contient toutes les informations du Tweet.
     * 
     * @return Un String qui contient tous les attributs de Tweet.
     */
	@Override
	public String toString() {
		return "Tweet [created_at=" + created_at + ", id_str=" + id_str + ", full_text=" + full_text + ", source="
				+ source + ", in_reply_to_status_id_str=" + in_reply_to_status_id_str + ", in_reply_to_user_id_str="
				+ in_reply_to_user_id_str + ", in_reply_to_screen_name=" + in_reply_to_screen_name
				+ ", quoted_status_id_str=" + quoted_status_id_str + ", quote_count=" + quote_count + ", reply_count="
				+ reply_count + ", retweet_count=" + retweet_count + ", favorite_count=" + favorite_count
				+ ", favorited=" + favorited + ", retweeted=" + retweeted + ", lang=" + lang + ", entities=" + entities
				+ ", user=" + user + "]";
	}

    /**
     * Constructeur Tweet.
     */
	public Tweet() {
		super();

	}

	 /**
     * Récupère un tableau de tous les tweets d'un utilisateur en renseignant un nom précis.
     * 
     * @param screen_name
     * Le nom d'écran de l'utilisateur dont on veut obtenir tous les tweets.
     * 
     * @return Un tableau de tous les tweets de l'utilisateur dont le nom d'écran correspond à peu près à celui passé en paramètre.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	public static Tweet[] listTweets(String screen_name) throws JsonSyntaxException, Exception {
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
        return listTweets;
	}
	
	 /**
     * Récupère un tableau de 5 tweets d'un utilisateur les plus retweetés en renseignant un nom précis.
     * 
     * @param screen_name
     * Le nom d'écran de l'utilisateur dont on veut obtenir les 5 tweets les plus retweetés.
     * 
     * @return Un tableau des 5 tweets de l'utilisateur dont le nom d'écran correspond à peu près à celui passé en paramètre les plus retweetés.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	public static Tweet[] getMostFamousTweets(String screen_name) throws JsonSyntaxException, Exception {
		
		Tweet[] allTweets = Tweet.listTweets(screen_name);
		Tweet tweetRocker=new Tweet();
		int rockerIndex=1;
		for (int i=1;i<allTweets.length;i++)
		{
			rockerIndex=i;
			while (allTweets[rockerIndex].getRetweet_count()>allTweets[rockerIndex-1].getRetweet_count()) 
			{
				tweetRocker=allTweets[rockerIndex];
				allTweets[rockerIndex]=allTweets[rockerIndex-1];
				allTweets[rockerIndex-1]=tweetRocker;
				rockerIndex=rockerIndex-1;
				if (rockerIndex==0)
				{
					break;
				}
			}
		}
		Tweet[] famousTweets=new Tweet[]{allTweets[0],allTweets[1],allTweets[2],allTweets[3],allTweets[4]};
		return famousTweets;
	}
	
	
	 /**
     * Récupère un tableau de maximum 500 tweets en effectuant une recherche précise d'hashtag.
     * 
     * @param q
     * Le hashtag que l'on recherche.
     * @param untilOrSince
     * Un string correspondant soit à since soit à until que l'on appellera dans une autre fonction pour faire des recherches chronologiques.
     * @param date
     * Paramètre renseigné si on ajoute une condition temporelle, ce paramètre doit alors être une date.
     * 
     * @return Un tableau de maximum 500 tweets correspondant à la recherche de hashtags.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	private static Tweet[] searchQueryHashtags(String q,String untilOrSince, String date) throws JsonSyntaxException, Exception {
		String url=new String("https://api.twitter.com/1.1/search/tweets.json");
		Map<String,String> query_parameters=new LinkedHashMap<String,String>();
		Tweet[] listTweets=new Tweet[]{};
		query_parameters.put("q=","#"+q+ " -filter:retweets "+untilOrSince+date);
		query_parameters.put("count=","100");
		query_parameters.put("tweet_mode=","extended");
		int countRequests=0;
		String max_id=new String();
		int lengthArrayTweets=1;
		while (lengthArrayTweets!=0 && countRequests<5) {
			Gson gson = new Gson(); 
			Status loadStatuses=gson.fromJson(HttpRequestManager.generateJSON(url,query_parameters), Status.class);
	        Tweet[] listTweetsSpecifiedPage = loadStatuses.getStatuses();
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
	        countRequests++;
		}
        return listTweets;
	}	
	
	 /**
     * Récupère un tableau de max 500 tweets en effectuant une recherche précise d'hashtag sans date précise.
     * 
     * @param q
     * Le hashtag que l'on recherche.
     * 
     * @return Un tableau de maximum 500 tweets correspondant à la recherche de hashtags sans condition imposée sur le temps.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	public static Tweet[] searchHashtags(String q) throws JsonSyntaxException, Exception {
		return searchQueryHashtags(q,"","");
	}	
	
	 /**
     * Récupère un tableau de max 500 tweets en effectuant une recherche précise d'hashtag depuis une date précise.
     * 
     * @param q
     * Le hashtag que l'on recherche.
     * @param date
     * Date depuis laquelle on accepte de récupérer les tweets contenant le hashtag recherché.
     * 
     * @return Un tableau de maximum 500 tweets correspondant à la recherche de hashtags depuis une date précise.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	public static Tweet[] searchHashtagsSince(String q, String date) throws JsonSyntaxException, Exception {
		return searchQueryHashtags(q,"since:",date);
	}
	
	 /**
     * Récupère un tableau de max 500 tweets en effectuant une recherche précise d'hashtag avant une date précise	.
     * 
     * @param q
     * Le hashtag que l'on recherche.
     * @param date
     * Date avant laquelle on accepte de récupérer les tweets contenant le hashtag recherché.
     * 
     * @return Un tableau de maximum 500 tweets correspondant à la recherche de hashtags avant une date précise.
     * 
     * @throws JsonSyntaxException  Si jamais il y a une exception qui est reperée sur le format du JSON.
     * @throws Exception  Si jamais il y a une exception. 
     */
	public static Tweet[] searchHashtagsUntil(String q, String date) throws JsonSyntaxException, Exception {
		return searchQueryHashtags(q,"until:",date);
	}
	
	 /**
     * Récupère les 10 hashtags lié à un hashtag recherche.
     * On passe en paramètre un tableau de tweet qui contient déjà tous les tweets du hashtag pour gagner du temps
     * sur les requêtes.
     * 
     * @param tweets
     * Tableau de tweets contenant le hashtag de référence.
     * @param hashtagSearched
     * Hashtag de référence qu'on cherche à relier à d'autres hashtags par odre d'occurence.
     * 
     * @return Un dictionnaire trié par ordre décroissant des hashtags les plus liés avec le hashtag de référence. 
     */
	public static Map<String,Integer>  mostLinkedHashtags(Tweet[] tweets, String hashtagSearched) {
		Map<String,Integer> listHashtags=new LinkedHashMap<String,Integer>();
		for (int i=0;i<tweets.length;i++)
		{
			Hashtag[] hashtags=new Hashtag[]{};
			hashtags=tweets[i].getEntities().getHashtags();
			for (int j=0;j<hashtags.length;j++)
			{
				if (listHashtags.containsKey(hashtags[j].getText().toLowerCase())) {
					Integer count = listHashtags.get(hashtags[j].getText().toLowerCase());
					listHashtags.replace(hashtags[j].getText().toLowerCase(), count+1); 
		        } else {
		        	listHashtags.put(hashtags[j].getText().toLowerCase(),1);
		        }
			}
		}
		Map<String, Integer> hashtagsSorted = Utils.sortByComparator(listHashtags, false);
		hashtagsSorted.remove(hashtagSearched.toLowerCase());
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
}
