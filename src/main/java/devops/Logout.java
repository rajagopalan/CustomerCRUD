package devops;

import java.io.IOException;   
  

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import org.apache.log4j.Logger;

public class Logout extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(HexConnection.class);
	
	   @Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");    
            
       try {     
            
            request.getRequestDispatcher("login.jsp").include(request, response);  
            HttpSession session=request.getSession();  
            session.removeAttribute("email");
            session.removeAttribute("password");
            session.invalidate(); 
       }
       catch(Exception ex)
       {
    	    LOGGER.error(ex);
       }
              
            LOGGER.info("You are successfully logged out!");  
               
    }  
}