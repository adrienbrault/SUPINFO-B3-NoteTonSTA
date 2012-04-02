package fr.adrienbrault.notetonsta.resource;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.adrienbrault.notetonsta.dao.EvaluationDao;
import fr.adrienbrault.notetonsta.dao.InterventionDao;
import fr.adrienbrault.notetonsta.entity.Evaluation;
import fr.adrienbrault.notetonsta.entity.Intervention;
import fr.adrienbrault.notetonsta.xml.XmlEvaluationRequest;

@Path("/evaluations")
public class EvaluationResource extends HibernateResource {

	@GET
	@Path("/{id}")
	public Evaluation getEvaluation(@PathParam("id") Integer id) {
		EvaluationDao evaluationDao = new EvaluationDao(createEm());
		
		Evaluation evaluation = evaluationDao.findById(id);
		
		if (null == evaluation) {
			throw new WebApplicationException(404);
		}
		
		return evaluation;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response evaluateIntervention(XmlEvaluationRequest xmlEvaluationRequest) {
		EntityManager entityManager = createEm();
		InterventionDao interventionDao = new InterventionDao(entityManager);
		
		Intervention intervention = interventionDao.findById(xmlEvaluationRequest.getInterventionId());
		
		if (null == intervention) {
			throw new WebApplicationException(404);
		}
		
		EvaluationDao evaluationDao = new EvaluationDao(entityManager);
		
		Evaluation evaluation = xmlEvaluationRequest.getEvaluation();
		
		if (evaluationDao.countByIdBooster(evaluation.getIdBooster()) > 0) {
			throw new WebApplicationException(403);
		}
		
		evaluation.setIntervention(intervention);
		
		entityManager.getTransaction().begin();
		entityManager.persist(evaluation);
		entityManager.getTransaction().commit();
		
		return Response.created(URI.create("/" + evaluation.getId())).build();
	}
	
}
