package fr.adrienbrault.notetonsta.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienbrault.notetonsta.dao.EvaluationDao;
import fr.adrienbrault.notetonsta.dao.InterventionDao;
import fr.adrienbrault.notetonsta.entity.Intervention;
import fr.adrienbrault.notetonsta.entity.Speaker;

@WebServlet(urlPatterns = "/interventions/mine")
@SuppressWarnings("serial")
public class SpeakerInterventionListServlet extends HibernateServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManager entityManager = createEm();
		
		Speaker speaker = (Speaker) request.getAttribute("logged_speaker");
		
		if (speaker == null) {
			return; // 404
		}
		
		InterventionDao interventionDao = new InterventionDao(entityManager);
		EvaluationDao evaluationDao = new EvaluationDao(entityManager);
		
		List<Intervention> interventions = interventionDao.findBySpeaker(speaker);
		request.setAttribute("interventions", interventions);
		
		Map<Intervention, Double> marks = new HashMap<Intervention, Double>();
		request.setAttribute("marks", marks);
		
		for (Intervention intervention : interventions) {
			marks.put(intervention, evaluationDao.getAverageMarkByIntervention(intervention));
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/speakerInterventions.jsp");
        rd.forward(request, response);
	}
	
}
