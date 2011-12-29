package fr.adrienbrault.notetonsta.dao;

import javax.persistence.EntityManager;

import fr.adrienbrault.notetonsta.entity.Intervention;

public class InterventionDao extends Dao<Intervention, Integer> {

	public InterventionDao(EntityManager entityManager) {
		super(entityManager);
	}
	
}
