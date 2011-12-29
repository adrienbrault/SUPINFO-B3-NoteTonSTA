package fr.adrienbrault.notetonsta.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienbrault.notetonsta.dao.SpeakerDao;
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
		
		SpeakerDao speakerDao = new SpeakerDao(createEm());
		
		Speaker speaker = speakerDao.findByEmail(email);
		
		if (speaker == null) {
			request.setAttribute("alert_error", "Invalid email.");
		} else {
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
		
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
	}
	
}
