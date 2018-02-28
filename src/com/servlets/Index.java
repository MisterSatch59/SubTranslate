package com.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Language;
import com.beans.Subtitles;
import com.dao.DaoException;
import com.dao.DaoFactory;
import com.file.FileException;
import com.file.SRTFile;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
				
		/*ServletContext context = getServletContext();
		context.getRealPath("/WEB-INF/password_presentation.srt");
		
		SRTFile f = new SRTFile();
		Subtitles file = null;
		try {
			file = f.open(context.getRealPath("/WEB-INF/password_presentation.srt"), new Language(), "password_presentation");
		} catch (FileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(file.toString());
		
		try {
			DaoFactory.getDaoSubtitles().add(file);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		String[] languagesNames = model.getLanguagesNames();
		request.setAttribute("languagesNames", languagesNames);
		
		String[] subtitlesOriginalsNames = model.getSubtitlesOriginalsNames();
		request.setAttribute("subtitlesOriginalsNames", subtitlesOriginalsNames);
		
		String[] subtitlesNames = model.getSubtitlesNames();
		request.setAttribute("subtitlesNames", subtitlesNames);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		
	}

}
