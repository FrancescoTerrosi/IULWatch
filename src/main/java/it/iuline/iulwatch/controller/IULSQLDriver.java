package it.iuline.iulwatch.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.iuline.iulwatch.jsonrecords.UserEmail;

@RestController
@RequestMapping("sqltest")
public class IULSQLDriver {

	public Connection getConn(String db_ip, String db_name, String db_user, String db_password)
	{
		Connection conn = null;
		try {
			String connection_string = String.format(
					"jdbc:mysql://%s/%s?user=%s&password=%s",
					db_ip, db_name, db_user, db_password);
		    conn =
		       DriverManager.getConnection(connection_string);


		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	@GetMapping("testsqlconn")
	public List<UserEmail> doSomething(
			@Value("${iul.db1_dbip}") String db_ip,
			@Value("${iul.db1_dbname}") String db_name,
			@Value("${iul.db1_dbuser}") String db_user,
			@Value("${iul.db1_dbpassword}") String db_password,
			@RequestParam String db_table
		)
	{
		Connection conn = this.getConn(db_ip, db_name, db_user, db_password);
		
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<UserEmail> result = new ArrayList<UserEmail>();
		try {
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery(String.format("SELECT * FROM %s", db_table));
	    	rs.next();

		    ResultSetMetaData rs_metadata = null;
		    int column_count = 0;
		    
		    while (!rs.isLast())
		    {
		    	rs_metadata = rs.getMetaData();
			    column_count = rs_metadata.getColumnCount();
			    
			    /*
		    	for (int i = 0; i < column_count; i++)
		    	{
		    		System.out.println(rs.getString("email"));
		    	}
		    	*/
			    
				UserEmail temp_user_email = new UserEmail(rs.getString("name"), rs.getString("email"));
			    result.add(temp_user_email);
		    	rs.next();
		    }
		    
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}

		return result;
		
	}
	
}
