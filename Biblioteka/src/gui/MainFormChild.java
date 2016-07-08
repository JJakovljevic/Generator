package gui;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class MainFormChild extends MainForm {
	
	private static MainFormChild instance;
	
	public static MainFormChild getInstance() {
		if (instance == null) {
			instance = new MainFormChild();
		}
		return instance;
	}

	private MainFormChild() {
		super();
		OracleDataSource ods;
		try {
		     	ods = new OracleDataSource();
		        ods.setURL("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		        ods.setUser("mbrs");
		        ods.setPassword("ftn");
		        Connection conn = ods.getConnection();
		         
		        String jobquery = "begin generisanje_opomena; end;";
		        CallableStatement callStmt = conn.prepareCall(jobquery);
		        callStmt.executeQuery();
		         
		        String jobquery2 = "begin istekla_clanarina; end;";
		        CallableStatement callStmt2 = conn.prepareCall(jobquery2);
		        callStmt2.executeQuery();
		         
		     } catch (SQLException e) {
		     	System.out.println(e.getMessage());
		     }
	}
	
}
