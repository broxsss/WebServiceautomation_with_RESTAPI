package com.serviceautomation.ESconnectionwithRESTAssured;

import  io.restassured.response.*;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
public class RestassuredES {

	
	
    @Test
	public  void rest()
	{
	String query="{\"query\":{\"bool\":{\"must\":[{\"match_all\":{}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":5000,\"sort\":[],\"aggs\":{}}";
	String strElasticSearchHostName = "http://127.0.0.1";
	String strElasticSearchPort = "9200";
	String strcontractotmIndexName="world";
    String strcontracttypename ="city";
    
/*    RestAssured.baseURI  = "http://127.0.0.1:9200/world";
    Response response =given().contentType("application/json")
    		                 .body(query)
    		                   .when()
    		                   .post("/city");*/
  try{  		                   
    Response response = given().body(query)
            .when().contentType(ContentType.JSON).post(strElasticSearchHostName+":"+strElasticSearchPort
		            +"/"+strcontractotmIndexName+"/"+strcontracttypename+"/_search");
//    
//    Response response = given().when().contentType(ContentType.JSON)
//    		.get(strElasticSearchHostName+":"+strElasticSearchPort
//		            +"/"+strcontractotmIndexName+"/"+strcontracttypename+"/_search");
	System.out.println("Response  :"+response.prettyPrint());
	
	}      
	
	catch(Exception e)
	{
	e.printStackTrace();
	System.out.println(e.getMessage());
	}
	}
}
