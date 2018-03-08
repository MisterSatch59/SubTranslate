package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.Language;

/**
 * 
 * Classe Dao permet l'accés à la table language de la base de données translator2
 * Seule la lecture (méthode list()) est possible pour cette table
 * @author oltenos
 *
 */
public class DaoLanguage2 extends Dao<Language> {

	@Override
	public void add(Language beans)  throws DaoException{
		// Rien à faire : impossible d'ajouter une langue

	}

	@Override
	public void delete(int id)  throws DaoException{
		// Rien à faire : impossible de supprimer une langue

	}

	@Override
	public List<Language> list() throws DaoException {
		Connection conn;
		try {
			conn = DaoFactory2.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		}
		
		List<Language> languages = new ArrayList<Language>();
		Statement statement = null;
		ResultSet resultat = null;

		try {
			statement = conn.createStatement();
			resultat = statement.executeQuery("SELECT * FROM language;");

			while (resultat.next()) {
				String abreviation = resultat.getString("abreviation");
				String name = resultat.getString("name");

				Language language = new Language();
				language.setAbreviation(abreviation);
				language.setName(name);

				languages.add(language);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		} 
		return languages;
	}

	@Override
	public void update(Language bean)  throws DaoException{
		// Rien à faire : impossible de modifier une langue

	}

};
