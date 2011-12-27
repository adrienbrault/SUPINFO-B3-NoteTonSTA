package fr.adrienbrault.notetonsta.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienbrault.notetonsta.entity.Speaker;

@WebFilter(urlPatterns = "/*")
public class SpeakerSessionFilter implements Filter {

    EntityManagerFactory emf;

	public void destroy() {
		emf.close();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		httpResponse.getWriter().println("YO");
		
		Integer speakerId = (Integer) httpRequest.getSession().getAttribute("speaker_id");
		if (speakerId != null) {
			EntityManager em = emf.createEntityManager();
			
			Query query = em.createQuery("SELECT s FROM Speaker s WHERE s.id = :id");
			query.setParameter("id", speakerId);
			
			try {
				Speaker speaker = (Speaker)query.getSingleResult();
				request.setAttribute("logged_speaker", speaker);
			} catch (NoResultException e) {
				// The speaker_id is incorrect
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
				return;
			}
					
			em.close();
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		emf = Persistence.createEntityManagerFactory("PU");
	}

}
