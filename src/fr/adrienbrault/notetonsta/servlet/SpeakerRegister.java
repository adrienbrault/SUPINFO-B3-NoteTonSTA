package fr.adrienbrault.notetonsta.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienbrault.notetonsta.entity.Speaker;
import fr.adrienbrault.notetonsta.service.PasswordService;

@WebServlet("/register")
@SuppressWarnings("serial")
public class SpeakerRegister extends HibernateServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManager em = getEmf().createEntityManager();
		
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("password_confirmation");
		
		Map<String, String> errors = new HashMap<String, String>();
		
		if (firstName == null || firstName.length() == 0) {
			errors.put("first_name", "This field is required.");
		}
		
		if (lastName == null || lastName.length() == 0) {
			errors.put("last_name", "This field is required.");
		}
		
		if (email == null || email.length() == 0) {
			errors.put("email", "This field is required.");
		}
		
		Query emailCountQuery = em.createQuery("SELECT COUNT(c) FROM Speaker c WHERE email = :email");
		emailCountQuery.setParameter("email", email.toLowerCase());
		if ((Long)emailCountQuery.getSingleResult() > 0) {
			errors.put("email", "This email is already used.");
		}
		
		if (password == null || password.length() == 0) {
			errors.put("password", "This field is required.");
		}
		
		if (!password.equals(passwordConfirmation)) {
			errors.put("password_confirmation", "This should be the same as the password.");
		}
		
		if (passwordConfirmation == null || passwordConfirmation.length() == 0) {
			errors.put("password_confirmation", "This field is required.");
		}
		
		request.setAttribute("errors", errors);
		
		if (errors.size() == 0) {
			Speaker speaker = new Speaker();
			speaker.setFirstName(firstName);
			speaker.setLastName(lastName);
			speaker.setEmail(email);
			
			speaker.setSalt(PasswordService.generateSalt());
			
			try {
				speaker.setPassword(PasswordService.hashPassword(password, speaker.getSalt()));
			} catch (Exception e) {
				speaker = null;
				request.setAttribute("alert_error", "An error occured: " + e.getLocalizedMessage());
			}
			
			if (speaker != null) {
				em.getTransaction().begin();
				em.persist(speaker);
				em.getTransaction().commit();
				
				em.close();
				
				request.getSession().setAttribute("speaker_id", speaker.getId());
				response.sendRedirect(request.getContextPath() + "/");
				return;
			}
		}
		
		em.close();
		
		RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
        rd.forward(request, response);
	}

}
