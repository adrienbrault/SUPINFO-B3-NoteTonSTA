package fr.adrienbrault.notetonsta.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienbrault.notetonsta.dao.CampusDao;
import fr.adrienbrault.notetonsta.dao.InterventionDao;
import fr.adrienbrault.notetonsta.entity.Campus;
import fr.adrienbrault.notetonsta.entity.Intervention;

@WebServlet(urlPatterns = "/interventions")
@SuppressWarnings("serial")
public class CampusInterventionListServlet extends HibernateServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		EntityManager entityManager = createEm();
		
		CampusDao campusDao = new CampusDao(entityManager);
		
		Integer campusId = Integer.parseInt(req.getParameter("campusId"));
		Campus campus = campusDao.findById(campusId);
		
		if (campus == null) {
			return; // 404
		}
		
		InterventionDao interventionDao = new InterventionDao(entityManager);
		
		List<Intervention> interventions = interventionDao.findByCampus(campus);
		
		req.setAttribute("campus", campus);
		req.setAttribute("interventions", interventions);
		
		RequestDispatcher rd = req.getRequestDispatcher("/interventions.jsp");
        rd.forward(req, resp);
	}

}
