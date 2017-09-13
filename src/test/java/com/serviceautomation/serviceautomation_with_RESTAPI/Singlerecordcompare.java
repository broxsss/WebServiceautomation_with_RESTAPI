package com.serviceautomation.serviceautomation_with_RESTAPI;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;
import com.serviceautomation.DataBasesCon.DBConection;

public class Singlerecordcompare {

	public static final Logger log = Logger.getLogger(Singlerecordcompare.class.getName());
	DBConection db = new DBConection();
	
	@Test
	public void singleRecordValidation() throws IOException, SQLException
	{   /*SQL*/
		int sqlID = 0;
		String sqlName = null;
		String sqlcountrycode = null;
		String sqlDistrict=null;
		int sqlpopulation = 0;
		String Query="Select * from city where Population='135621'";
		ResultSet rs = db.mysql(Query);
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i=1 ;i<=rsmd.getColumnCount();i++)
		{
			System.out.print(rsmd.getColumnName(i)+"  ");
			
		}
		System.out.println();
		while(rs.next())
		{
			sqlID=rs.getInt("ID");
			sqlName = rs.getString("Name");
			sqlcountrycode = rs.getString("CountryCode");
			sqlDistrict=rs.getString("District");
			sqlpopulation=rs.getInt("population");
			System.out.println("Mysql record : "+sqlID+"  "+sqlName+"  "+  sqlcountrycode+"  "+  sqlDistrict+"  "+sqlpopulation  ); 
			
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
		/*******************************************MongoDb***************************************************/
            BasicDBObject query = new BasicDBObject();
            query.put("Population", 135621);
            int mdbID = 0;
    		String mdbName = null;
    		String mdbcountrycode = null;
    		String mdbDistrict=null;
    		int mdbpopulation = 0;
            DBCursor cursor =  db.MongoDBQuery(query);
            try {
                while (cursor.hasNext()) {
                    BasicDBObject document = (BasicDBObject) cursor.next();
                    System.out.println(document.toJson());
                    mdbID=document.getInt("ID");
                    mdbName=document.getString("Name");
                    mdbcountrycode=document.getString("CountryCode");
                    mdbDistrict=document.getString("District");
                    mdbpopulation=document.getInt("Population");
              System.out.println("MongoDB record :"+mdbID+" "+mdbName+" "+mdbcountrycode+" "+mdbDistrict+" "+mdbpopulation);      
                 }
            cursor.close();
            } 
            catch(MongoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
            
		
        /*******************************************Elastic search********************************************/
		String strAllresponse = db.Elasticsearch().toString();
		int ESID = 0;
		String ESName = null;
		String EScountrycode = null;
		String ESDistrict=null;
		int ESpopulation = 0;
		//Converting to JSON
		JsonParser jsonParser = new JsonParser();
		JsonObject elasticJsonResponse = jsonParser.parse(strAllresponse).getAsJsonObject();
	    JsonElement json_elehits = elasticJsonResponse.get("hits").getAsJsonObject().get("hits").getAsJsonArray();
	    System.out.println("json_hits :"+json_elehits);
	    int totalHitsize = elasticJsonResponse.get("hits").getAsJsonObject().get("total").getAsInt();
		System.out.println("totalHitsize:"+totalHitsize);
		JsonArray ES_hits = elasticJsonResponse.get("hits").getAsJsonObject().get("hits").getAsJsonArray();
        if(totalHitsize==0)
        {
        	System.out.println("Record not processed...in ES");
        }
        else
        {
        	
        	for(int i =0;i<ES_hits.size();i++)
        	{
        		
        		 JsonObject json_ESHits= ES_hits.get(i).getAsJsonObject();
        		 try
        		 {
        			ESID =  json_ESHits.get("_source").getAsJsonObject().get("ID").getAsInt();
        			ESName = json_ESHits.get("_source").getAsJsonObject().get("Name").getAsString();
        			EScountrycode = json_ESHits.get("_source").getAsJsonObject().get("CountryCode").getAsString();
        			ESDistrict=json_ESHits.get("_source").getAsJsonObject().get("District").getAsString();
        			ESpopulation = json_ESHits.get("_source").getAsJsonObject().get("Population").getAsInt();
        		 System.out.println("ElasticSearch record :"+ESID+"  "+ESName+"  "+EScountrycode+"  "+ESDistrict+"  "+ESpopulation);
        		 }
        		 catch(Exception e)
        		 {
        			e.printStackTrace();
        			System.out.println("Error in ES  :"+e.getMessage());
        		 }
        	}
        	
        }
        
        /***********************************compare Mysql data to Mongo*********************************************/
        if(sqlID!=0 && sqlName!=null && sqlcountrycode!=null  && sqlDistrict!=null  && sqlpopulation!=0   
        		&& sqlID==mdbID && sqlName.equals(mdbName) && sqlcountrycode.equals(mdbcountrycode)  &&
		    sqlDistrict.equals(mdbDistrict)  &&  sqlpopulation==mdbpopulation)
        {
        	System.out.println();
        	System.out.println("Passed	::::Records in MySQL is Equal to MongoDB ");
        	System.out.println();
        }
        else
        {
        	System.out.println("Failed	::::Record in MySQL is Equal Not to MongoDB ");
        	if(sqlID!=mdbID)
        	{
        		System.out.println("Failed	::::ID is not equal:- SQl ID:"+sqlID+"  MongoID :"+mdbID);
        	}
        	if(!sqlName.equals(mdbName))
        	{
        		System.out.println("Failed	::::Name is not equal:- SQl Name:"+sqlName+"  MongoName :"+mdbName);
        	}
        	if(!sqlcountrycode.equals(mdbcountrycode))
        	{
        		System.out.println("Failed	::::countrycode is not equal:- SQl countrycode:"+sqlcountrycode+"  Mongocountrycode :"+mdbcountrycode);	
        	}
        	if(!sqlDistrict.equals(mdbDistrict))
        	{
        		System.out.println("Failed	::::District is not equal:- SQl District:"+sqlDistrict+"  MongoDistrict :"+mdbDistrict);	
        	}
        	if(sqlpopulation!=mdbpopulation)
        	{
        		System.out.println("Failed	::::population is not equal:- SQl population:"+sqlpopulation+"  Mongopopulation :"+mdbpopulation);	
        	}
        	else
        	{
        		System.out.println("Failed	::::Different error");
        	}
        }
        /******************************compare Mysql data to ES**********************************/
        if(sqlID!=0 && sqlName!=null && sqlcountrycode!=null  && sqlDistrict!=null  && sqlpopulation!=0   
        		&& sqlID==ESID && sqlName.equals(ESName) && sqlcountrycode.equals(EScountrycode)  &&
		    sqlDistrict.equals(ESDistrict)  &&  sqlpopulation==ESpopulation)
        {
        	System.out.println();
        	System.out.println("Passed	::::Records in MySQL is Equal to ElasticSearch ");
        	System.out.println();
        }
        else
        {
        	System.out.println("Failed	::::Record in MySQL is Equal Not to ElasticSearch ");
        	if(sqlID!=ESID)
        	{
        		System.out.println("Failed	::::ID is not equal:- SQl ID:"+sqlID+"  ESID :"+ESID);
        	}
        	if(!sqlName.equals(ESName))
        	{
        		System.out.println("Failed	::::Name is not equal:- SQl Name:"+sqlName+"  ESName :"+ESName);
        	}
        	if(!sqlcountrycode.equals(EScountrycode))
        	{
        		System.out.println("Failed	::::countrycode is not equal:- SQl countrycode:"+sqlcountrycode+"  EScountrycode :"+EScountrycode);	
        	}
        	if(!sqlDistrict.equals(ESDistrict))
        	{
        		System.out.println("Failed	::::District is not equal:- SQl District:"+sqlDistrict+"  ESDistrict :"+ESDistrict);	
        	}
        	if(sqlpopulation!=ESpopulation)
        	{
        		System.out.println("Failed	::::population is not equal:- SQl population:"+sqlpopulation+"  ESpopulation :"+ESpopulation);	
        	}
        	else
        	{
        		System.out.println("Failed	::::Different error");
        	}
        } 
	}
}
