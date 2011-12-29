package fr.adrienbrault.notetonsta.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.adrienbrault.notetonsta.entity.Evaluation;
import fr.adrienbrault.notetonsta.entity.Intervention;

public class EvaluationDao extends Dao<Evaluation, Integer> {

	public EvaluationDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Long countByIntervention(Intervention intervention) {
		Query query = entityManager.createQuery(
			"SELECT COUNT(e) " +
			"FROM " + entityClass.getName() + " e " +
			"WHERE e.intervention = :intervention"
		);
		query.setParameter("intervention", intervention);
		
		return (Long)query.getSingleResult();
	}
	
	public Double getSpeakerAverageMarkByIntervention(Intervention intervention) {
		Query query = entityManager.createQuery(
			"SELECT AVG( (e.speakerKnowledgeMark + e.speakerTeachingMark + e.speakerAnswersMark) / 3 ) " +
			"FROM " + entityClass.getName() + " e " +
			"WHERE e.intervention = :intervention"
		);
		query.setParameter("intervention", intervention);
		
		return (Double)query.getSingleResult();
	}
	
	public Double getSlidesAverageMarkByIntervention(Intervention intervention) {
		Query query = entityManager.createQuery(
			"SELECT AVG( (e.slidesContentMark + e.slidesFormatMark + e.slidesExamplesMark) / 3 ) " +
			"FROM " + entityClass.getName() + " e " +
			"WHERE e.intervention = :intervention"
		);
		query.setParameter("intervention", intervention);
		
		return (Double)query.getSingleResult();
	}
	
	public Double getAverageMarkByIntervention(Intervention intervention) {
		Query query = entityManager.createQuery(
			"SELECT AVG( (e.slidesContentMark + e.slidesFormatMark + e.slidesExamplesMark + e.speakerKnowledgeMark + e.speakerTeachingMark + e.speakerAnswersMark) / 6 ) " +
			"FROM " + entityClass.getName() + " e " +
			"WHERE e.intervention = :intervention"
		);
		query.setParameter("intervention", intervention);
		
		return (Double)query.getSingleResult();
	}
	
}
