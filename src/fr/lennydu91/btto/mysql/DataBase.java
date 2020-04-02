package fr.lennydu91.btto.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	private String host;
	private String name;
	private String user;
	private String pass;
	private String url;
	private Connection conn;
	
	public DataBase(String h, String n, String u, String p){
		this.host = h;
		this.name = n;
		this.user = u;
		this.pass = p;
		this.url = "jdbc:mysql://"+this.host+"/"+this.name;
	}
	
	public void connection(){
		try {
            Class.forName("com.mysql.jdbc.Driver"); // Ici, on appel la Class du Driver du Connecteur que nous avons importez dans notre Build Path :p
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       
        try {
             this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
             System.out.println("Je me suis connecte a la BDD!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Rt. ma vie est nulle, j'ai pas reussi a me connecter.");
        }
	}
	
	public boolean connected() {
        return this.conn != null;
    }

	private void connectIfNot(){
   	 if (!this.connected()) this.connection();
	}
	
	public void disconnection(){
		if(this.connected()) this.conn = null;
	}
	
	public String getString(String request, String ci){
		this.connectIfNot();
    	try {
            Statement state = this.conn.createStatement();
            ResultSet result = state.executeQuery(request);
		            try {
		                while(result.next()){
		                    return result.getString(ci);
		                }
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
    	 } catch (SQLException e) {
             e.printStackTrace();
         }
    	return "NULL";
    }
	
	public String getValue(String request, String ci){
		this.connectIfNot();
    	try {
            Statement state = this.conn.createStatement();
            ResultSet result = state.executeQuery(request);
		            try {
		                while(result.next()){
		                    return result.getString(ci);
		                }
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
    	 } catch (SQLException e) {
             e.printStackTrace();
         }
    	return "NULL";
    }
	
	public int getInt(String request, int ci){
		this.connectIfNot();
    	try {
            Statement state = this.conn.createStatement();
            ResultSet result = state.executeQuery(request);
                    try {
                        while(result.next()){
                            return result.getInt(ci);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
    	 } catch (SQLException e) {
             e.printStackTrace();
         }
    	return -1;
    }
	
	public void sendRequest(String request){
        this.connectIfNot();
        
        try{
        	Statement state = this.conn.createStatement();
        	state.executeUpdate(request);
        	state.close();
        	System.out.println("Oee! ca marche |-| " + request);
        }
        catch(SQLException e){
            e.printStackTrace();
        	System.out.println("Noo! ca marche pas |-| " + request);
        }
    }
	
	public boolean isInBdd(String request) {
        try {
            Statement state = this.conn.createStatement();
            ResultSet result = state.executeQuery(request);
            if (result.next()) {
                return true;
            }
            return false;
        } 
        catch (Exception e) {
            return false;
        }
    }
}
