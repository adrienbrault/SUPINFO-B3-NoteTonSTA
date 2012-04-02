package fr.adrienbrault.notetonsta.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "interventions")
public class XmlInterventionList {

	private List<XmlIntervention> interventions;
	
	public XmlInterventionList() {
		
	}
	
	public XmlInterventionList(List<XmlIntervention> interventions) {
		this.interventions = interventions;
	}

	@XmlElement(name = "intervention")
	public List<XmlIntervention> getInterventions() {
		return interventions;
	}

	public void setCampuses(List<XmlIntervention> interventions) {
		this.interventions = interventions;
	}
	
	@XmlElement
	public int getCount() {
		return interventions.size();
	}
}
