package de.herobrine.fregman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 
public class MySQL {
 
  private static Connection conn = null;
 
  // Hostname
  private static String dbHost = "localhost";
 
  // Port -- Standard: 3306
  private static String dbPort = "3306";
 
  // Datenbankname
  private static String database = "**********";
 
  // Datenbankuser
  private static String dbUser = "***********";
 
  // Datenbankpasswort
  private static String dbPassword = "***********";
 
   MySQL() {
    try {
 
      // Datenbanktreiber für ODBC Schnittstellen laden.
      // Für verschiedene ODBC-Datenbanken muss dieser Treiber
      // nur einmal geladen werden.
      Class.forName("com.mysql.jdbc.Driver");
 
      // Verbindung zur ODBC-Datenbank 'sakila' herstellen.
      // Es wird die JDBC-ODBC-Brücke verwendet.
      conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
          + dbPort + "/" + database + "?" + "user=" + dbUser + "&"
          + "password=" + dbPassword);
    } catch (ClassNotFoundException e) {
      System.out.println("Treiber nicht gefunden");
    } catch (SQLException e) {
      System.out.println("Connect nicht moeglich");
    }
  }
 
  private static Connection getInstance()
  {
    if(conn == null)
      new MySQL();
    return conn;
  }
 
  
  /**
   * Fügt einen neuen Datensatz hinzu
   */
  
  public boolean checkName(String name){
	  
	  boolean succ = false;
	  
  conn = getInstance();
  
  if(conn != null)
  {
    // Anfrage-Statement erzeugen.
    @SuppressWarnings("unused")
	Statement query;
    try {
      query = conn.createStatement();

      // Ergebnistabelle erzeugen und abholen.
      String sql = "SELECT name FROM heroplayer WHERE name= ?";
      
      PreparedStatement ps = conn.prepareStatement(sql);
      // Erstes Fragezeichen durch "firstName" Parameter ersetzen
      ps.setString(1, name);
      ResultSet result = ps.executeQuery();
      

     

      // Ergebnissätze durchfahren.
      while (result.next()) {
    	  
    	  String username = result.getString("name");
    	  System.out.println("[HeroStats]Spieler in Datenbank, wird geupdatet: " + username);
    	  succ = true;
 
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
return succ;
 }

public boolean updatePlayer(String playerName, int i) {
	boolean succ = false;
	  
	  conn = getInstance();
	  
	  if(conn != null)
	  {
	    // Anfrage-Statement erzeugen.

	    try {
	        // Ergebnistabelle erzeugen und abholen.
	        String updateSql = "UPDATE heroplayer " +
	                           "SET name = ?, online = ? " +
	                           "WHERE name = ?";
	        PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateSql);
	        // Erstes Fragezeichen durch "firstName" Parameter ersetzen
	        preparedUpdateStatement.setString(1, playerName);
	        // Zweites Fragezeichen durch "lastName" Parameter ersetzen
	        preparedUpdateStatement.setInt(2, i);
	        // Drittes Fragezeichen durch "actorId" Parameter ersetzen
	        preparedUpdateStatement.setString(3, playerName);
	        // SQL ausführen
	        preparedUpdateStatement.executeUpdate();
	         
	        // Es wird der letzte Datensatz abgefragt

	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	return succ;
	 
	
}

public boolean insertPlayer(String playerName, int i) {
	boolean succ = false;
	  
	  conn = getInstance();
	  
	  if(conn != null)
	  {
	    // Anfrage-Statement erzeugen.

	    try {


	    	String sql = "INSERT INTO heroplayer(name, online) " +
                    "VALUES(?, ?)";
       PreparedStatement preparedStatement = conn.prepareStatement(sql);
       // Erstes Fragezeichen durch "firstName" Parameter ersetzen
       preparedStatement.setString(1, playerName);
       // Zweites Fragezeichen durch "lastName" Parameter ersetzen
       preparedStatement.setInt(2, i);
       // SQL ausführen.
       preparedStatement.executeUpdate();
       System.out.println("[HeroStats]Spieler wurde der Datenbank hinzugefügt:" + playerName);
	 
	      }
	     catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	return succ;
	 }

public void setTimestamp() {
	

	
	  conn = getInstance();
	  
	  if(conn != null)
	  {
	    // Anfrage-Statement erzeugen.

	    try {
	    	// Ergebnistabelle erzeugen und abholen.
	        String updateSql = "UPDATE servercheck SET time = NOW()";
	        PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateSql);
		    preparedUpdateStatement.executeUpdate();

		   
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	

}
	  
}