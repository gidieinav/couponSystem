package DbOperator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import DAO.ConnectionPool;
import Java_Beans.DbComponent;

/**
 * Class Base that handling repeat actions on DB
 * @author Gidi
 */

public abstract class DbOperatorBase {

	public Connection con = null;
	public Statement stmt = null;
	/**
	 * get instance connection from connection pool
	 */
	public void getConnection()
	{
		this.con = ConnectionPool.getInstance().getConnection();
	}
	/**
	 * create statement
	 * @throws SQLException
	 */
	public void createStatement() throws SQLException 
	{
			this.stmt = this.con.createStatement();
	}
	/**
	 * return instance connection to connection pool
	 */
	public void returnConnection()
	{
		ConnectionPool.getInstance().returnConnection(this.con);
	}	
	/**
	 * template of selected repeat actions
	 * @param query
	 * @return ArrayList<DbComponent>
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<DbComponent> buildSelected(String query) throws SQLException, ParseException
	{
		getConnection();
		createStatement();
		ArrayList<DbComponent> dbComponents = runSelectedQueryAndBuildArrayListComponent(stmt, query);
		returnConnection();
		return dbComponents;
	}
	/**
	 * template of writer repeat actions	
	 * @param query
	 * @throws SQLException
	 */
	public void buildWriter(String query) throws SQLException
	{
		getConnection();
		createStatement();
		runWriterQuery(query);
		returnConnection();
	}
	/**
	 * run writer Query	
	 * @param query
	 * @throws SQLException
	 */
	public void runWriterQuery(String query) throws SQLException {
			stmt.execute(query);		
	}
	/**
	 * template of writer repeat actions
	 * @param query
	 * @return long the Component id from DB 
	 * @throws SQLException
	 */
	public long buildDbComponentCreator(String query) throws SQLException
	{
		getConnection();
		long DbComponentId =  createDbComponent(query);
		returnConnection();
		return DbComponentId;
	}
	/**
	 * create DbComponent on DB
	 * @param query
	 * @return long the Component id from DB 
	 * @throws SQLException
	 */
	public long createDbComponent(String query) throws SQLException {

		long DbComponentId = 0;
		PreparedStatement preparedStatement = con.prepareStatement
		(query,Statement.RETURN_GENERATED_KEYS);
		preparedStatement.executeUpdate();
		ResultSet rs =preparedStatement.getGeneratedKeys();
	
		if (rs.next())
		{
			DbComponentId=rs.getLong(1);
		}
		return DbComponentId;
	}
	/**
	 * run SelectedQuery And Build ArrayList Component	
	 * @param stmt
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public abstract ArrayList<DbComponent> runSelectedQueryAndBuildArrayListComponent(Statement stmt, String query) throws SQLException, ParseException;
		
}