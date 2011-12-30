package fr.adrienbrault.notetonsta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.adrienbrault.notetonsta.entity.Campus;
import fr.adrienbrault.notetonsta.entity.Intervention;
import fr.adrienbrault.notetonsta.entity.Speaker;

public class InterventionDao extends Dao<Intervention, Integer> {

	public InterventionDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<Intervention> findByCampus(Campus campus) {
		Query query = entityManager.createQuery(
			"SELECT i " +
			"FROM " + entityClass.getName() + " i " +
			"WHERE i.campus = :campus "
		);
		query.setParameter("campus", campus);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Intervention> findBySpeaker(Speaker speaker) {
		Query query = entityManager.createQuery(
			"SELECT i " +
			"FROM " + entityClass.getName() + " i " +
			"WHERE i.speaker = :speaker "
		);
		query.setParameter("speaker", speaker);
		
		return query.getResultList();
	}
	
}
