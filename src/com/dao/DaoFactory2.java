package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * Factory des élément dao pour la base de données Mysql translator2
 * @author oltenos
 *
 */
public class DaoFactory2 extends AbstractFactory{
	/**
	 * URL de connexion
	 */
	private static String url = "jdbc:mysql://localhost:3306/translator2";
	/**
	 * Nom du user
	 */
	private static String user = "utilisateur";
	/**
	 * Mot de passe de l'utilisateur
	 */
	private static String passwd = "utilisateur";
	/**
	 * Objet Connection
	 */
	private static Connection conn;
	/**
	 * Instance
	 */
	private static DaoFactory2 instance = new DaoFactory2();

	/**
	 * Constructeur privé
	 */
	private DaoFactory2() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }
		try {
			conn = DriverManager.getConnection(url, user, passwd);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return l'instance de DaoFactory
	 */
	public static DaoFactory2 getInstance() {
		if (conn == null)
			instance = new DaoFactory2();

		return instance;
	}
	
	/**
	 * Permet d'obtenir la connection à la base de données
	 * @return la connection à la base de données
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		if(conn == null) {
					conn = DriverManager.getConnection(url, user, passwd);
			conn.setAutoCommit(false);
			Statement statement = conn.createStatement();
			statement.executeQuery("SET NAMES 'utf8';");
		}
        return conn; 
    }

	/**
	 * Permet d'obtenir un objet DaoSubtitle2
	 * @return un nouvel objet DaoSubtitle2
	 */
	@Override
	public DaoSubtitle2 getDaoSubtitles() {
		return new DaoSubtitle2();
	}
	
	/**
	 * Permet d'obtenir un objet DaoSubtitleLine2
	 * @return un nouvel objet DaoSubtitleLine2
	 */
	public DaoSubtitleLine2 getDaoSubtitleLine2() {
		return new DaoSubtitleLine2();
	}
	
	/**
	 * Permet d'obtenir un objet DaoLanguage2
	 * @return un nouvel objet DaoLanguage2
	 */
	@Override
	public DaoLanguage2 getDaoLanguages() {
		return new DaoLanguage2();
	}

};
