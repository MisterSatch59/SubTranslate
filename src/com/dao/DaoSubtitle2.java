package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.beans.Language;
import com.beans.SubtitleLine;
import com.beans.Subtitles;

/**
 * 
 * Classe Dao permet l'accés à la table subtitle de la base de données  translator2
 * 
 * @author oltenos
 *
 */
public class DaoSubtitle2 extends Dao<Subtitles> {

	@Override
	public void add(Subtitles subtitles) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = DaoFactory2.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO subtitle (title, language_abreviation, original_id) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, subtitles.getTitle());
			preparedStatement.setString(2, subtitles.getLanguage().getAbreviation());
			Integer idOriginal = subtitles.getIdOriginal();
			if (idOriginal == null) {
				preparedStatement.setNull(3, java.sql.Types.INTEGER);
			} else {
				preparedStatement.setInt(3, idOriginal);
			}
			preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			int newid = 0;
			if ( resultSet != null && resultSet.next() ) 
			{ 
			    newid = resultSet.getInt(1);
			}

			connexion.commit();
			
			List<SubtitleLine> subtitleLines = subtitles.getsubTitleLines();
			DaoFactory2.getInstance().getDaoSubtitleLine2().add(newid,subtitleLines);
			
			

		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
			e.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		} 
	}

	@Override
	public void delete(int id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			DaoFactory2.getInstance().getDaoSubtitleLine2().delete(id);
			
			connexion = DaoFactory2.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM subtitle WHERE id = ?;");
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
			e.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		}
	}

	@Override
	public List<Subtitles> list() throws DaoException {
		Connection conn;
		try {
			conn = DaoFactory2.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		}

		List<Subtitles> subtitles = new ArrayList<Subtitles>();
		Statement statement = null;
		ResultSet resultat = null;

		try {
			statement = conn.createStatement();
			resultat = statement.executeQuery("SELECT * FROM subtitle;");

			List<Language> languages = DaoFactory2.getInstance().getDaoLanguages().list();

			while (resultat.next()) {

				Subtitles subtitle = new Subtitles();

				subtitle.setId(resultat.getInt("id"));
				subtitle.setTitle(resultat.getString("title"));
				subtitle.setIdOriginal(resultat.getInt("original_id"));
				for (Iterator<Language> iterator = languages.iterator(); iterator.hasNext();) {
					Language language = (Language) iterator.next();
					if (language.getAbreviation().equals(resultat.getString("language_abreviation"))) {
						subtitle.setLanguage(language);
					}
				}

				List<SubtitleLine> listSubtitleLine = DaoFactory2.getInstance().getDaoSubtitleLine2().list(subtitle.getId());

				subtitle.setsubTitleLines(listSubtitleLine);
				subtitles.add(subtitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		} 
		return subtitles;

	}

	@Override
	public void update(Subtitles subtitles) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = DaoFactory2.getConnection();
			preparedStatement = connexion.prepareStatement("UPDATE subtitle SET title = ?, language_abreviation = ?, original_id = ? WHERE id = ? ;");
			preparedStatement.setString(1, subtitles.getTitle());
			preparedStatement.setString(2, subtitles.getLanguage().getAbreviation());
			preparedStatement.setInt(3, subtitles.getIdOriginal());
			preparedStatement.setInt(4, subtitles.getId());

			List<SubtitleLine> listSubtitleLine = subtitles.getsubTitleLines();
			DaoFactory2.getInstance().getDaoSubtitleLine2().update(subtitles.getId(),listSubtitleLine);
			
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
			e.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		} 

	}
};
