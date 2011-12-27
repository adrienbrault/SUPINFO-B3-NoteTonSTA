package fr.adrienbrault.notetonsta.servlet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public abstract class HibernateServlet extends HttpServlet {
	
	private EntityManagerFactory emf;
	
	protected EntityManagerFactory getEmf() {
		return this.emf;
	}

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("PU");
    }
    
    @Override
    public void destroy() {
        emf.close();
    }

}
