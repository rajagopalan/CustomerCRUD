package devops;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

public class HexConnection {
	private static final Logger LOGGER = Logger.getLogger(HexConnection.class);

	public Connection getConnection() {
		Connection connection = null;
		LOGGER.info("checkConnection() started");

		try {
			Properties prop = new Properties();
		
			String filename = "database_config.properties";
			LOGGER.info("prop.load started" + filename);
			prop.load(getClass().getClassLoader().getResourceAsStream(filename));
			
			LOGGER.info("prop.load ends");
			String connectionURL = prop.getProperty("connectionURL");

			String uname = prop.getProperty("uname");
			String psword = prop.getProperty("psword");
			String driver = prop.getProperty("driver");
			LOGGER.info("Trying to get the driver");
			Class.forName(driver);
			LOGGER.info("Registering driver Success..");
			

			connection = DriverManager.getConnection(connectionURL, uname, psword);
		} catch (Exception e) {
			
			LOGGER.error(e);
		}
		return connection;
	}
}
