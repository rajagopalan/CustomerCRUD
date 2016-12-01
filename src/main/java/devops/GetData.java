package devops;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;



public class GetData extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(HexConnection.class);
	
	@Override
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		  
	try {
	HttpSession session = request.getSession();
	AuthUsers user = (AuthUsers) session.getAttribute("user");
	GetDataService data = new GetDataService();
	List<Customer> customers = data.getList(response,user);
	
	request.setAttribute("customers", customers);
	request.getRequestDispatcher("display.jsp").forward(request, response);
	}
	
	catch(Exception e) {
		LOGGER.error(e);
	}
    
	 }

	 

}
