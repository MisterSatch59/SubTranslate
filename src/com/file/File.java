package com.file;

import com.beans.Language;
import com.beans.Subtitles;

/**
 * 
 * Superclasse permettant la lecture et l'enregistrement dans un fichier
 * @author oltenos
 *
 */
public abstract class File {
	
	/**
	 * Permet l'enregistrement dans un fichier
	 * @param file dossier sous titre à sauvegarder
	 * @param adresse adresse du fichier créé
	 * @throws FileException
	 */
	public abstract void save(Subtitles file, String adresse) throws FileException;

	/**
	 * Permet la récupération d'un fichier
	 * @param adresse adresse du fichier
	 * @param language langue des sous titres
	 * @param title nom de la vidéo
	 * @return Bean contenant les sous titres
	 * @throws FileException 
	 */
	public abstract Subtitles open(String adresse, Language language, String title) throws FileException;

};
