package com.serviceautomation.DataBasesCon;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

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
	    public static void Elasticsearch()
	    {
	    	String strCollectionName="world";
	    	String strType="city";
	    	String query="{\"query\":{\"bool\":{\"must\":[{\"match_all\":{}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}";
	    	Settings settings = Settings.builder()
	    	    .put("cluster.name", "elasticsearch")
	    	    .put("client.transport.sniff", true)
				.build();
	    	
	    	try {
				TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
				// Client client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
				  
				 SearchResponse searchResponse = client.prepareSearch(strCollectionName).setTypes(strType).setFrom(0).setSize(10000).get();
				
//				SearchResponse searchResponse = client.prepareSearch(strCollectionName)
//				                               .setTypes(strType)
//				                               .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//				                               .setQuery(QueryBuilders.termQuery("Population", "135621"))
//				                               .setFrom(0).setSize(10000)
//				                               .setExplain(false)
//				                               .get();
      			System.out.println("Search Response" +searchResponse.toString());
      			SearchHit[] hits = searchResponse.getHits().getHits();
      			StringBuilder response = new StringBuilder("{ ");
      			response.append("\"status\" : " + (searchResponse.status().equals(searchResponse.status().OK) ? "\"success\"" : "\"Failed\""));
      			response.append(" , ");
      			long totalCount = searchResponse.getHits().getTotalHits();
      			response.append("\"count\" : " + totalCount);
      			response.append(" , ");
      			response.append(" \"data\" : [");
      			int counter = 0;
      			for(SearchHit hit : hits) {
      				String result = hit.getSourceAsString();
      				response.append(result);
      				counter++;
      				if(totalCount > 1 && counter != totalCount) {
      					response.append(" , ");
      				}
      			}
      			response.append(" ] ");
      			response.append(" } ");

      			System.err.println(response.toString());
     			client.close();
	    	} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	       
	    }
	
	
	
	public static void main(String a[]) 
	{
		//MongoDBQuery();
		//mysql();
		Elasticsearch();
	}
}
