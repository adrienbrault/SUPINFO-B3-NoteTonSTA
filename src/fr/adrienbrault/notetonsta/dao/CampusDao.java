package fr.adrienbrault.notetonsta.dao;

import javax.persistence.EntityManager;

import fr.adrienbrault.notetonsta.entity.Campus;

public class CampusDao extends Dao<Campus, Integer> {

	public CampusDao(EntityManager entityManager) {
		super(entityManager);
	}
	
}
