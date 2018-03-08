package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Model;
import com.beans.SubtitleLine;
import com.beans.Subtitles;



/**
 * Servlet implementation class EditSubtitle
 */
@WebServlet("/EditSubtitle" )
public class EditSubtitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Modèle de l'application
	 */
	private Model model = Model.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditSubtitle() {
		super();
	}

	/**
	 * Affichage de la page de traduction avec le travail déjà réalisé
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String subtitlesNames = request.getParameter("subtitlesOriginalsNames");
		String name = subtitlesNames.substring(0, subtitlesNames.length() - 3);
		String language = subtitlesNames.substring(subtitlesNames.length() - 2, subtitlesNames.length());
		model.setSubtitlesOriginal(name, language);

		String languageNamesDest = request.getParameter("languagesNameDest");
		model.setSubtitlesDestination(name, languageNamesDest);
		

		Subtitles subtitlesOr = model.getSubtitlesOriginal();
		List<SubtitleLine> subTitleLinesOr = subtitlesOr.getsubTitleLines();
		String[][] original = new String[subTitleLinesOr.size()][4];
		int i = 0;
		for (SubtitleLine subtitleLine : subTitleLinesOr) {
			original[i] = affiche(subtitleLine);
			i++;
		}
		request.setAttribute("original", original);

		Subtitles subtitlesDest = model.getsubtitlesDestination();
		List<SubtitleLine> subTitleLinesDest = subtitlesDest.getsubTitleLines();
		String[][] destination = new String[subTitleLinesDest.size()][4];
		i=0;
		for (SubtitleLine subtitleLine : subTitleLinesDest) {
			destination[i] = affiche(subtitleLine);
			i++;
		}
		request.setAttribute("destination", destination);

		String error = model.getError();
		request.setAttribute("error", error);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/editSubtitle.jsp").forward(request, response);

	}

	/**
	 * Enregistrement des modifications réalisés dans la traduction
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Subtitles subtitlesDest = model.getsubtitlesDestination();
		
		List<SubtitleLine> subTitleLines = subtitlesDest.getsubTitleLines();
		for (SubtitleLine subtitleLine : subTitleLines) {
			int j= (subtitleLine.getId()-1);
			subtitleLine.setLine1(request.getParameter("line1-"+j));
			subtitleLine.setLine2(request.getParameter("line2-"+j));
		}
		subtitlesDest.setsubTitleLines(subTitleLines);
		model.setSubtitlesDestination(subtitlesDest);
		
		request.setAttribute("fin", "Les modifications ont été enregistrées");
		
		Subtitles subtitlesOr = model.getSubtitlesOriginal();
		List<SubtitleLine> subTitleLinesOr = subtitlesOr.getsubTitleLines();
		String[][] original = new String[subTitleLinesOr.size()][4];
		int i = 0;
		for (SubtitleLine subtitleLine : subTitleLinesOr) {
			original[i] = affiche(subtitleLine);
			i++;
		}
		request.setAttribute("original", original);
		
		subtitlesDest = model.getsubtitlesDestination();
		List<SubtitleLine> subTitleLinesDest = subtitlesDest.getsubTitleLines();
		String[][] destination = new String[subTitleLinesDest.size()][4];
		i=0;
		for (SubtitleLine subtitleLine : subTitleLinesDest) {
			destination[i] = affiche(subtitleLine);
			i++;
		}
		request.setAttribute("destination", destination);

		String error = model.getError();
		request.setAttribute("error", error);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/editSubtitle.jsp").forward(request, response);
	}

	/**
	 * Retourne le tableau de String nécéssaire à l'affichage d'un SubtitleLine
	 * @param subLine
	 * @return
	 */
	private String[] affiche(SubtitleLine subLine) {
		String[] tab = new String[4];
		tab[0] = "" + subLine.getId();
		tab[1] = subLine.gettStart() + " --> " + subLine.gettEnd();
		tab[2] = subLine.getLine1();
		tab[3] = subLine.getLine2();
		return tab;
	}

}
