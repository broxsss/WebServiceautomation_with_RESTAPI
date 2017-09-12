package com.serviceautomation.DataBasesCon;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.serviceautomation.properties.Testcasesproperties;


public class DBConection {
	private final static Logger Log = LogManager.getLogger(DBConection.class.getName());
    Testcasesproperties prop = new Testcasesproperties();
	public ResultSet  mysql(String Query) {	
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String hostname=prop.getData("HostUrlsql");
			String port = prop.getData("Portsql");
			String database = prop.getData("Database");
			String username=prop.getData("Username");
			String password = prop.getData("Password");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+hostname+":"+port+"/"+database,username,password);
			Statement smt = con.createStatement();
            rs = smt.executeQuery(Query);
           
		}
		catch(Exception e)
		{
            System.err.println("error   "+e.getMessage());
		}
		return rs;
	}
	
	
	/****************************MongoDB connection
	 * @return ********************************************************/
	

  
	    
	    public  DBCursor MongoDBQuery(BasicDBObject query) {
	    	 DBCursor cursor =null;
	    	try {
	            
	            //MongoClient client = new MongoClient("mongodb://localhost/27017");
	            MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	            DB database = client.getDB("world");
	            DBCollection collection = database.getCollection("city");
	            cursor = collection.find(query);
	            /*try {
	                while (cursor.hasNext()) {
	                    BasicDBObject document = (BasicDBObject) cursor.next();
	                    System.out.println(document.toString());
	                    count++;
	                }
	                System.out.println("count  :"+count);
	            } finally {
	                cursor.close();
	            }*/
	            
	        } catch (MongoException e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
	        return cursor;
	    }
	    public SearchResponse Elasticsearch() throws IOException 
	    {
	    	
	    	SearchResponse searchResponse=null;
	    	
	    	try {
	    	String strclustername=prop.getData("Clustername");
	    	String strCollectionName=prop.getData("Indexname");
	    	String strType=prop.getData("Typename");
	    	String strhostname=prop.getData("Hostname");
	    	int strport=Integer.parseInt(prop.getData("PortES"));
	    	 
	    	//String query="{\"query\":{\"bool\":{\"must\":[{\"match_all\":{}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}";
	    	Settings settings = Settings.builder()
	    	    .put("cluster.name", strclustername)
	    	    .put("client.transport.sniff", true)
				.build();
	    	
	    	
				TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(strhostname), strport));
				  
				//searchResponse = client.prepareSearch(strCollectionName).setTypes(strType).setFrom(0).setSize(10000).get();
				
				 searchResponse = client.prepareSearch(strCollectionName)
				                               .setTypes(strType)
				                               .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				                               .setQuery(QueryBuilders.termQuery("Population", "135621"))
				                               .setFrom(0).setSize(10000)
				                               .setExplain(false)
				                               .get();
      			System.out.println("Search Response" +searchResponse.toString());
      			
	    	} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	    	return  searchResponse;      
	    }
	
}
