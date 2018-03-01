package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.model.Model;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Modèle de l'application
	 */
	private Model model = Model.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
    }

	/**
	 * Tranmet les élément nécéssaire de la page d'accueil du modèle vers la vue
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String adresse = getServletContext().getRealPath("/WEB-INF/");
		model.setAdresseWebInf(adresse);


		String[] languagesNames = model.getLanguagesNames();
		request.setAttribute("languagesNames", languagesNames);
		
		String[] subtitlesOriginalsNames = model.getSubtitlesOriginalsNames();
		request.setAttribute("subtitlesOriginalsNames", subtitlesOriginalsNames);
		
		String[] subtitlesNames = model.getSubtitlesNames();
		request.setAttribute("subtitlesNames", subtitlesNames);
		
		String error = model.getError();
		request.setAttribute("error", error);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * Gestion du formulaire d'upload
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String languageName = request.getParameter("languageName");
		
		Part part = request.getPart("fichier");
		
		model.save(part, title, languageName);
		
		String error = model.getError();
		request.setAttribute("error", error);
		
		this.doGet(request, response);
		
	}

}
