package com.beans;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * Bean contenant un élément de sous titre
 * @author oltenos
 * 
 */
public class SubtitleLine {

	/**
	 * Identifiant de l'élément de sous titre
	 * Correspond à sa postion dans la suite des sous titres
	 */
	private int id;
	/**
	 * Temps du début d'affichage au format HH:mm:ss,SSS
	 */
	private LocalTime tStart;
	/**
	 * Temps de fin d'affichage au format HH:mm:ss,SSS
	 */
	private LocalTime tEnd;
	/**
	 * 1ère ligne de sous titre
	 */
	private String line1;
	/**
	 * 2ème ligne de sous titre
	 */
	private String line2;

	/**
	 * Constructeur avec paramètres
	 * @param id
	 * @param tStart au format HH:mm:ss,SSS
	 * @param tEnd au format HH:mm:ss,SSS
	 * @param line1
	 * @param line2
	 */
	public SubtitleLine(int id, String tStart, String tEnd, String line1, String line2) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
		this.id = id;
		this.tStart = LocalTime.parse(tStart,f);
		this.tEnd = LocalTime.parse(tEnd,f);
		this.line1 = line1;
		this.line2 = line2;
	}

	/**
	 * Constructeur par défaut
	 */
	public SubtitleLine() {
		this.id = 0;
		this.tStart = LocalTime.parse("00:00:00");
		this.tEnd = LocalTime.parse("00:00:00");
		this.line1 = "";
		this.line2 = "";
	}

	// getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String gettStart() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
		return tStart.format(f);
	}

	public void settStart(String tStart) {
		this.tStart = LocalTime.parse(tStart,DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));
	}

	public String gettEnd() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
		return tEnd.format(f);
	}

	public void settEnd(String tEnd) {
		this.tEnd = LocalTime.parse(tEnd,DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	@Override
	public String toString() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");

		String s = "" + id + "\n";
		s += tStart.format(f) + " --> " + tEnd.format(f) + "\n";
		s += line1;
		if (!line2.equals("")) {
			s += "\n" + line2;
		}
		return s;
	}
};
