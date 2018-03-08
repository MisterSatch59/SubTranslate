package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.SubtitleLine;

public class DaoSubtitleLine2 extends Dao<SubtitleLine>  {

	@Override
	public void add(SubtitleLine subtitleLine) throws DaoException {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(int subtitle_id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = DaoFactory2.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM subtitle_line WHERE subtitle_id = ?;");
			preparedStatement.setInt(1, subtitle_id);

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
	public List<SubtitleLine> list() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SubtitleLine bean) throws DaoException {
		// TODO Auto-generated method stub
	}

	/**
	 * Retourne la liste des lignes de sous titre correspondant au subtitleId
	 * @param subtitleId
	 * @return liste des lignes de sous titre
	 * @throws DaoException 
	 */
	public List<SubtitleLine> list(int subtitleId) throws DaoException {
		Connection conn;
		try {
			conn = DaoFactory2.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		}

		List<SubtitleLine> listSubtitleLine = new ArrayList<SubtitleLine>();
		Statement statement = null;
		ResultSet resultat = null;

		try {
			statement = conn.createStatement();
			resultat = statement.executeQuery("SELECT * FROM subtitle_line WHERE subtitle_id = "+ subtitleId +";");
			
			while (resultat.next()) {
				SubtitleLine sub = new SubtitleLine();
				sub.setId(resultat.getInt("position"));
				sub.settStart(resultat.getString("start"));
				sub.settEnd(resultat.getString("end"));
				sub.setLine1(resultat.getString("line1"));
				sub.setLine2(resultat.getString("line2"));
				listSubtitleLine.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Impossible de communiquer avec la base de données");
		}

		return listSubtitleLine;
	}
	
	/**
	 * Ajoute le subtitleLine à la base de données avec le subtitleId
	 * @param subtitleId
	 * @param subtitleLines
	 * @throws DaoException
	 */
	public void add(int subtitleId,List<SubtitleLine> subtitleLines) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = DaoFactory2.getConnection();
			
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO subtitle_line (subtitle_id, position, start, end, line1, line2) VALUES( ?, ?, ?, ?, ?, ?);");
			for (SubtitleLine subtitleLine : subtitleLines) {
				preparedStatement.setInt(1, subtitleId);
				preparedStatement.setInt(2, subtitleLine.getId());
				preparedStatement.setString(3, subtitleLine.gettStart());
				preparedStatement.setString(4, subtitleLine.gettEnd());
				preparedStatement.setString(5, subtitleLine.getLine1());
				preparedStatement.setString(6, subtitleLine.getLine2());
				
				preparedStatement.executeUpdate();
			}
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

	/**
	 * Mise à jour de la ligne de soustitre
	 * @param subtitleId
	 * @param listSubtitleLine
	 * @throws DaoException
	 */
	public void update(int subtitleId, List<SubtitleLine> listSubtitleLine) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = DaoFactory2.getConnection();
			preparedStatement = connexion.prepareStatement(
					"UPDATE subtitle_line SET start = ?, end = ?, line1 = ?, line2 = ? WHERE subtitle_id = ? AND position = ? ;");
			
			for (SubtitleLine subtitleLine : listSubtitleLine) {
				preparedStatement.setString(1, subtitleLine.gettStart());
				preparedStatement.setString(2, subtitleLine.gettEnd());
				preparedStatement.setString(3, subtitleLine.getLine1());
				preparedStatement.setString(4, subtitleLine.getLine2());
				preparedStatement.setInt(5, subtitleId);
				preparedStatement.setInt(6, subtitleLine.getId());
				
				preparedStatement.executeUpdate();
			}
			
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

}
