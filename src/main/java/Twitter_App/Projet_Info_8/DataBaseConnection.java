package Twitter_App.Projet_Info_8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * <b>DataBaseConnection est la classe qui permet de faire toutes les opérations nécessaires 
 * sur la base de données utilisée dans l'application pour enregistrer les favoris de l'utilisateur
 * (création des tables, ajout de valeurs, récupération de données.</b>
 * <p>
 * La classe DataBaseConnection est caractérisée par les attributs suivants :
 * </p>
 * <ul>
 * <li>Un serveur.</li>
 * <li>Un nom de base de données.</li>
 * <li>Un nom d'utilisateur, nom utilisé pour se connecter à l'application.</li>
 * <li>Un mot de passe pour se connecter à l'application.</li>
 * <li>Une connexion pour faire le lien entre la base de données et l'application.</li>
 * </ul>
 * 
 * <p>
 * On peut ainsi récupérer les identifiants, nécessaires lors de sa connexion, et les favoris d'un utilisateur.
 * </p>
 * 
 * 
 * @author ProjetInfo8
 * @version 1.0
 */

public class DataBaseConnection {
	
	/**
     * Le serveur de la base de données.
     */
	String server;
	
	/**
     * Le nom de la base de données.
     */
	String database;
	
	/**
     * Le nom de l'utilisateur.
     */
	String user;
	
	/**
     * Le mot de passe de l'utilisateur.
     */
	String pwd;
	
	/**
     * La connexion entre la base de données et l'application.
     */
	Connection co;
	
	/**
     * Constructeur DataBaseConnection (qui permet la connexion à notre base de données).
     * 
     * @param db
     * Le nom de la base de données à laquelle accéder.
     * 
     * @param username
     * Le nom de l'utilisateur de la base de données.
     * 
     * @param password
     * Le mot de passe de l'utilisateur de la base de données.
     * 
     * Dans notre application, la base de données est caractérisée de la manière suivante :
     * db = ""
     * username = "SA"
     * password = ""
     */
	public DataBaseConnection(String db, String username, String password) {
		// FIXME
		this.server = "jdbc:hsqldb:hsql://localhost/db"; //Lien du serveur
		this.database = db;
		this.user = username;
		this.pwd = password;
		try {
			co = DriverManager.getConnection(server,user,pwd);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver"); // Il s'agit du driver de notre database
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
     * Crée la table users qui contiendra tous les utilisateurs de l'application, avec leur id 
     * (qui correspond à l'ordre d'inscription des utilisateurs), leur nom d'utilisateur et leur mot de passe. 
     */
	public void createTableUser(){
		// FIXME
		try {
			Statement stmt = co.createStatement();
			String query = "create table if not exists users (user_id integer generated by default as identity(start with 1, increment by 1) primary key,user_name varchar(255),twitter_name varchar(255),user_pwd varchar(255));";
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Table created successfully");
	}

	/**
     * Crée la table favoris qui contiendra les favoris d'un utilisateur de l'application soient ses comptes twitter 
     * préférés et ses hashtags préférés. Pour savoir à qui sont ces favoris on a également la colonne user_id 
     * qui correspond à la primary key de la table users.
     */
	public void createTableFav(){
		// FIXME
		try {
			Statement stmt = co.createStatement();
			String query = "create table if not exists favoris (user_id int not null,fav_user varchar(255),fav_hashtag varchar(255),CONSTRAINT fk_id_fav_user FOREIGN KEY (user_id) REFERENCES users(user_id));";
			int rs = stmt.executeUpdate(query);
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Table created successfully");
		
	}
	
	 /**
     * Récupère le compte Twitter d'un utilisateur à partir de son nom d'utilisateur de l'application.
     * 
     * @param user_name
     * Le nom d'utilisateur de l'application dont on veut récupérer le compte Twitter.
     * 
     * @return Un String correspondant au compte Twitter.
     */
	public String getTwitterName(String user_name) {
		try {
			Statement stmt = co.createStatement();
			String query = "SELECT twitter_name FROM users WHERE user_name='"+user_name+"';";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			return rs.getString("twitter_name"); //pb ici
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
     * Ajoute un nouvel utilisateur de l'application.
     * 
     * @param user_name
     * Le nom d'utilisateur qu'il a choisi.
     * 
     * @param twitter_name
     * Le compte twitter du nouvel utilisateur.
     * 
     * @param pwd
     * Le mot de passe qu'il a choisi
     * 
     * @return Un int selon si le nouvel utilisateur a pu être ajouté.
     */
	public int ajoutUser(String user_name, String twitter_name,String pwd) {
		try {
			Statement stmt_1 = co.createStatement();
			String query_1 = "SELECT user_id FROM users WHERE user_name='"+user_name+"';";
			ResultSet rs = stmt_1.executeQuery(query_1);
			if (rs.next()) {
				System.out.println("user name déjà  utilisé");
				stmt_1.close();
				return -1; // On return un int pour pouvoir afficher l'erreur sur l'interface
			}
			else {
				try {
					Statement stmt_2 = co.createStatement();
					String query_2 = "INSERT INTO users(user_name,twitter_name,user_pwd) VALUES('"+user_name+"','"+twitter_name+"','"+pwd+"');";
					int rs_2 = stmt_2.executeUpdate(query_2);
					stmt_2.close();
					return 0;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 3;
				}
				
				
			} 
		
		// FIXME
	} catch (SQLException e) {
		e.printStackTrace();
		return 3;
	}
	}
	
	/**
     * Vérifie si l'utilisateur qui se connecte à l'application est bien enregistré dans la base de données.
     * 
     * @param user_name
     * Le nom d'utilisateur.
     * 
     * @param user_pwd
     * Le mot de passe de l'utilisateur.
     * 
     * @return Un int selon si l'utilisateur existe pour lui permettre la connexion à l'application.
     */
	public int connectionUser(String user_name, String user_pwd) {
		Statement stmt_1;
		try {
			stmt_1 = co.createStatement();
			String query_1 = "SELECT * FROM users WHERE (user_name='"+user_name+"') AND (user_pwd='"+user_pwd+"');";
			ResultSet rs = stmt_1.executeQuery(query_1);
			if(rs.next()) {
				System.out.println("Connexion rÃ©ussie");
				return 0;
			}
			else {
				System.out.println("Erreur dans le nom d'utilisateur ou le mot de passe");
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;
		}
	}
	
	/**
     * Ajoute un nouveau compte twitter favori et évite l'ajout d'un favori déjà existant.
     * 
     * @param user_name
     * Le nom d'utilisateur.
     * 
     * @param fav_user
     * Le compte twitter à ajouter en favori.
     * 
     * @return Un int selon si le favori a pu être ajouté.
     */
	public int ajoutFavUser(String user_name, String fav_user) {
		// FIXME
		try {
			Statement stmt_1 = co.createStatement();
			String query_1 = "SELECT fav_user FROM favoris JOIN users ON (favoris.user_id = users.user_id) WHERE (user_name='"+user_name+"') AND (fav_user='"+fav_user+"');";
			ResultSet rs = stmt_1.executeQuery(query_1);
			if (rs.next()) {
				System.out.println("favori user déjà  enregistré");
				stmt_1.close();
				return -1;
			}
			else {
				try {
					Statement stmt_2 = co.createStatement();
					String query_2 = "INSERT INTO favoris (user_id,fav_user) VALUES((SELECT user_id FROM users WHERE user_name='"+user_name+"'),'"+fav_user+"');";
					int rs_2 = stmt_2.executeUpdate(query_2);
					stmt_2.close();
					return 0;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 3;
				}
				
			} 
		
		// FIXME
	} catch (SQLException e) {
		e.printStackTrace();
		return 3;
	}}
	
	
	/**
     * Ajoute un nouveau hashtag twitter favori et évite l'ajout d'un favori déjà existant.
     * 
     * @param user_name
     * Le nom d'utilisateur.
     * 
     * @param fav_hashtag
     * Le hashtag à ajouter en favori.
     * 
     * @return Un int selon si le favori a pu être ajouté.
     */
	public int ajoutFavHashtag(String user_name, String fav_hashtag) {
		try {
			Statement stmt_1 = co.createStatement();
			String query_1 = "SELECT fav_hashtag FROM favoris JOIN users ON (favoris.user_id = users.user_id) WHERE (user_name='"+user_name+"') AND (fav_hashtag='"+fav_hashtag+"');";
			ResultSet rs = stmt_1.executeQuery(query_1);
			if (rs.next()) {
				System.out.println("favori hashtag déjà  enregistré");
				stmt_1.close();
				return -1;
			}
			else {
				try {
					Statement stmt_2 = co.createStatement();
					String query_2 = "INSERT INTO favoris (user_id,fav_hashtag) VALUES((SELECT user_id FROM users WHERE user_name='"+user_name+"'),'"+fav_hashtag+"');";
					int rs_2 = stmt_2.executeUpdate(query_2);
					stmt_2.close();
					return 0;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 3;
				}
				
			} 
		
		// FIXME
	} catch (SQLException e) {
		e.printStackTrace();
		return 3;
	}
	}
	
	/**
     * Renvoie les comptes twitter favoris d'un utilisateur.
     * 
     * @param user_name
     * Le nom d'utilisateur pour lequel on veut afficher les favoris.
     * 
     * @return Un tableau de String contenant chaque favori
     */
	public ArrayList<String> afficherFavUser(String user_name) {
		ArrayList<String> fav = new ArrayList<>();
		try {
			int i = 0;
			Statement stmt = co.createStatement();
			String query = "SELECT fav_user FROM favoris JOIN users ON (favoris.user_id = users.user_id) WHERE user_name='"+user_name+"';";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (rs.getNString("fav_user")!=null) {
					fav.add(rs.getNString("fav_user"));
				}
				else {}
			}
			stmt.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fav;
	}
	
	/**
     * Renvoie les hashtags twitter favoris d'un utilisateur.
     * 
     * @param user_name
     * Le nom d'utilisateur pour lequel on veut afficher les favoris.
     * 
     * @return Un tableau de String contenant chaque favori
     */
	public ArrayList<String> afficherFavHashtag(String user_name) {
		ArrayList<String> fav = new ArrayList<>();
		try {
			Statement stmt = co.createStatement();
			String query = "SELECT fav_hashtag FROM favoris JOIN users ON (favoris.user_id = users.user_id) WHERE user_name='"+user_name+"';";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (rs.getNString("fav_hashtag")!=null) {
					fav.add(rs.getNString("fav_hashtag"));
				}
				else {}
			}
			stmt.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fav;
	}
	
	/**
     * Supprime un compte twitter des favoris.
     * 
     * @param user_name
     * Le nom d'utilisateur pour lequel on veut supprimer le favori.
     * 
     * @param fav_user
     * Le nom du compte Twitter à supprimer
     */
	public void deleteFavUser(String user_name,String fav_user) {
		try {
			Statement stmt = co.createStatement();
			String query = "DELETE FROM favoris WHERE (user_id=(SELECT user_id FROM users WHERE user_name='"+user_name+"')) AND (fav_user='"+fav_user+"');";
			int rs = stmt.executeUpdate(query);
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
     * Supprime un hashtag des favoris.
     * 
     * @param user_name
     * Le nom d'utilisateur pour lequel on veut supprimer le favori.
     * 
     * @param fav_hashtag
     * Le nom du hashtag à supprimer
     */
	public void deleteFavHashtag(String user_name,String fav_hashtag) {
		try {
			Statement stmt = co.createStatement();
			String query = "DELETE FROM favoris WHERE (user_id=(SELECT user_id FROM users WHERE user_name='"+user_name+"')) AND (fav_hashtag='"+fav_hashtag+"');";
			int rs = stmt.executeUpdate(query);
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
     * Supprime une table de la base de données.
     * 
     * @param table
     * Le nom de la table que l'on veut supprimer.
     */
	public void deleteTable(String table){
		try {
			Statement stmt = co.createStatement();
			String query = "DROP TABLE "+table+";";
			int rs = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
