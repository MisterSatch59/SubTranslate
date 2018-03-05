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
 * Classe Dao permet l'accés à la table subtitles de la base de données
 * @author oltenos
 *
 */
public class DaoSubtitles extends Dao<Subtitles> {

	@Override
	public void add(Subtitles subtitles) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = DaoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO subtitles(title, language_abreviation, original_id, sub) VALUES(?, ?, ?, ?);");
			preparedStatement.setString(1, subtitles.getTitle());
			preparedStatement.setString(2, subtitles.getLanguage().getAbreviation());
			Integer id = subtitles.getIdOriginal();
			if (id == null) {
				preparedStatement.setNull(3,java.sql.Types.INTEGER);
			}else {
				preparedStatement.setInt(3, id);
			}
			preparedStatement.setString(4, subtitles.toString());
			
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
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}
		

	}

	@Override
	public void delete(int id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = DaoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM subtitles WHERE id = ?;");
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
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}
	}

	@Override
	public List<Subtitles> list() throws DaoException {
		Connection conn;
		try {
			conn = DaoFactory.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		}

		List<Subtitles> subtitles = new ArrayList<Subtitles>();
		Statement statement = null;
		ResultSet resultat = null;

		try {
			statement = conn.createStatement();
			resultat = statement.executeQuery("SELECT * FROM subtitles;");

			List<Language> languages = DaoFactory.getDaoLanguage().list();

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

				String[] tab = resultat.getString("sub").split("\n");
				
				List <SubtitleLine> subLines = new ArrayList<SubtitleLine>();
				for (int i = 0; i < tab.length; i++) {
					SubtitleLine subLine = new SubtitleLine();
					subLine.setId(Integer.valueOf(tab[i]));
					i++;
					subLine.settStart((String) tab[i].subSequence(0, 12));
					subLine.settEnd((String) tab[i].subSequence(17, 29));
					i++;
					subLine.setLine1(tab[i]);
					i++;
					if ( i < tab.length) {
						if (!tab[i].equals("")) {
							subLine.setLine2(tab[i]);
							i++;
						}
					}
					subLines.add(subLine);
				}
				subtitle.setsubTitleLines(subLines);
				subtitles.add(subtitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}
		return subtitles;

	}

	@Override
	public void update(Subtitles subtitles)  throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = DaoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("UPDATE subtitles SET title = ?, language_abreviation = ?, original_id = ?, sub = ? WHERE id = ? ;");
			preparedStatement.setString(1, subtitles.getTitle());
			preparedStatement.setString(2, subtitles.getLanguage().getAbreviation());
			preparedStatement.setInt(3, subtitles.getIdOriginal());
			preparedStatement.setString(4, subtitles.toString());
			preparedStatement.setInt(5, subtitles.getId());

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
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}

	}
};
