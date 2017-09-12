package com.serviceautomation.ESconnectionwithRESTAssured;

import  io.restassured.response.*;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.serviceautomation.properties.Testcasesproperties;

import io.restassured.http.ContentType;
public class RestassuredES {

	Testcasesproperties prop = new Testcasesproperties();
	
    @Test
	public String rest(String query) throws IOException
	{
    	Response response=null;
	
	String strElasticSearchHostName = "http://"+prop.getData("HostUrl");
	String strElasticSearchPort = prop.getData("Port");
	String strcontractotmIndexName=prop.getData("Indexname");
    String strcontracttypename =prop.getData("Typename");
    
   try{
     response = given().body(query)
            .when().contentType(ContentType.JSON).post(strElasticSearchHostName+":"+strElasticSearchPort
		            +"/"+strcontractotmIndexName+"/"+strcontracttypename+"/_search");
    
//    Response response = given().when().contentType(ContentType.JSON)
//    		.get(strElasticSearchHostName+":"+strElasticSearchPort
//		            +"/"+strcontractotmIndexName+"/"+strcontracttypename+"/_search");
	System.out.println("Response  :"+response.asString());
	
	}      
	catch(Exception e)
	{
	e.printStackTrace();
	System.out.println(e.getMessage());
	}
   return response.asString();
	}
}
