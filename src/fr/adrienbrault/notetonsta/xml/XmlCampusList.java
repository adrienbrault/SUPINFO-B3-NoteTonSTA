package fr.adrienbrault.notetonsta.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.adrienbrault.notetonsta.entity.Campus;

@XmlRootElement(name = "campuses")
public class XmlCampusList {

	private List<Campus> campuses;
	
	public XmlCampusList() {
		
	}
	
	public XmlCampusList(List<Campus> campuses) {
		this.campuses = campuses;
	}

	@XmlElement(name="campus")
	public List<Campus> getCampuses() {
		return campuses;
	}

	public void setCampuses(List<Campus> campuses) {
		this.campuses = campuses;
	}
	
	@XmlElement
	public int getCount() {
		return campuses.size();
	}
}
