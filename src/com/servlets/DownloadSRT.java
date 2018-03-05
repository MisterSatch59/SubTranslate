package com.servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
	 * Gestion du formulaire de download
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
		model.createSRT(name, language);
		
		fileName+=".srt";
		request.setAttribute("adresse", adresse);
		request.setAttribute("fileName", fileName);
		
		response.setContentType("application/download"); 
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\""); 
	  
		ServletOutputStream out = response.getOutputStream(); 
		File file = null; 
		BufferedInputStream from = null; 
	  
		try {  
			file = new File(adresse+"/"+fileName); 
			response.setContentLength((int) file.length());  
			int bufferSize = 64 * 1024; 
	  
			from = new BufferedInputStream(new FileInputStream(file), bufferSize * 2); 
			byte[] bufferFile = new byte[bufferSize]; 
	  
			for (@SuppressWarnings("unused")int i = 0; ; i++) { 
	  
				int len = from.read(bufferFile);  
	  
				if (len < 0) { 
					break; 
				}  
	  
				out.write(bufferFile, 0, len); 
			} 
			out.flush(); 
	  
		} catch (FileNotFoundException e) {
			model.setError("Erreur du téléchargement du fichier");
			e.printStackTrace(); 
		} catch (IOException e) { 
			model.setError("Erreur du téléchargement du fichier");
			e.printStackTrace(); 
		} finally { 
			if (from != null) { 
				try { 
					from.close(); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
			} 
			if (out != null) { 
				try { 
					out.close(); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
			} 
			if (file != null) { 
				try { 
					file.delete(); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
			} 
		}

	}

}
