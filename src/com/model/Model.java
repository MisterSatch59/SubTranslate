package com.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import com.beans.Language;
import com.beans.SubtitleLine;
import com.beans.Subtitles;
import com.dao.DaoException;
import com.dao.DaoFactory;
import com.file.FileException;
import com.file.SRTFile;

public class Model {
	/**
	 * Message en cas d'erreur
	 */
	private String errorMessage = "";
	/**
	 * Sous titres originaux
	 */
	private Subtitles subtitlesOriginal;
	/**
	 * Sous titre traduit
	 */
	private Subtitles subtitlesDestination;
	
	/**
	 * taille tampon pour l'upload de fichier
	 */
	public int tailleTampon = 10240;
	/**
	 * dossier contenant temporairement les fichier SRT (download)
	 */
	public String cheminFichier;

	/**
	 * instance unique de Model
	 */
	private static Model instance = new Model();

	/**
	 * Constructeur privé
	 */
	private Model() {
		
	}
	/**
	 * Acces à l'instance unique de Model
	 * 
	 * @return instance de Model
	 */
	public static Model getInstance() {
		if (instance == null)
			instance = new Model();
		return instance;
	}
	
	/**
	 * permet l'obtention de l'adresse réel de WebInf 
	 * @param adresseWebInf adresse de WebInf obtenu avec getServletContext().getRealPath("/WEB-INF/") dans la servlet Index
	 */
	public void setAdresseWebInf(String adresseWebInf) {
		this.cheminFichier = adresseWebInf;
	}

	/**
	 * Retourne la liste des noms des langues
	 * 
	 * @return liste des noms des langues
	 */
	public String[] getLanguagesNames() {
		List<Language> list = null;
		try {
			list = DaoFactory.getDaoLanguage().list();
		} catch (DaoException e) {
			setError(e.getMessage());
			e.printStackTrace();
		}
		String[] result = new String[list.size()];
		int i = 0;
		for (Language language : list) {
			result[i] = language.getName();
			i++;
		}
		return result;
	}

	/**
	 * Retourne l'objet Language à partir de son nom
	 * 
	 * @param languagesNames nom de la langue
	 * @return objet language correspondant
	 */
	public Language getLanguages(String languagesNames) {
		List<Language> list = null;
		try {
			list = DaoFactory.getDaoLanguage().list();
		} catch (DaoException e) {
			setError(e.getMessage());
			e.printStackTrace();
		}
		Language result = null;
		for (Language language : list) {
			if (language.getName().equalsIgnoreCase(languagesNames)) {
				result = language;
			}
		}
		return result;
	}

	/**
	 * Retourne la liste de tous les noms des sous titres sous le format titre.abrevationlangue
	 * 
	 * @return noms des sous titres
	 */
	public String[] getSubtitlesNames() {
		List<Subtitles> list = null;
		try {
			list = DaoFactory.getDaoSubtitles().list();
		} catch (DaoException e) {
			setError(e.getMessage());
			e.printStackTrace();
		}
		String[] result = new String[list.size()];
		int i = 0;
		for (Subtitles subtitles : list) {
			result[i] = subtitles.getTitle() + "." + subtitles.getLanguage().getAbreviation();
			i++;
		}
		return result;
	}

	/**
	 * Retourne la liste de tous les noms des sous titres originaux sous le format titre.abrevationlangue
	 * 
	 * @return noms des sous titres
	 */
	public String[] getSubtitlesOriginalsNames() {
		List<Subtitles> list = null;
		try {
			list = DaoFactory.getDaoSubtitles().list();
		} catch (DaoException e) {
			setError(e.getMessage());
			e.printStackTrace();
		}

		List<Subtitles> subtitlesOriginals = new ArrayList<Subtitles>();
		for (Subtitles subtitles : list) {
			if (subtitles.getIdOriginal() == 0) {
				subtitlesOriginals.add(subtitles);
			}
		}
		String[] result = new String[subtitlesOriginals.size()];
		int i = 0;
		for (Subtitles subtitles : subtitlesOriginals) {
			result[i] = subtitles.getTitle() + "." + subtitles.getLanguage().getAbreviation();
			i++;
		}
		return result;
	}
	
	/**
	 * Retourne le sous titres à partir de son titre et de sa langue
	 * retourne null si il n'existe pas dans la base de données
	 * 
	 * @param title titre
	 * @param language langue
	 * @return sous titre correspondant
	 */
	private Subtitles getSubtitles(String title, String language) {
		List<Subtitles> list = null;
		try {
			list = DaoFactory.getDaoSubtitles().list();
		} catch (DaoException e) {
			setError(e.getMessage());
			e.printStackTrace();
		}
		Subtitles result = null;
		for (Subtitles subtitles : list) {
			if (subtitles.getTitle().equalsIgnoreCase(title) && subtitles.getLanguage().getAbreviation().equalsIgnoreCase(language)) {
				result = subtitles;
			}
		}
		return result;
	}

	/**
	 * setter de subtitlesOriginal à partir de son titre et de sa langue (recherche dans la base de donné les sous titres correspondant)
	 * 
	 * @param title titre
	 * @param language langue abréviation de la langue
	 */
	public void setSubtitlesOriginal(String title, String language) {
		Subtitles sub = this.getSubtitles(title, language);
		if (sub != null)
			this.subtitlesOriginal = this.getSubtitles(title, language);
		else
			this.setError("Erreur lors du chargement des sous titres");
	}

	/**
	 * setter de subtitlesDestination à partir de son titre et de sa langue: -
	 * recherche dans la base de donné les sous titres correspondant si existant -
	 * sinon : crée l'objet avec le même nombre de ligne (vide) que le
	 * subtitlesOriginal et les mêmes parametres et l'enregistre dans la base de données
	 * 
	 * @param title titre
	 * @param language nom de la langue
	 */
	public void setSubtitlesDestination(String title, String language) {
		Subtitles subtitles = this.getSubtitles(title, this.getLanguages(language).getAbreviation());
		if (subtitles == null) {
			this.subtitlesDestination = new Subtitles();
			this.subtitlesDestination.setLanguage(this.getLanguages(language));
			this.subtitlesDestination.setIdOriginal(this.subtitlesOriginal.getId());
			this.subtitlesDestination.setTitle(title);
			List<SubtitleLine> subtitleLinesDestination = new ArrayList<SubtitleLine>();
			List<SubtitleLine> subtitleLinesOriginal = this.subtitlesOriginal.getsubTitleLines();
			for (SubtitleLine subtitleLineOriginal : subtitleLinesOriginal) {
				SubtitleLine subLine = new SubtitleLine();
				subLine.setId(subtitleLineOriginal.getId());
				subLine.settStart(subtitleLineOriginal.gettStart());
				subLine.settEnd(subtitleLineOriginal.gettEnd());
				subLine.setLine1("");
				subLine.setLine2("");
				subtitleLinesDestination.add(subLine);
			}
			this.subtitlesDestination.setsubTitleLines(subtitleLinesDestination);
			try {
				DaoFactory.getDaoSubtitles().add(subtitlesDestination);
			} catch (DaoException e) {
				setError(e.getMessage());
				e.printStackTrace();
			}
			this.subtitlesDestination = this.getSubtitles(title, this.getLanguages(language).getAbreviation());
		} else {
			this.subtitlesDestination = subtitles;
		}
	}
	
	/**
	 * getter de subtitlesOriginal
	 * 
	 * @return subtitlesOriginal
	 */
	public Subtitles getSubtitlesOriginal() {
		return subtitlesOriginal;
	}
	
	/**
	 * getter de subtitlesDestination
	 * 
	 * @return subtitlesDestination
	 */
	public Subtitles getsubtitlesDestination() {
		return subtitlesDestination;
	}

	/**
	 * set subtitlesDestination et enregistre les modification dans la base de données
	 * @param subtitlesDest
	 */
	public void setSubtitlesDestination(Subtitles subtitlesDest) {
		this.subtitlesDestination = subtitlesDest;
		try {
			DaoFactory.getDaoSubtitles().update(this.subtitlesDestination);
		} catch (DaoException e) {
			this.setError("Erreur lors de l'enregistrement des modifications dans la base de données");
			e.printStackTrace();
		}
	}

	/**
	 * Permet de créer le fichier SRT correspondant au titre et à la langue et de l'enregistrer dans le dossier SRT
	 * @param title titre
	 * @param language langue
	 */
	public void createSRT(String title, String language) {
		String adresse = this.cheminFichier;
		Subtitles sub = this.getSubtitles(title, language);
		SRTFile f = new SRTFile();
		try {
			f.save(sub, adresse);
		} catch (FileException e) {
			this.setError("Erreur lors de l'enregistrement du fichier");
			e.printStackTrace();
		}
	}


	/**
	 * Permet d'éditer un message d'erreur
	 * @param message message d'erreur
	 */
	public void setError(String message) {
		System.out.println(message);
		this.errorMessage = message;
	}
	
	/**
	 * Permet d'obtenir le message d'erreur enregistré et de le réinitialiser
	 * @return
	 */
	public String getError() {
		String s = this.errorMessage;
		this.errorMessage = "";
		return s;
	}
	
	
	/**
	 * Enregistrement d'un fichier SRT uploadé
	 * @param part
	 * @param title
	 * @param languageName
	 * @throws IOException 
	 */
	public void save(Part part, String title, String languageName) throws IOException {
		// On vérifie qu'on a bien reçu un fichier
		String nomFichier = getNomFichier(part);

		// Si on a bien un fichier et qu'il s'agit d'un fichier SRT
		if (nomFichier != null && !nomFichier.isEmpty() && nomFichier.contains(".srt") ) {
			// Corrige un bug du fonctionnement d'Internet Explorer
			nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
					.substring(nomFichier.lastIndexOf('\\') + 1);

			// On écrit définitivement le fichier sur le disque
			ecrireFichier(part, nomFichier, cheminFichier);
		}else {
			this.setError("Erreur lors du chargement du fichier.");
		}
		SRTFile f = new SRTFile();
		Subtitles file = null;
		try {
			file = f.open(this.cheminFichier+nomFichier, this.getLanguages(languageName), title);
		} catch (FileException e) {
			e.printStackTrace();
			this.setError("Erreur lors du chargement du fichier.");
		}
		try {
			DaoFactory.getDaoSubtitles().add(file);
		} catch (DaoException e) {
			e.printStackTrace();
			this.setError("Erreur lors du chargement du fichier.");
		}
	}
	
	/**
	 * Méthode interne de traitement du fichier à uploader (issue du cours)
	 * @param part
	 * @param nomFichier
	 * @param chemin
	 * @throws IOException
	 */
	private void ecrireFichier(Part part, String nomFichier, String chemin) throws IOException {
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			entree = new BufferedInputStream(part.getInputStream(), tailleTampon);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), tailleTampon);
			byte[] tampon = new byte[tailleTampon];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} catch (Exception e) {
			this.setError("Erreur lors du chargement du fichier.");
			e.printStackTrace();
		}
		finally {
			try {
				if(sortie!=null)
					sortie.close();
			} catch (IOException ignore) {
				ignore.printStackTrace();
			}
			try {
				if(entree!=null)
					entree.close();
			} catch (IOException ignore) {
				ignore.printStackTrace();
			}
		}
	}
	
	/**
	 * Méthode interne de traitement du fichier à uploader (issue du cours)
	 * @param part
	 * @return
	 */
	private static String getNomFichier(Part part) {
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
