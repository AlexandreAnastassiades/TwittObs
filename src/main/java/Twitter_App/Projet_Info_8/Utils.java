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
 * <b>Utils est une classe sans attributs qui sert de bo�te � outils pour les m�thodes des autres classes.</b>
 *
 * @author ProjetInfo8
 * @version 1.0
 */
public class Utils {
	 /**
     * Retourne un dictionnaire tri� par ordre de valeurs croissantes ou d�croissantes.
     * 
     * @param unsortMap
     * Le dictionnaire non tri�.
     * @param order
     * Le bool�en qui choisit l'odre de trie : true pour croissant et false pour d�croissant.
     * 
     * @return La concat�nation des deux autres tableaux, dans notre cas des String.
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
     * Retourne un tableau qui est la concat�nation de deux tableaux pass�s en param�tres.
     * On utilise cette m�thode pour concatener les strings
     * 
     * @param <T>
     * Type du tableau que l'on va concat�ner
     * @param first
     * Premier tableau qui va �tre en d�but de cha�ne de la concat�nation.
     * @param second
     * Dernier tableau qui va �tre en fin de cha�ne de la concat�nation.
     * 
     * @return La concat�nation des deux autres tableaux, dans notre cas des String.
     */
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
		}
}
