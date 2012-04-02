package fr.adrienbrault.notetonsta.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import fr.adrienbrault.notetonsta.dao.CampusDao;
import fr.adrienbrault.notetonsta.dao.InterventionDao;
import fr.adrienbrault.notetonsta.entity.Campus;
import fr.adrienbrault.notetonsta.xml.XmlCampusList;
import fr.adrienbrault.notetonsta.xml.XmlInterventionConverter;
import fr.adrienbrault.notetonsta.xml.XmlInterventionList;

@Path("/campuses")
public class CampusResource extends HibernateResource {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public XmlCampusList getCampusList() {
		CampusDao campusDao = new CampusDao(createEm());

		return new XmlCampusList(campusDao.findAll());
	}
	
	@GET
	@Path("/{id}/interventions")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public XmlInterventionList getInterventionsByCampusId(@PathParam("id") Integer id) {
		EntityManager entityManager = createEm();
		
		CampusDao campusDao = new CampusDao(entityManager);
		Campus campus = campusDao.findById(id);
		
		if (null == campus) {
			throw new WebApplicationException(404);
		}
		
		InterventionDao interventionDao = new InterventionDao(entityManager);
		
		return XmlInterventionConverter.createXmlInterventionList(interventionDao.findByCampus(campus), entityManager);
	}

}
