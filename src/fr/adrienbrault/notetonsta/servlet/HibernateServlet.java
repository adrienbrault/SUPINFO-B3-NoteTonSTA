package fr.adrienbrault.notetonsta.servlet;

import javax.persistence.EntityManager;
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
	
	protected EntityManager createEm() {
		return emf.createEntityManager();
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
