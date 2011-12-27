package fr.adrienbrault.notetonsta.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienbrault.notetonsta.entity.Speaker;
import fr.adrienbrault.notetonsta.service.PasswordService;

@WebServlet("/login")
@SuppressWarnings("serial")
public class LoginServlet extends HibernateServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		EntityManager em = getEmf().createEntityManager();
		
		Query speakerQuery = em.createQuery("SELECT s FROM Speaker s WHERE s.email = :email");
		speakerQuery.setParameter("email", email);
		Speaker speaker = null;
		try {
			speaker = (Speaker)speakerQuery.getSingleResult();
		} catch (NoResultException e) {
			request.setAttribute("alert_error", "Invalid email.");
		}
		
		if (speaker != null) {
			try {
				String hashedPassword = PasswordService.hashPassword(password, speaker.getSalt());
				
				if (hashedPassword.equals(speaker.getPassword())) {
					request.getSession().setAttribute("speaker_id", speaker.getId());
					
					response.sendRedirect(request.getContextPath() + "/");
					return;
				} else {
					request.setAttribute("alert_error", "Invalid password.");
				}
			} catch (Exception e) {
				request.setAttribute("alert_error", "An error occured: " + e.getLocalizedMessage());
			}
		}
		
		em.close();
		
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
	}
	
}
