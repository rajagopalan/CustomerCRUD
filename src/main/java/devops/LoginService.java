package devops;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

 
public class LoginService {
	
	private static final Logger LOGGER = Logger.getLogger(HexConnection.class);
 
    public boolean authenticateUser(String email1, String password) {
        AuthUsers user = getUserByEmail(email1);  
        boolean result;
        
        if(user!=null && user.getEmail().equals(email1) && user.getPassword().equals(password)){
            result = true;
        }else{
            result = false;
        }
        return result;  // Structured Programming states that every method should have only one return 
    }
 
    public AuthUsers getUserByEmail(String email1) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        AuthUsers user = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            
            String hql = "from AuthUsers where email = :email";
            Query query = session.createQuery(hql).setParameter("email",email1);
            
            //Query query = session.createQuery("from AuthUsers where email='"+email1+"'");
            user = (AuthUsers)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error(e);
        } finally {
            session.close();
        }
        return user;
    }
     
    public List<AuthUsers> getListOfUsers(){
        List<AuthUsers> list = new ArrayList<AuthUsers>();
        Session session = HibernateUtil.openSession();
        Transaction tx = null;       
        try {
            tx = session.getTransaction();
            tx.begin();
            list = session.createQuery("from AuthUsers").list();                       
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            
            LOGGER.error(e);
         
        } finally {
            session.close();
        }
        return list;
    }
}