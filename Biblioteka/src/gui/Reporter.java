package gui;

 
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;



public class Reporter {
	
	private Connection conn;
	private String templateName;
	private String templatePath;
	private String destinationPath;
	private Map<String, Object> parameters;
	
	
	public Reporter(){
		this.templatePath = "/";
		this.templateName = "izvestaj";
		this.parameters = new HashMap<String,Object>();;
		this.destinationPath = "/home/jovan/Documents/Faks/mbrs/izvestaji";
	}
	
	public Reporter(String templatePath, String templateName,  Map<String, Object> parameters, String destinationPath){
		this.templatePath = templatePath;
		this.templateName = templateName;
		this.parameters = parameters;
		this.destinationPath = destinationPath;
	}
	
	protected void  initConnection(){
        
        String HOST = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        String USERNAME = "mbrs";
        String PASSWORD = "ftn";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	
	protected void  closeConnection(){
		if (conn != null) {
			try { 
				conn.close(); 
			} catch (Exception e) {}
		}
	}
	
	protected void generatePdfReport () {
    	
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		Date date = new Date();
		try {
			//Path to your .jasper file in your package
			
			JasperReport jasperReport = JasperCompileManager.compileReport("/home/jovan/Documents/Faks/mbrs/vezbe/eclipse-workspace/TestProjekat/src/BibliotekaIzvestaj.jrxml");
	        //Get a stream to read the file
	        InputStream is = this.getClass().getClassLoader().getResourceAsStream("BibliotekaIzvestaj.jasper");
			// fills compiled report with parameters and a connection
			JasperPrint print = JasperFillManager.fillReport(is,new HashMap<String, Object> (), conn);
			// exports report to pdf
			JasperExportManager.exportReportToPdfFile(print, destinationPath + templateName +"_"+ dateFormat.format(date) + ".pdf");
			//JOptionPane.showMessageDialog(null, "Uspešno generisan izveštaj.", "Generisan izveštaj", JOptionPane.INFORMATION_MESSAGE);
			if (Desktop.isDesktopSupported()) {
			    try {
			        File myFile = new File(destinationPath + templateName +"_"+ dateFormat.format(date) + ".pdf");
			        Desktop.getDesktop().open(myFile);
			    } catch (IOException ex) {
			        // no application registered for PDFs
			    }
			}
			 
		}catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new RuntimeException("Nije moguće generisati izveštaj.", e);
		} 
    }
	
	public void generateReport() {
		initConnection();
		generatePdfReport();
		closeConnection();
	}
}