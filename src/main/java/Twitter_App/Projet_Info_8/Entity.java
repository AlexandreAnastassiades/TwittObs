package Twitter_App.Projet_Info_8;

import java.util.Arrays;  

/**
 * <b>Entity est la classe qui permet de r�cuperer tous les hashtags utilis�s lorsque l'on recherche les tweets d'un utilisateur.</b>
 * <p>
 * La classe Entity est seulement caract�ris�e par un tableau de Hashtag qu'elle peut contenir.
 * </p>
 * <p>
 * On peut ainsi r�cuperer tous les hashtags contenus dans un fichier JSON.
 * </p>
 * 
 * @see Tweet
 * @see Hashtag
 * 
 * @author ProjetInfo8
 * @version 1.0
 */
public class Entity {

    /**
     * Le tableau de Hashtag contenu dans Entity.
     * 
     * @see Entity#getHashtags()
     */
	private Hashtag[] hashtags;

    /**
     * Retourne le tableau de Hashtag de Entity.
     * 
     * @return Un tableau de Hashtag. 
     */
	public Hashtag[] getHashtags() {
		return hashtags;
	}

    /**
     * Redefinition de la m�thode hashCode() pour la classe Entity.
     * 
     * @return Un entier qui �quivaut � la valeur de hachage de l'instance cr��e
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(hashtags);
		return result;
	}

	 /**
     * Compare cet objet � un autre objet.
     * Le resultat est True que si l'autre objet n'est pas null et l'autre Entity contient les m�mes valeurs.
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
		Entity other = (Entity) obj;
		if (!Arrays.equals(hashtags, other.hashtags))
			return false;
		return true;
	}

	 /**
     * Retourne tous les Hashtags sous forme de String.
     * 
     * @return Un String repr�sentant le tableau des Hashtags.
     */
	@Override
	public String toString() {
		return "Entity [hashtags=" + Arrays.toString(hashtags) + "]";
	}

    /**
     * Constructeur Entity.
     */
	public Entity() {
		super();
	}

}


