package com.beans;

/**
 * 
 * Bean contenant les langue et leur abréviation dans la norme ISO 639-1
 * @author oltenos
 *
 */
public class Language {
	/**
	 * Nom de la langue en anglais
	 */
	private String name;
	/**
	 * Abréviation à 2 caractére de la langue (norme ISO 639-1)
	 */
	private String abreviation;
	
	/**
	 * Constructeur par défaut : francais
	 */
	public Language() {
		name = "francais";
		abreviation = "fr";
	}

	/**
	 * Constructeur avec paramétres;
	 * @param name
	 * @param abreviation
	 */
	public Language(String name, String abreviation) {
		this.name = name;
		this.abreviation = abreviation;
	}

	// getters et setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

}
