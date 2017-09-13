package com.serviceautomation.serviceautomation_with_RESTAPI;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.serviceautomation.DataBasesCon.DBConection;
import com.serviceautomation.ESconnectionwithRESTAssured.RestassuredES;

import io.restassured.response.Response;

public class CountValidation {
     
	DBConection db = new DBConection();
	RestassuredES rest = new RestassuredES();
	@Test
	public void countValidation() throws SQLException
	{
		/********************************SQL count ********************************************/
		int Sqlcount=0;
		String Query ="Select * from city";
		ResultSet rs = db.mysql(Query);
		try {
			rs.last();
			Sqlcount=rs.getRow();
			} catch (SQLException e) {
			System.out.println("Error in sql:"+e.getSQLState());
			e.printStackTrace();
		}
		rs.close();
		
		/********************************Mongo count ********************************************/
		
		int mongocount = db.MongoDBQueryCount();
		
		/*********************************ES******************************************************/
		int EScount =0;
		try {
			String response = rest.restcount();
			JsonParser parse = new JsonParser();
			JsonObject es_response = parse.parse(response).getAsJsonObject();
			JsonElement es_element = es_response.get("hits").getAsJsonObject().get("total");
			EScount = es_element.getAsInt();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(Sqlcount!=0 && Sqlcount==mongocount)
		{
			System.out.println("Passed  :::Sql count is equal with Mongo i.e  "+Sqlcount);
		}
		else
		{
			System.out.println("Failed  :::Sql count is not equal with Mongo ::::: SQLCount :"+Sqlcount+" Mongo :"+mongocount);
		}
		if(Sqlcount!=0 && Sqlcount==EScount)
		{
			System.out.println("Passed  :::Sql count is  equal with ES   i.e  "+Sqlcount);
		}
		else
		{
			System.out.println("Failed  :::Sql count is not equal with ES ::::: SQLCount :"+Sqlcount+" ES :"+EScount);
		}
		if(Sqlcount!=0 && mongocount==EScount)
		{
			System.out.println("Passed  :::Mongo count is equal with ES  i.e  "+mongocount);
		}
		else
		{
			System.out.println("Failed  :::Mongo count is not equal with ES ::::: Mongo :"+mongocount+" ES :"+EScount);
		}
	}
	
}
