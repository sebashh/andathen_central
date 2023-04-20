package nl.andathen.central.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.andathen.central.beans.ApplicationScopedBean;
import nl.andathen.central.dao.AbstractDao;
import nl.andathen.central.dao.BodyImageDao;
import nl.andathen.central.dao.LanguageDao;
import nl.andathen.central.dao.OrganizationDao;
import nl.andathen.central.dao.ResourceDao;
import nl.andathen.central.dao.SourceDao;
import nl.andathen.central.dao.SpeciesDao;

@RequestScoped
public class DatabaseImageServlet extends HttpServlet {
	private static final long serialVersionUID = -4241835145751881669L;
	@EJB
	private ResourceDao resourceDao;
	@EJB
	private LanguageDao languageDao;
	@EJB
	private SpeciesDao speciesDao;
	@EJB
	private OrganizationDao organizationDao;
	@EJB
	private SourceDao sourceDao;
	@EJB
	private BodyImageDao bodyImageDao;
	@Inject
	private ApplicationScopedBean applicationBean;

	// Provide two parameters, separated by the underscore _:
	// id (the primary key)
	// type (the class indicator, for example "resource")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        // Get type and ID from request.
        String param = request.getParameter("id");

        // Check if param is supplied to the request.
        if (param == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        // Get class from request.
        String imageClass = param.substring(0,param.indexOf("_"));
        
        // Check if class is supplied to the request.
        if (imageClass == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST); //400
            return; 
        }
        
        // Initialize image
        BufferedImage image = null;
        
        // Get id from request
        String idText = param.substring(param.indexOf("_")+1);
        Long id;
        try {
        	id = Long.parseLong(idText);

	        // Read image from the database using the configured dao
	        switch (imageClass) {
	        	case "resource": image = doImageLookup(request, response, resourceDao, id); break;
	        	case "language": image = doImageLookup(request, response, languageDao, id); break;
	        	case "species": image = doImageLookup(request, response, speciesDao, id); break;
	        	case "source": image = doImageLookup(request, response, sourceDao, id); break;
	        	case "organization": image = doImageLookup(request, response, organizationDao, id); break;
	        	case "body": image = doImageLookup(request, response, bodyImageDao, id); break;
	        	default: image = null;
	        }
        }
        catch (NumberFormatException e) {
        	image = ImageIO.read(getServletContext().getResourceAsStream("/WEB-INF/images/no-image-found.jpg"));
        }
        
    	if (image != null) {
    		sendResponse(response, image);
    	}
    	else {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
        }
    }
    
    private void sendResponse(HttpServletResponse response, BufferedImage image) throws IOException {
        // Get as byte array so it can be sent to the client
        byte[] temp = applicationBean.getImageConverter().convertToDatabaseColumn(image);
        
        // Init servlet response.
        response.reset();
        response.setContentType("jpg");
        response.setContentLength(temp.length);

        // Write image content to response.
        response.getOutputStream().write(temp);
    }

    private BufferedImage doImageLookup(HttpServletRequest request, HttpServletResponse response, AbstractDao<?> dao, Long id) throws IOException {
    	// Lookup item by id in database.
    	IImageProvider r = dao.getImageProvider(id);

        // Check if item is actually retrieved from database.
        if (r == null) {
            response.sendError(HttpServletResponse.SC_GONE);
            return null;
        }
        // Retrieve the image from the item
        BufferedImage image = r.getImage();
        
        // Check if image is actually retrieved from database. If not return a default image.
        if (image == null) {
        	image = ImageIO.read(getServletContext().getResourceAsStream("/WEB-INF/images/no-image-found.jpg"));

        }
        return image;
    }
}