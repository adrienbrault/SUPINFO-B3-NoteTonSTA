package fr.adrienbrault.notetonsta.dao;

import javax.persistence.EntityManager;

import fr.adrienbrault.notetonsta.entity.Evaluation;

public class EvaluationDao extends Dao<Evaluation, Integer> {

	public EvaluationDao(EntityManager entityManager) {
		super(entityManager);
	}
	
}
