package com.beans;

import java.util.ArrayList;
import java.util.List;

import com.beans.SubtitleLine;

/**
 * 
 * Bean contenant les soustitres et les informations associé à une vidéo
 * @author oltenos
 * 
 */
public class Subtitles {
	/**
	 * Identifiant
	 */
	private int id;
	/**
	 * Titre de la vidéo associé
	 */
	private String title;
	/**
	 * Langue des sous titres
	 */
	private Language language;
	/**
	 * Identifiant des sous titre originaux
	 */
	private int idOriginal;
	/**
	 * Listes des sous titres
	 */
	private List<SubtitleLine> subTitleLines;

	/**
	 * Constructeur avec parametres
	 * @param id
	 * @param title
	 * @param language
	 * @param idOriginal
	 * @param subTitleLines
	 */
	public Subtitles(int id, String title, Language language, int idOriginal, List<SubtitleLine> subTitleLines) {
		this.id = id;
		this.title = title;
		this.language = language;
		this.idOriginal = idOriginal;
		this.subTitleLines = subTitleLines;
	}

	/**
	 * Constructeur par défaut
	 */
	public Subtitles() {
		this.id = 0;
		this.title = "";
		this.language = new Language();
		this.idOriginal = 0;
		this.subTitleLines = new ArrayList<SubtitleLine>();
	}

	// getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public int getIdOriginal() {
		return idOriginal;
	}

	public void setIdOriginal(int idOriginal) {
		this.idOriginal = idOriginal;
	}

	public List<SubtitleLine> getsubTitleLines() {
		return subTitleLines;
	}

	public void setsubTitleLines(List<SubtitleLine> subTitleLines) {
		this.subTitleLines = subTitleLines;
	}

	@Override
	public String toString() {
		String s="";
		for (SubtitleLine subtitleLine : subTitleLines) {
			s+=subtitleLine.toString();
			s+="\n\n";
		}
		return s;
	}
};
