package devops;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.servlet.http.*;

public class UpdateCustomer extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(HexConnection.class);
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		int paramID = Integer.parseInt(request.getParameter("ID_to_update")); // Emp iD to be
																// updated

		int check;

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String contactNumber = request.getParameter("contactNumber");
		String alternateContactNumber = request.getParameter("alternateContactNumber");
		String specialty = request.getParameter("specialty");
		String qualificationSummary = request.getParameter("qualificationSummary");
	


		HttpSession s1 = request.getSession(false);
		AuthUsers user =(AuthUsers) s1.getAttribute("user");

try {		
		if ( user != null )
		{
		    check = update(name, address, contactNumber, alternateContactNumber, specialty, qualificationSummary, paramID);
		}
		else
		{
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return; // return from the method and stop the execution of the remnant of the code.
		}

		
		
		
		if (check == 0) {
			request.setAttribute("update_fail_message", "Failed to Update Customer Data");
			request.getRequestDispatcher("update.jsp?id="+paramID).forward(request, response);
			
		}

		else {
			
			request.setAttribute("update_success_message", "Customer Data Updated Successfully");
			request.getRequestDispatcher("update.jsp?id="+paramID).forward(request, response);
			
		}
	}

catch (Exception ex)
{
	LOGGER.error(ex);
}
	}



	public int update(String name, String address, String contactNumber, String alternateContactNumber, String specialty, String qualificationSummary, int paramID)
			throws ServletException, IOException {
		int check = 1;
	   	LOGGER.info("Updating Customer Profile..");
	    	
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        
	        try {
	        
	        session.beginTransaction();
	 
	        Customer c1 = 
                    (Customer)session.get(Customer.class,paramID); 
	        c1.setName(name);
	        c1.setAddress(address);
	        c1.setContactNumber(contactNumber);
	        c1.setAlternateContactNumber(alternateContactNumber);
	        c1.setSpecialty(specialty);
	        c1.setQualificationSummary(qualificationSummary);
	        session.update(c1);
	        
	        session.flush();
	        session.getTransaction().commit();
	        }
	       
	        
	        catch(HibernateException e) {
	        	check = 0;
	        	LOGGER.error(e);
	        }
	        finally {
	        	session.close();
	        }
	        

		return check;
	}
	
}
