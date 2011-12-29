package fr.adrienbrault.notetonsta.filter;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienbrault.notetonsta.dao.SpeakerDao;
import fr.adrienbrault.notetonsta.entity.Speaker;

@WebFilter(urlPatterns = "/*")
public class SpeakerSessionFilter implements Filter {

    EntityManagerFactory emf;

	public void init(FilterConfig fConfig) throws ServletException {
		emf = Persistence.createEntityManagerFactory("PU");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		Integer speakerId = (Integer) httpRequest.getSession().getAttribute("speaker_id");
		if (speakerId != null) {
			SpeakerDao speakerDao = new SpeakerDao(emf.createEntityManager());
			
			Speaker speaker = speakerDao.findById(speakerId);
			
			if (speaker == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
				return;
			}
			
			request.setAttribute("logged_speaker", speaker);
		}
		
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		emf.close();
	}

}
