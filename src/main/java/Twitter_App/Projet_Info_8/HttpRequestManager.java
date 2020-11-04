package Twitter_App.Projet_Info_8;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * <b>HttpRequestManager est la classe qui gère toutes les requêtes https vers le serveur Twitter.</b>
 * <p>
 * La classe HttpRequestManager est caractérisée par les attributs suivantes :
 * </p>
 * <ul>
 * <li>Une clé que twitter nous fournit.</li>
 * <li>Un token que twitter nous fournit.</li>
 * <li>Une méthode de signature ici "HMAC-SHA1".</li>
 * <li>Une version de signature ici "1.0".</li>
 * <li>Une clé secrete que twitter nous fournit.</li>
 * <li>Un token secret que twitter nous fournit.</li>
 * <li>Une méthode qui correspond soit à "post" ou soit à "get" ici on ne récupère
 *  des informations donc la méthode est fixée à "get".</li>
 * </ul>
 * <p>
 * Toutes ces informations sont nécéssaires pour effectuer les requêtes vers les serveurs de 
 * twitter.
 * </p>
 * 
 * @author ProjetInfo8
 * @version 1.0
 */
public class HttpRequestManager {
    /**
     * La clé du compte utilisateur.
     */
    private final static String oauth_consumer_key =new String("GxkBhRb4QMheyWf6cSAKrREYJ");
    /**
     * Le token du compte utilisateur.
     */
    private final static String oauth_token =new String("838120495543365632-RSqTHXZTm26Htpn3aourW0qfayFl8MD");
    /**
     * La méthode de signature de la requête.
     */
    private final static String oauth_signature_method =new String("HMAC-SHA1");
    /**
     * La version.
     */
    private final static String oauth_version =new String("1.0");  
    /**
     * La clé secrète du compte utilisateur.
     */
    private final static String consumer_secret=new String("qlOR52wlgVUnAyn1I5zh1F387TvasjPWIWX4G1PB0x6K8iJbww");
    /**
     * Le token secret du compte utilisateur.
     */
    private final static String oAuth_token_secret=new String("9AdZQe6IWmx3jNvJ4ZZq7SfoPsulrwSSGUmYmhAmNHURW");
    /**
     * La clé de signature du compte utilisateur.
     */
    private final static String signingkey=new String(consumer_secret+"&"+oAuth_token_secret);
    /**
     * Le type de requête que l'on réalise, ici on impose les requêtes "get".
     */
    private final static String method=new String("get");
    
	 /**
     * Récupère le Json de la requête https dans un string à partir d'un url et de différents paramètres.
     * 
     * @param url
     * Url de la requête https selon la norme de l'API Twitter.
     * @param query_parameters
     * Dictionnaire contenant tous les paramètres de la requêtes https.
     * 
     * @return Un String contenant le JSON que nous renvoie l'API Twitter.
     * 
     * @throws Exception  Si jamais il y a une exception. 
     */
    public static String generateJSON(String url, Map<String, String> query_parameters) throws Exception {
        try {
            System.out.println("Testing 1 - Send Http GET request");
            return(sendGet(url,query_parameters));
        } catch (Exception e) {
        	return "error";
        }
    }
    
	 /**
     * Effectue une requête "get" https aux serveurs de Twitter.
     * 
     * @param url
     * Url de la requête https selon la norme de l'API Twitter.
     * @param query_parameters
     * Dictionnaire contenant tous les paramètres de la requêtes https.
     * 
     * @return Un String contenant le JSON que nous renvoie l'API Twitter.
     * 
     * @throws Exception  Si jamais il y a une exception. 
     */
    private static String sendGet(String url, Map<String, String> query_parameters) throws Exception {
    	CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
        
    	String parameters=new String(calculateParameters(query_parameters));
        HttpGet request = new HttpGet(url+parameters);
        Long time = Instant.now().getEpochSecond();
        String oauth_timestamp=new String(time.toString());
        String oauth_nonce=new String(generate(42)); 
        String data=new String(convert(url,oauth_nonce,oauth_timestamp,query_parameters));
        String oauth_signature=new String(calculateRFC2104HMAC(data,signingkey));
        String header=new String(calculateHeader(oauth_nonce, oauth_signature, oauth_timestamp));
        request.addHeader("Authorization",header);
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                httpClient.close();
                return result;
            }

        }
        return "error";

    }

	 /**
     * Génère un string aléatoire pour calculer oauth_nonce.
     * 
     * @param length
     * Longueur du String qui sera généré.
     * 
     * @return Un String aléatoire d'une longueur prédéfinie pour réaliser les requêtes.
     */
    private static String generate(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
        StringBuffer pass = new StringBuffer();
        for(int x=0;x<length;x++)   {
           int i = (int)Math.floor(Math.random() * (chars.length() -1));
           pass.append(chars.charAt(i));
        }
        return pass.toString();
}

	 /**
     * Récupère toutes les données dans un string conformément à la norme de twitter pour réaliser les requêtes https.
     * 
     * @param url
     * Url de la requêtes https conformément à l'API fournie par twitter.
     * @param oauth_nonce
     * String généré aléatoirement choisi très long pour qu'il n'apparaisse qu'une fois et qui constitue un paramètre de la requête https.
     * @param oauth_timestamp
     * String qui correspond au temps en seconde écoulé depuis 1970, c'est le timestamp, il constitue un paramètre de la requête https.
     * @param query_parameters
     * Dictionnaire contenant tous les paramètres de la requête https.
     * 
     * @return Un String qui correspond à une donnée calculée pour obtenir la signature de la requête https.
     */
    private static String convert(String url,String oauth_nonce,String oauth_timestamp,Map<String,String> query_parameters) {
		String data=new String();
		Map<String,String> parameters=new LinkedHashMap<String,String>();
	    Set<Entry<String, String>> setHm_1 = query_parameters.entrySet();
	    Iterator<Entry<String, String>> it_1 = setHm_1.iterator();
	    while(it_1.hasNext()){
	    	Entry<String, String> e = it_1.next();
	    	parameters.put(e.getKey(),e.getValue());
	     }
	    parameters.put("oauth_consumer_key=",oauth_consumer_key);
	    parameters.put("oauth_nonce=",oauth_nonce);
	    parameters.put("oauth_signature_method=",oauth_signature_method);
		parameters.put("oauth_timestamp=",oauth_timestamp);
		parameters.put("oauth_token=",oauth_token);
		parameters.put("oauth_version=",oauth_version);
		Map<String, String> parametres_tries = new TreeMap<String, String>(parameters);
	    Set<Entry<String, String>> setHm_2 = parametres_tries.entrySet();
	    Iterator<Entry<String, String>> it_2 = setHm_2.iterator();
	    while(it_2.hasNext()){
	    	Entry<String, String> e = it_2.next();
	    	data=data+e.getKey() + percentEncode(e.getValue())+"&";
	     }
	    data=data.substring(0, data.length() - 1);
	    data=method.toUpperCase()+"&"+percentEncode(url)+"&"+percentEncode(data);
		return data;
	}

	 /**
     * Retourne l'url encodé d'un string.
     * 
     * @param encodeMe
     * Un string non encodé pour être un url.
     * 
     * @return String qui est un url encodé selon les normes.
     */
    private static String percentEncode(String encodeMe) {
	    if (encodeMe == null) {
	        return "";
	    }
	    String encoded = encodeMe.replace("%", "%25");
	    encoded = encoded.replace(" ", "%20");
	    encoded = encoded.replace("!", "%21");
	    encoded = encoded.replace("#", "%23");
	    encoded = encoded.replace("$", "%24");
	    encoded = encoded.replace("&", "%26");
	    encoded = encoded.replace("'", "%27");
	    encoded = encoded.replace("(", "%28");
	    encoded = encoded.replace(")", "%29");
	    encoded = encoded.replace("*", "%2A");
	    encoded = encoded.replace("+", "%2B");
	    encoded = encoded.replace(",", "%2C");
	    encoded = encoded.replace("/", "%2F");
	    encoded = encoded.replace(":", "%3A");
	    encoded = encoded.replace(";", "%3B");
	    encoded = encoded.replace("=", "%3D");
	    encoded = encoded.replace("?", "%3F");
	    encoded = encoded.replace("@", "%40");
	    encoded = encoded.replace("[", "%5B");
	    encoded = encoded.replace("]", "%5D");
	    return encoded;
	}
	
	 /**
     * Calcule le header de la requête https en fonction des différents paramètres.
     * 
     * @param oauth_nonce
     * String généré aléatoirement choisi très long pour qu'il n'apparaisse qu'une fois et qui constitue un paramètre de la requête https.
     * @param oauth_signature
     * String qui est calculé à l'aide de tous les paramètres et qui constitue un paramètre de la requête https.
     * @param oauth_timestamp
     * String qui correspond au temps en seconde écoulé depuis 1970, c'est le timestamp, il constitue un paramètre de la requête https.
     * 
     * @return Un String qui correspond au header de la requête https.
     */
    private static String calculateHeader(String oauth_nonce, String oauth_signature,String oauth_timestamp)
	{
		String header=new String("OAuth ");	
		header+="oauth_consumer_key=\""+percentEncode(oauth_consumer_key)+"\", ";
		header+="oauth_nonce=\""+percentEncode(oauth_nonce)+"\", ";
		header+="oauth_signature=\""+percentEncode(oauth_signature)+"\", ";
		header+="oauth_signature_method=\""+percentEncode(oauth_signature_method)+"\", ";
		header+="oauth_timestamp=\""+percentEncode(oauth_timestamp)+"\", ";
		header+="oauth_token=\""+percentEncode(oauth_token)+"\", ";
		header+="oauth_version=\""+percentEncode(oauth_version)+"\"";
		return header;
	}

	 /**
     * Calcule la signature nécessaire pour executer des requêtes https.
     * Elle permet d'identifier une requête ainsi que celui qui l'execute.
     * 
     * @param data
     * Une donnée calculée à partir de tous les autres paramètre de la requête https.
     * @param key
     * Signing key qui correspond à la concaténation de la clé secrete du symbole "&" et de token secret.
     * 
     * @return Une chaîne de caractère qui correspond à la signature des requêtes https.
     */
    private static String calculateRFC2104HMAC(String data, String key)
			throws NoSuchAlgorithmException, InvalidKeyException, IOException
		{
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey); 
			byte[] signature=mac.doFinal(data.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(signature);
		}
	
	 /**
     * Retourne un String à partir d'un dictionnaire contenant les paramètres des requêtes https.
     * 
     * @param parameters
     * Un dictionnaire contenant les différents paramètres que l'on impose dans nos différentes requêtes.
     * 
     * @return Une chaîne de caractère que l'on concatène à l'url pour effectuer les requêtes https.
     */
	private static String calculateParameters(Map<String, String> parameters)
		{
			String query=new String("?");
		    Set<Entry<String, String>> setHm = parameters.entrySet();
		    Iterator<Entry<String, String>> it = setHm.iterator();
		    while(it.hasNext()){
		    	
		    	Entry<String, String> e = it.next();
		    	query=query+e.getKey() + percentEncode(e.getValue())+"&";
		     }
		    query=query.substring(0, query.length() - 1);
			return query;
		}
}