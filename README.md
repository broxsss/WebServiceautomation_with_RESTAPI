# serviceautomation_with_RESTAPI

Project which shows how to verify database records are same in MySQL ,MongoDb and ElasticSearch(5.5)

This project basically shows how service automation is done how you verify data on SQL (MySQl) and NoSQL (Mongodb and Elastic search) databases:

Technology used:

MySQL 6.3

MongoDB 3.4.7

Elastic Search 5.5

Code language:

JAVA

Library used:

Elastic Search Java API

MongoDB Java API

RestAssured(Rest Api)

Gson  (parse Jsonobject)

others:

TestNG

properties file

log4j

Objective:

To verify Data present in MySQL dataBase when migrated to MongoDB and Elastic Search should remain same.

In simple word there should be no discrepency in data while migrating from one to another databases.

Indroduction:

1.) Install Mysql workbench in your system.

2.) Install MongoDb in your system and use any tool(Robo3t,mongobooster,Studio3T) to access DB.

3.) Create cluster for Elasticsearch after downloading Zip from Elastic search website.
    you can use elastic search head plugin chrome extension to see your cluster,node,index and you can also use postman to search in         elastic search Db because elastic search uses restapi.

4.) updated other two database mongo and Elastisearch with databasename work and tablename city which comes pre installed in                 MysqlWorkBench.

   I will be updating soon the Wiki page to show how to install all these.


About project:

1.) Start the server program from src/main/java-->com.serviceautomation.startserver
    and run startserver.java
    Wait till all server gets started.
    startserver will initiate a batch file which will start your  3 server pre install before executing this program.
    so in batch file folder a.bat and b.bat is present copy these file and paste this on your desktop or anywhere but change the path in     startserver.java file accordingly.
 
 2.) Goto configuration.properties file change database ,hostname ,port ,index,type to what you have configured.
 
 
 3.)Run test cases present in src/test/java-->com.serviceautomation.serviceautomation_with_RESTAPI
    
    
    Testcases::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
     
     
     
     a.)Singlerecordcompare.java
     In this testcase we queryed one record and then compare that record with mongodb and then with Elastic search .
     In this test cases i have used Elastic search java Api to get record from ES. It will fetch REcord in json then we have to parse        json and get record.
     
     
     b.)Singlerecordcompare_RESTAPI_RestAssured.java
       In this program all is same except i have used restassured library package to get record from ElasticSearch and then compare it          with Mysql record and mongoDb record.So basically we are hitting restapi when ever we query in Elastic Search.
    
    
    C.)Countvalidation.java
       It gives you count of record in sql for table city and count from mongo collection world and count of Elastic search of type            world.
               
    
    
