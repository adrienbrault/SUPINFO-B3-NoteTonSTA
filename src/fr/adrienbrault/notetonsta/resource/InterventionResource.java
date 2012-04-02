package fr.adrienbrault.notetonsta.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import fr.adrienbrault.notetonsta.dao.EvaluationDao;
import fr.adrienbrault.notetonsta.dao.InterventionDao;
import fr.adrienbrault.notetonsta.entity.Intervention;
import fr.adrienbrault.notetonsta.xml.XmlIntervention;
import fr.adrienbrault.notetonsta.xml.XmlInterventionConverter;

@Path("/interventions")
public class InterventionResource extends HibernateResource {
	
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public XmlIntervention getIntervention(@PathParam("id") Integer id) {
		EntityManager entityManager = createEm();
		InterventionDao interventionDao = new InterventionDao(entityManager);
		Intervention intervention = interventionDao.findById(id);
		
		if (null == intervention) {
			throw new WebApplicationException(404);
		}
		
		return XmlInterventionConverter.createXmlIntervention(intervention, new EvaluationDao(entityManager));
	}

}
