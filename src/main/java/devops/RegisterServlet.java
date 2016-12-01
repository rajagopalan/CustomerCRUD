package devops;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
 
 
public class RegisterServlet extends HttpServlet {
	
	
	private static final Logger LOGGER = Logger.getLogger(HexConnection.class);
	
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     

     String email = request.getParameter("email");
     String password = request.getParameter("password");
     
try {             
     if (add(email,password) == 0) {
			request.setAttribute("register_fail_message", "Registration failed");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}

		else {
			request.setAttribute("register_success_message", "New User registered in Database.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

catch(Exception ex)
{
	LOGGER.error(ex);
}

	}

	public int add(String email,String password)
			throws ServletException, IOException {
		int check = 1;
	LOGGER.info("Registering new User...");
 	
     Session session = HibernateUtil.getSessionFactory().openSession();
     
     try {
     
     session.beginTransaction();
     AuthUsers au1 = new AuthUsers();
     
     au1.setEmail(email);
     au1.setPassword(password);
     
     session.saveOrUpdate(au1);
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