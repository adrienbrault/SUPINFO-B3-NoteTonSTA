package fr.adrienbrault.notetonsta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienbrault.notetonsta.dao.InterventionDao;
import fr.adrienbrault.notetonsta.entity.Intervention;
import fr.adrienbrault.notetonsta.entity.Speaker;

@WebServlet("/intervention/delete")
@SuppressWarnings("serial")
public class DeleteInterventionServlet extends HibernateServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Speaker speaker = (Speaker) request.getAttribute("logged_speaker");
		
		if (speaker == null) {
			return; // 403
		}
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		if (id == null) {
			return; // 404
		}
		
		InterventionDao interventionDao = new InterventionDao(createEm());
		
		Intervention intervention = interventionDao.findById(id);
		
		if (intervention == null || !intervention.getSpeaker().getId().equals(speaker.getId())) {
			return; // 403/404
		}
		
		interventionDao.beginTransaction();
		interventionDao.remove(intervention);
		interventionDao.commitTransaction();
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
