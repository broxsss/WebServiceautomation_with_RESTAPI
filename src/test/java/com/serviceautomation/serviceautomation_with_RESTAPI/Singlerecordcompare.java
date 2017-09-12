package com.serviceautomation.serviceautomation_with_RESTAPI;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.serviceautomation.DataBasesCon.DBConection;

public class Singlerecordcompare {

	public static final Logger log = Logger.getLogger(Singlerecordcompare.class.getName());
	DBConection db = new DBConection();
	
	@Test
	public void singleRecordValidation() throws IOException, SQLException
	{   /*SQL*/
		String Query="Select * from city where Population='135621'";
		ResultSet rs = db.mysql(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i=1 ;i<=rsmd.getColumnCount();i++)
		{
			System.out.print(rsmd.getColumnName(i)+"  ");
			
		}
		while(rs.next())
		{
	    System.out.println("");
		}
		
  
            //Closing the ResultSet object
             
            try
            {
                if(rs!=null)
                {
                    rs.close();
                    rs=null;
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
		
		
		/*Elastic Search*/
		String strAllresponse = db.Elasticsearch().toString();
		//Converting to JSON
		JsonParser jsonParser = new JsonParser();
		JsonObject elasticJsonResponse = jsonParser.parse(strAllresponse).getAsJsonObject();
	    JsonElement json_elehits = elasticJsonResponse.get("hits").getAsJsonObject().get("hits").getAsJsonArray();
	    System.out.println("json_hits :"+json_elehits);
	    int totalHitsize = elasticJsonResponse.get("hits").getAsJsonObject().get("total").getAsInt();
		System.out.println("totalHitsize:"+totalHitsize);
       log.info("totalHitsize:"+totalHitsize);
       log.info("==================startedverifywithinvalidcredintials=====================");
	}
}
