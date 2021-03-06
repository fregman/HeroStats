package de.herobrine.fregman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.entity.Player;

 
public class MySQL {
	
	

 
  private static Connection conn = null;
 
  private static String dbHost = "";
 
  private static String dbPort = "3306";

  private static String database = "";

  private static String dbUser = "";

  private static String dbPassword = "";
 
   MySQL() {
    try {
 
      Class.forName("com.mysql.jdbc.Driver");
 
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
 //pr�ft ob Spieler in der DB steht
  public boolean checkName(String name){
	  
	  boolean succ = false;
	  
  conn = getInstance();
  
  if(conn != null)
  {

    @SuppressWarnings("unused")
	Statement query;
    try {
      query = conn.createStatement();

      String sql = "SELECT name FROM hero_player WHERE name= ?";
      
      PreparedStatement ps = conn.prepareStatement(sql);

      ps.setString(1, name);
      ResultSet result = ps.executeQuery();
      
      if (!result.isBeforeFirst()){
    	  succ = false; 
      }
      else {
    	  succ = true;
    	  
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  return succ;
 }
 //Updateted den Onlinestatus
  public void updatePlayer(String playerName, int i) {
	  
	  conn = getInstance();
	  
	  if(conn != null)
	  {


	    try {

	        String updateSql = "UPDATE hero_player " +
	                           "SET name = ?, online = ? " +
	                           "WHERE name = ?";
	        PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateSql);

	        preparedUpdateStatement.setString(1, playerName);

	        preparedUpdateStatement.setInt(2, i);

	        preparedUpdateStatement.setString(3, playerName);

	        preparedUpdateStatement.executeUpdate();
	        
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }

	 
	
	}
 //F�gt den Spieler in die DB ein
  public void insertPlayer(String playerName, int i) {
	  
	  conn = getInstance();
	  
	  if(conn != null)
	  {


	    try {


	    	String sql = "INSERT INTO hero_player (name, online) " +
                    "VALUES(?, ?)";
       PreparedStatement preparedStatement = conn.prepareStatement(sql);
       preparedStatement.setString(1, playerName);
       preparedStatement.setInt(2, i);
       preparedStatement.executeUpdate();

	 
	      }
	     catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }

	 }
 //Setzt den aktuellen Timestamp in der DB
  public void setTimestamp() {
	

	
	  conn = getInstance();
	  
	  if(conn != null)
	  {

	    try {

	        String updateSql = "UPDATE hero_servercheck SET time = NOW()";
	        PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateSql);
		    preparedUpdateStatement.executeUpdate();

		   
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
  }

 //Pr�ft ob Datenbankeintr�ge korrekt sind 
  public void checkList(Player[] player) {
	  
	  ArrayList <String> liste = new ArrayList<String>();
	  for (Player playerOn: player) {
		  
		  liste.add(playerOn.getName());

	  }
	
	  conn = getInstance();
	  
	  if(conn != null)
	  {

	    @SuppressWarnings("unused")
		Statement query;
	    try {
	      query = conn.createStatement();

	      String sql = "SELECT name FROM hero_player WHERE online = '1'";
	      
	      PreparedStatement ps = conn.prepareStatement(sql);


	      ResultSet result = ps.executeQuery();
	      
	     while(result.next()){
	    	 
	    	 if (!liste.contains(result.getString("name"))){
	    		 
	    		 updatePlayer(result.getString("name"), 0);

	    		 
	    	 }
	    	 

	    		
	    	}


	      
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }

	
	
}	  
}