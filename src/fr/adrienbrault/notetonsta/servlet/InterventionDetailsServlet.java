package fr.adrienbrault.notetonsta.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienbrault.notetonsta.dao.EvaluationDao;
import fr.adrienbrault.notetonsta.dao.InterventionDao;
import fr.adrienbrault.notetonsta.entity.Intervention;

@WebServlet("/intervention")
@SuppressWarnings("serial")
public class InterventionDetailsServlet extends HibernateServlet implements Servlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		EntityManager entityManager = createEm();
		InterventionDao interventionDao = new InterventionDao(entityManager);
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		Intervention intervention = interventionDao.findById(id);
		
		if (intervention == null) {
			return; // 404
		}
		
		req.setAttribute("intervention", intervention);
		
		// Calculate the marks
		Map<String, Object> marks = new HashMap<String, Object>();
		req.setAttribute("marks", marks);
		
		EvaluationDao evaluationDao = new EvaluationDao(entityManager);
		marks.put("count", evaluationDao.countByIntervention(intervention));
		marks.put("speakerAverage", evaluationDao.getSpeakerAverageMarkByIntervention(intervention));
		marks.put("slidesAverage", evaluationDao.getSlidesAverageMarkByIntervention(intervention));
		marks.put("average", evaluationDao.getAverageMarkByIntervention(intervention));
		
		RequestDispatcher rd = req.getRequestDispatcher("/interventionDetail.jsp");
        rd.forward(req, resp);
	}
	
}
