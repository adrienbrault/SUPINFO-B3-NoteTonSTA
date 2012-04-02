package fr.adrienbrault.notetonsta.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class HibernateResource {
	
	private EntityManagerFactory emf;
	
	public HibernateResource() {
		emf = Persistence.createEntityManagerFactory("PU");
	}
	
	protected EntityManager createEm() {
		return emf.createEntityManager();
	}

}
