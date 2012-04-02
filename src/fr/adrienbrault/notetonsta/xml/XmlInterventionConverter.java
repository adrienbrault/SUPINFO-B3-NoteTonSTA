package fr.adrienbrault.notetonsta.xml;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import fr.adrienbrault.notetonsta.dao.EvaluationDao;
import fr.adrienbrault.notetonsta.entity.Intervention;

public class XmlInterventionConverter {

	static public XmlIntervention createXmlIntervention(Intervention intervention, EvaluationDao evaluationDao) {
		XmlIntervention xmlIntervention = new XmlIntervention(intervention);
		
		xmlIntervention.setEvaluationCount(evaluationDao.countByIntervention(intervention));
		xmlIntervention.setSlidesAverageMark(evaluationDao.getSlidesAverageMarkByIntervention(intervention));
		xmlIntervention.setSpeakerAverageMark(evaluationDao.getSpeakerAverageMarkByIntervention(intervention));
		xmlIntervention.setAverageMark(evaluationDao.getAverageMarkByIntervention(intervention));
		
		return xmlIntervention;
	}
	
	static public XmlInterventionList createXmlInterventionList(List<Intervention> interventions, EntityManager entityManager) {
		EvaluationDao evaluationDao = new EvaluationDao(entityManager);
		
		List<XmlIntervention> xmlInterventions = new ArrayList<XmlIntervention>();
		
		for (Intervention intervention : interventions) {
			xmlInterventions.add(createXmlIntervention(intervention, evaluationDao));
		}
		
		return new XmlInterventionList(xmlInterventions);
	}
	
}
