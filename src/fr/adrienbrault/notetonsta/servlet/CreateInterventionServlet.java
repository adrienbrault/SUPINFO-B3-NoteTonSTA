package fr.adrienbrault.notetonsta.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

@WebServlet("/intervention/new")
@SuppressWarnings("serial")
public class CreateInterventionServlet extends HibernateServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManager entityManager = createEm();
		
		CampusDao campusDao = new CampusDao(entityManager);
		
		List<Campus> campuses = campusDao.findAll();
		request.setAttribute("campuses", campuses);
		
		RequestDispatcher rd = request.getRequestDispatcher("/createIntervention.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManager entityManager = createEm();
		
		CampusDao campusDao = new CampusDao(entityManager);
		
		List<Campus> campuses = campusDao.findAll();
		request.setAttribute("campuses", campuses);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		
		String subject = request.getParameter("subject");
		Integer campusId = Integer.parseInt(request.getParameter("campus"));
		Date fromDate = null;
		Date toDate = null;
		String description = request.getParameter("description");
		
		if (description != null) {
			description = description.trim();
		}
		
		Campus campus = campusDao.findById(campusId);
		
		try {
			fromDate = sdf.parse(request.getParameter("from"));
		} catch (ParseException e) { }
		
		try {
			toDate = sdf.parse(request.getParameter("to"));
		} catch (ParseException e) { }
		
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		if (subject == null || subject.length() == 0) {
			errors.put("subject", "This field is required.");
		}
		
		if (campus == null) {
			errors.put("campus", "This field is required.");
		}
		
		if (fromDate == null) {
			errors.put("from", "This field is required.");
		} else if (fromDate.after(toDate)) {
			errors.put("from", "This date should be before the end date.");
		}
		
		if (toDate == null) {
			errors.put("to", "This field is required.");
		}
		
		
		
		if (description == null || description.length() == 0) {
			errors.put("description", "This field is required.");
		}
		
		if (errors.size() == 0) {
			Intervention intervention = new Intervention();
			
			intervention.setSubject(subject);
			intervention.setCampus(campus);
			intervention.setDateBegin(fromDate);
			intervention.setDateEnd(toDate);
			intervention.setDescription(description);
			
			InterventionDao interventionDao = new InterventionDao(entityManager);
			
			interventionDao.beginTransaction();
			interventionDao.persist(intervention);
			interventionDao.commitTransaction();
			
			response.sendRedirect(request.getContextPath() + "/intervention?id=" + intervention.getId());
			return;
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/createIntervention.jsp");
        rd.forward(request, response);
	}
	
}
