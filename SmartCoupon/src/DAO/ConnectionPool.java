package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * A class for ConnectionPool, recycling, and managing JDBC connections
 * @author Gidi
 */

public class ConnectionPool {
	
	// public final static 
	private final static int MAX_CONNECTIONS = 3;
	// Static stuff
	private static ConnectionPool instance = null; // lazy loading
	// URL JDBC
	private final String connectionUrl =  "jdbc:sqlserver://localhost:1433;databaseName=SmartCoupon;integratedSecurity=true;";
	// preapre JDBC driver 
	// JDBC --> please work with MS SQL
	private final String driverString = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// Key for wait/notify
	private Object key = new Object();
	// Set of connections
	private Set<Connection> connections = new HashSet<>();
	// private Ctor.
	private ConnectionPool() {}

	public static synchronized ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			synchronized (key) {
				while (true) {
					if (connections.size() <= MAX_CONNECTIONS) {
						Class.forName(driverString);
						con = DriverManager.getConnection(connectionUrl);
						connections.add(con);
						return con;
					} else {
						System.out.println("No free connection please wait...");
						key.wait();
					}
				}
			}
		} catch (SQLException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void returnConnection(Connection con) {
		try {
			synchronized (key) {
				connections.remove(con);
				key.notify();
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeAllConnection() {
		try {
			synchronized (key) {
				for (Connection c : connections) {
					c.close();
				}
				key.notifyAll();
				System.exit(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
