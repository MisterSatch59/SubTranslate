package com.file;

/**
 * 
 * Exception généré lors de probléme de lecture ou d'écriture dans un fichier
 * @author oltenos
 *
 */
public class FileException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * constructeur avec message
	 */
	public FileException (String mess) {
		super(mess);
	}
}
