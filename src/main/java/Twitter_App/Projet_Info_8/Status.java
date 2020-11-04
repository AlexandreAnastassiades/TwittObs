package Twitter_App.Projet_Info_8;

import java.util.Arrays;

/**
 * <b>Status est la classe qui permet de r�cuperer tous les tweets lorsque l'on recherche un hashtag.</b>
 * <p>
 * La classe Status est seulement caract�ris�e par un tableau de Tweet qu'elle peut contenir.
 * </p>
 * <p>
 * On peut ainsi r�cuperer tous les tweets que l'on recherche et afficher leurs informations.
 * </p>
 * 
 * @see Tweet
 * 
 * @author ProjetInfo8
 * @version 1.0
 */
public class Status {
    /**
     * Le tableau de Tweet contenu dans Status.
     * 
     * @see Status#getStatuses()
     */
	private Tweet[] statuses;

    /**
     * Retourne le tableau de Tweet de Status.
     * 
     * @return Un tableau de Tweet. 
     */
	public Tweet[] getStatuses() {
		return statuses;
	}

    /**
     * Redefinition de la m�thode hashCode() pour la classe Status.
     * 
     * @return Un entier qui �quivaut � la valeur de hachage de l'instance cr��e
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(statuses);
		return result;
	}

	 /**
     * Compare cet objet � un autre objet.
     * Le resultat est True que si l'autre objet n'est pas null et l'autre Status contient les m�mes valeurs.
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
		Status other = (Status) obj;
		if (!Arrays.equals(statuses, other.statuses))
			return false;
		return true;
	}

	 /**
     * Retourne tous les Tweets sous forme de String.
     * 
     * @return Un String repr�sentant le tableau des Tweets.
     */
	@Override
	public String toString() {
		return "Status [statuses=" + Arrays.toString(statuses) + "]";
	}

    /**
     * Constructeur Status.
     */
	public Status() {
		super();
	}
}


