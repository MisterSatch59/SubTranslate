package com.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.beans.Language;
import com.beans.SubtitleLine;
import com.beans.Subtitles;
import com.file.File;

/**
 *
 * Classe permettant la lecture et l'enregistrement dans un fichier SRT
 * @author oltenos
 *
 */
public class SRTFile extends File {

	@Override
	public void save(Subtitles file, String adresse) throws FileException {
		String enter = System.getProperty("line.separator");
		List<SubtitleLine> subtitleLines = file.getsubTitleLines();
		String enr = "";
		for (SubtitleLine subtitleLine : subtitleLines) {
			enr += "" + subtitleLine.getId() + enter;
			enr += subtitleLine.gettStart() + " --> " + subtitleLine.gettEnd() + enter;
			enr += subtitleLine.getLine1();
			if (!subtitleLine.getLine2().equals("")) {
				enr += enter + subtitleLine.getLine2();
			}
			enr += enter + enter;
		}

		FileWriter fw;
		BufferedWriter output = null;
		try {
			//TODO A voir où l'enregistrer pour le télécharger ensuite
			fw = new FileWriter(adresse +file.getTitle() + "." +file.getLanguage().getAbreviation() + ".srt", false);
			output = new BufferedWriter(fw);
			output.write(enr);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new FileException("Erreur lors de la création du fichier srt");
		}
	}
	
	@Override
	public Subtitles open(String adresse, Language language, String title) throws FileException {
		Subtitles sub = new Subtitles();
		sub.setTitle(title);
		sub.setLanguage(language);

		List<SubtitleLine> subLines = new ArrayList<SubtitleLine>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(adresse));
			String line;
			while ((line = br.readLine()) != null) {
				SubtitleLine subLine = new SubtitleLine();
				subLine.setId(Integer.valueOf(line.trim()));
				line = br.readLine();
				subLine.settStart((String) line.subSequence(0, 12));
				subLine.settEnd((String) line.subSequence(17, 29));
				line = br.readLine();
				subLine.setLine1(line);

				if ((line = br.readLine()) != null) {
					if (!line.equals("")) {
						subLine.setLine2(line);
						if ((line = br.readLine()) != null) {
						}
					}
				}
				subLines.add(subLine);
			}
			br.close();
		} catch (IOException e) {
			throw new FileException("Erreur lors de la lecture du fichier srt");
		}
		sub.setsubTitleLines(subLines);
		return sub;
	}
};
