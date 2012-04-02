package fr.adrienbrault.notetonsta.xml;

import javax.xml.bind.annotation.XmlRootElement;

import fr.adrienbrault.notetonsta.entity.Evaluation;

@XmlRootElement(name = "data")
public class XmlEvaluationRequest {

	private Evaluation evaluation;
	private Integer interventionId;
	
	public Evaluation getEvaluation() {
		return evaluation;
	}
	
	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	
	public Integer getInterventionId() {
		return interventionId;
	}
	
	public void setInterventionId(Integer interventionId) {
		this.interventionId = interventionId;
	}
	
}
