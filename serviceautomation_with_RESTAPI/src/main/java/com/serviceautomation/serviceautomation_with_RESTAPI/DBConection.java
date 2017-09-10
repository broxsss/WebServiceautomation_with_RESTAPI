package com.serviceautomation.serviceautomation_with_RESTAPI;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public class DBConection {

	public static void mysql() {	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","root");
             Statement smt = con.createStatement();
             ResultSet rs = smt.executeQuery("select * from city");
             while(rs.next())
             {
              System.out.println("ID :"+rs.getString("ID")+"  Name:"+rs.getString("Name"));
             }
		}
		catch(Exception e)
		{
     System.err.println("ereror   "+e.getMessage());
		}
	}
	
	
	/****************************MongoDB connection********************************************************/
	

  
	    
	    public static void MongoDBQuery() {
	        try {
	            
	            //MongoClient client = new MongoClient("mongodb://localhost/27017");
	            MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	            DB database = client.getDB("world");
	            DBCollection collection = database.getCollection("city");
	            
	            BasicDBObject query = new BasicDBObject();
	            int count = 0;
	            DBCursor cursor = collection.find(query);
	            try {
	                while (cursor.hasNext()) {
	                    BasicDBObject document = (BasicDBObject) cursor.next();
	                    System.out.println(document.toString());
	                    count++;
	                }
	                System.out.println("count  :"+count);
	            } finally {
	                cursor.close();
	            }
	            
	        } catch (MongoException e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
	    }
	    public void Elasticsearch()
	    {
	    	
	    }
	
	
	
	public static void main(String a[]) 
	{
		MongoDBQuery();
		//mysql();
	}
}
