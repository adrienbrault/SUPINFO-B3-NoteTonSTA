package fr.adrienbrault.notetonsta.servlet;

import fr.adrienbrault.notetonsta.entity.Campus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/")
@SuppressWarnings("serial")
public class CampusChoiceServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("PU");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        createDefaultCampusesIfNeeded(em);
        
        List<Campus> campuses = em.createQuery("SELECT c FROM Campus AS c").getResultList();

        req.setAttribute("campuses", campuses);

        RequestDispatcher rd = req.getRequestDispatcher("/campusChoice.jsp");
        rd.forward(req, resp);
    }

    @Override
    public void destroy() {
        emf.close();
    }

    protected void createDefaultCampusesIfNeeded(EntityManager em) {
        Query query = em.createQuery("SELECT COUNT(c) FROM Campus AS c");
        Long count = (Long)query.getSingleResult();

        if (count == 0) {
            String[] campusesNames = {
                    "Paris",
                    "Marseille",
                    "Lyon",
                    "Rennes",
            };

            em.getTransaction().begin();
            
            for (int i=0; i<campusesNames.length; i++) {
                String campusName = campusesNames[i];
                
                Campus campus = new Campus();
                campus.setName(campusName);
                
                em.persist(campus);
            }
            
            em.getTransaction().commit();
        }
    }

}
