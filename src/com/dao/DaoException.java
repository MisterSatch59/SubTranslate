package com.dao;

/**
 * 
 * Exception généré lors de probléme d'utilisation de la base de données
 * @author oltenos
 *
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * constructeur avec message
	 */
	public DaoException(String message) {
        super(message);
    }
}