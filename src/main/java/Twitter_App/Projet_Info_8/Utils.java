package Twitter_App.Projet_Info_8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <b>Utils est une classe sans attributs qui sert de boîte à outils pour les méthodes des autres classes.</b>
 *
 * @author ProjetInfo8
 * @version 1.0
 */
public class Utils {
	 /**
     * Retourne un dictionnaire trié par ordre de valeurs croissantes ou décroissantes.
     * 
     * @param unsortMap
     * Le dictionnaire non trié.
     * @param order
     * Le booléen qui choisit l'odre de trie : true pour croissant et false pour décroissant.
     * 
     * @return La concaténation des deux autres tableaux, dans notre cas des String.
     */
	public static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
	
	 /**
     * Retourne un tableau qui est la concaténation de deux tableaux passés en paramètres.
     * On utilise cette méthode pour concatener les strings
     * 
     * @param <T>
     * Type du tableau que l'on va concaténer
     * @param first
     * Premier tableau qui va être en début de chaîne de la concaténation.
     * @param second
     * Dernier tableau qui va être en fin de chaîne de la concaténation.
     * 
     * @return La concaténation des deux autres tableaux, dans notre cas des String.
     */
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
		}
}
