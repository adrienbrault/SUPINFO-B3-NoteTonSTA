package fr.adrienbrault.notetonsta.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.adrienbrault.notetonsta.entity.Intervention;

@XmlRootElement(name = "intervention")
public class XmlIntervention {

	private Intervention intervention;
	private Long evaluationCount;
	private Double speakerAverageMark;
	private Double slidesAverageMark;
	private Double averageMark;
	
	public XmlIntervention() {
		
	}
	
	public XmlIntervention(Intervention intervention) {
		this.intervention = intervention;
	}
	
	@XmlElement(name = "interventionDetail")
	public Intervention getIntervention() {
		return intervention;
	}
	
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}
	
	public Long getEvaluationCount() {
		return evaluationCount;
	}
	
	public void setEvaluationCount(Long evaluationCount) {
		this.evaluationCount = evaluationCount;
	}
	
	public Double getSpeakerAverageMark() {
		return speakerAverageMark;
	}
	
	public void setSpeakerAverageMark(Double speakerAverageMark) {
		this.speakerAverageMark = speakerAverageMark;
	}
	
	public Double getSlidesAverageMark() {
		return slidesAverageMark;
	}
	
	public void setSlidesAverageMark(Double slidesAverageMark) {
		this.slidesAverageMark = slidesAverageMark;
	}
	
	public Double getAverageMark() {
		return averageMark;
	}
	
	public void setAverageMark(Double averageMark) {
		this.averageMark = averageMark;
	}
	
}