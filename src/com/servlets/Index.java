package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		
		/*SRTFile f = new SRTFile();
		ServletContext context = getServletContext();
		Subtitles subtitles = null;
		try {
			subtitles = f.open(context.getRealPath("/WEB-INF/password_presentation.srt"), new Language(), "test");
		} catch (FileException e) {
			e.printStackTrace();
		}*/

		List<Subtitles> list = null;
		try {
			list = DaoFactory.getDaoSubtitles().list();
		} catch (DaoException e) {
			e.printStackTrace();
		}

		for (Iterator<Subtitles> iterator = list.iterator(); iterator.hasNext();) {
			Subtitles subtitles1 = (Subtitles) iterator.next();
			try {
				DaoFactory.getDaoSubtitles().delete(subtitles1.getId());
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		System.out.println("list : " + list.toString());
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		
	}

}
