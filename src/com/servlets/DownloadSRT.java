package com.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Model;

/**
 * Servlet implementation class UploadSRT
 */
@WebServlet("/DownloadSRT")
public class DownloadSRT extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Modèle de l'application
	 */
	private Model model = Model.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadSRT() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String fileName = request.getParameter("subtitlesNames");
		String name = fileName.substring(0, fileName.length() - 3);
		String language = fileName.substring(fileName.length() - 2, fileName.length());
		
		ServletContext context = getServletContext();
		String adresse = context.getRealPath("/WEB-INF/");
		model.setAdresseWebInf(adresse);
		model.download(name, language);
		adresse+="SRT/";
		
		fileName+=".srt";
		request.setAttribute("adresse", adresse);
		request.setAttribute("fileName", fileName);
		
		String error = model.getError();
		request.setAttribute("error", error);
		model.setError("");
				
		this.getServletContext().getRequestDispatcher("/WEB-INF/downloadSRT.jsp").forward(request, response);
	}

}
