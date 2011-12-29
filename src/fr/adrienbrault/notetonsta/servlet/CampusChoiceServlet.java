package fr.adrienbrault.notetonsta.servlet;

import fr.adrienbrault.notetonsta.dao.CampusDao;
import fr.adrienbrault.notetonsta.entity.Campus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/campus/select")
@SuppressWarnings("serial")
public class CampusChoiceServlet extends HibernateServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CampusDao campusDao = new CampusDao(createEm());
        
        createDefaultCampusesIfNeeded(campusDao);
        
        List<Campus> campuses = campusDao.findAll();

        req.setAttribute("campuses", campuses);

        RequestDispatcher rd = req.getRequestDispatcher("/campusChoice.jsp");
        rd.forward(req, resp);
    }

    protected void createDefaultCampusesIfNeeded(CampusDao campusDao) {
        if (campusDao.countAll() == 0) {
            String[] campusesNames = {
                    "Paris",
                    "Marseille",
                    "Lyon",
                    "Rennes",
            };

            campusDao.beginTransaction();
            
            for (int i=0; i<campusesNames.length; i++) {
                String campusName = campusesNames[i];
                
                Campus campus = new Campus();
                campus.setName(campusName);
                
                campusDao.persist(campus);
            }
            
            campusDao.commitTransaction();
        }
    }

}
