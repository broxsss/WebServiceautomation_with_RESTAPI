package com.serviceautomation.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PropertyConfigurator;


public class Report
{
	int value = 1;
	String path12 = System.getProperty("user.dir");
	String projectName = this.path12.substring(this.path12.lastIndexOf("\\") + 1);
	String fileName = null;
	private static String reportsDirectoryPath = null;
	static String errorMsg = "";
	static String logMsg = "";
	protected static int count1 = 1;
	static int imageCounter = 1;
	static String dateTime = "";
	static String errorSummary = null;
	static String errorMsgCode = null;
	static String errorStepToRep = null;
	static double totalTimeTaken = 0.0D;
	private static String failedHtmlPath = null;
	private static String failedScreenshotPath = null;
	private static String actuvalResult = null;
	private static String expectedResult = null;
	String path = "";
	public static String htmlPath = "";
	public static final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(Report.class.getName());
//	static Properties log4jProperties = PropertiesFileReader.getInstance().readProperties("log4j.properties");
	public static String log4jPath = "";
	private static int count = 1;
	static int passedcount = 0;
	static int failedcount = 0;
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Report.class.getName());
	static String imageName;

	public void setReportDirPath(String path)
	{
		reportsDirectoryPath = path;
	}

	public static void clearErrorSumm()
	{
		errorMsgCode = "";
		errorStepToRep = "";
		errorMsgCode = "";
	}

	public String getReportDirPath()
	{
		return reportsDirectoryPath;
	}

	public static void clearErrorMsg()
	{
		errorMsg = "";
		logMsg = "";
	}

	public static String getErrorMsgCode()
	{
		return errorMsgCode;
	}

	public static void setErrorMsgCode(String errMsgcode)
	{
		errorMsgCode = errMsgcode;
	}

	public static String getErrorStepToRep()
	{
		return errorStepToRep;
	}

	public static void setErrorStepToRep(String errStepToRep)
	{
		errorStepToRep = errStepToRep;
	}

	public static String getErrorSummary()
	{
		return errorSummary;
	}

	public static void setErrorSummary(String errSummary)
	{
		errorSummary = errSummary;
	}

	public static String getErrorMsg()
	{
		return errorMsg;
	}

	public static void setErrorMsg(String error)
	{
		errorMsg = error;
	}

	public static String getDateTime()
	{
		return dateTime;
	}

	public static void setDateTime(String dateTime)
	{
		Report.dateTime = dateTime;
	}

	public static String getFailedHtmlPath()
	{
		return failedHtmlPath;
	}

	public static void setFailedHtmlPath(String failedHtmlPath)
	{
		Report.failedHtmlPath = failedHtmlPath;
	}

	public static String getFailedScreenshotPath()
	{
		return failedScreenshotPath;
	}

	public static void setFailedScreenshotPath(String failedScreenshotPath)
	{
		Report.failedScreenshotPath = failedScreenshotPath;
	}

	public static String getActuvalResult()
	{
		return actuvalResult;
	}

	public static void setActuvalResult(String actuvalResult)
	{
		Report.actuvalResult = actuvalResult;
	}

	public static String getExpectedResult()
	{
		return expectedResult;
	}

	public static void setExpectedResult(String expectedResult)
	{
		Report.expectedResult = expectedResult;
	}

	public void createReportFolders()
	{
		createFolders();
	}

	public BufferedWriter createlogfile(String Filename, String dataId)
	{
		BufferedWriter writer = null;
		int count = 1;
		try
		{
			File dir = new File(reportsDirectoryPath);

			String logsDirectoryPath = String.valueOf(reportsDirectoryPath) + System.getProperty("file.separator") + "Reports(" + getDateTime() + ")" + System.getProperty("file.separator") + "logs";
			dir = new File(logsDirectoryPath);
			if (!dir.exists()) {
				dir.mkdir();
			}
			File file;
			if ((file = new File(this.path = String.valueOf(logsDirectoryPath) + System.getProperty("file.separator") + Filename + "_" + "DataId" + "_" + dataId + "_" + count + ".html")).exists())
			{
				this.path = (String.valueOf(logsDirectoryPath) + System.getProperty("file.separator") + Filename + "_" + "DataId" + "_" + dataId + "_" + ++count + ".html");
				file = new File(this.path);
			}
			htmlPath = this.path;
			setFailedHtmlPath(htmlPath);
			System.out.println(""+file.getPath());
			file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file));
			// writer.write("<html><head><link href='http://fdk-stage.cisco.com/content/dam/en/i/test/style.css' rel='stylesheet' type='text/css' /></head><hr class='divline'><table class='reportheader' width=100%><TR><td height=50px align=left><img src = 'http://fdk-stage.cisco.com/content/dam/en/i/test/cisco_logo.jpg'></TD><BR><td height=50px align=right><Table class='developer'><TR><td class='desc1'><Center><table><TR><TD class='desccpy'>Automation Test Execution Report</TD></TR><TR><TD class='dev'>Tool Used : Selenium WebDriver </TD></TR></Table></TD></TR></Table></td><BR></tr></table><hr class='divline'><BR><table class='subheader' width=100%><tr><tr><td width=100% class='subheader'>Test Case Desription - " + Filename + "</td></tr><tr><td width=100% class='subcontents'></td></tr></tr></table> <hr class='divline'><BR>");
		      
		     // writer.write("<table class='teststeps' width=100%><tr><td class='tsheader' width=75px>Step #</td><TD class='tsheader' width=155px>Step Description</TD><TD class='tsheader' width=285px>Expected Result</TD><TD class='tsheader' width=285px>Actual Result</TD><TD class='tsheader' width=50px>Status</TD><TD class='tsheader' width=50px>Screen shot</TD></tr>");

			Date Now = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a zzz");

			String currentDateTime = ft.format(Now);
			writer.write(""
					+ "<!------------ Initialize Report Start -------------------->"
					+ "<!DOCTYPE html>"
					+ "<html lang='en'>"
					+ "   <head>"
					+ "      <title>"
					+ "         Automation Execution Summary Report - Cisco"
					+ "      </title>"
					+ "      <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>"
					+ "      <meta charset='utf-8'>"
					+ "      <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css'>"
					+ "      <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>"
					+ "      <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>"
					+ "      <script type='text/javascript' src='https://www.google.com/jsapi'></script>"
					+ "      <script type='text/javascript'>"
					+ "         google.load('visualization', '1', {packages:['corechart']});"
					+ "         google.setOnLoadCallback(drawChart);"
					+ "         function drawChart() {"
					+ "         "
					+ "           var data = google.visualization.arrayToDataTable(["
					+ "             ['Test Scenario', 'Number of testcases'], ['Cost Rollup',     5],['MRPI',      3],  ['DCR',  9], ['Special Orders', 19], ['Expedite',    9]"
					+ "           ]);"
					+ "           var options = {"
					+ "             title: 'Tests Automated'"
					+ "           };"
					+ "         "
					+ "           var chart = new google.visualization.PieChart(document.getElementById('piechart'));"
					+ "                    chart.draw(data, options);"
					+ "        }"
					+ "      </script>"
					+ "      <meta name='viewport' content='width=device-width, initial-scale=1'>"
					+ "      <style type='text/css'>"
					+ "	  .jumbotron_report,.jumbotron_report_fail{color:#FFF;-webkit-box-shadow:inset 0 3px 7px rgba(0,0,0,.2),inset 0 -3px 7px rgba(0,0,0,.2);-moz-box-shadow:inset 0 3px 7px rgba(0,0,0,.2),inset 0 -3px 7px rgba(0,0,0,.2);padding-top:27px;padding-bottom:27px;filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#00aaaaaa', endColorstr='#a6aaaaaa', GradientType=0 )}.subtle,.tsheader,.tsindlevel1{font-weight:700;text-shadow:none}.jumbotron,.jumbotron_report,.jumbotron_report_fail{filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#00aaaaaa', endColorstr='#a6aaaaaa', GradientType=0 )}.logo-main,.timer strong{font-size:27px}.footer p,.sticky{text-align:center}@font-face{font-family:GraublauWeb;src:url(fonts/trenchLogo.otf) format('opentype')}@font-face{font-family:GraublauWeb;font-weight:700;src:url(fonts/trenchLogo.otf) format('opentype')}.jumbotron_report{background:-moz-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-webkit-gradient(linear,left top,left bottom,color-stop(0,rgba(170,170,170,0)),color-stop(100%,rgba(170,170,170,.65)));background:-webkit-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-o-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-ms-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:linear-gradient(to bottom,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-webkit-linear-gradient(45deg,#270C3C 0,#106B30 100%);background:-o-linear-gradient(45deg,#270C3C 0,#106B30 100%);background:-ms-linear-gradient(45deg,#270C3C 0,#106B30 100%);background:linear-gradient(45deg,#270C3C 0,#106B30 100%);box-shadow:inset 0 3px 7px rgba(0,0,0,.2),inset 0 -3px 7px rgba(0,0,0,.2)}.jumbotron_report_fail{background:-moz-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-webkit-gradient(linear,left top,left bottom,color-stop(0,rgba(170,170,170,0)),color-stop(100%,rgba(170,170,170,.65)));background:-webkit-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-o-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-ms-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:linear-gradient(to bottom,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-webkit-linear-gradient(45deg,#230C3C 0,#6B1010 100%);background:-o-linear-gradient(45deg,#230C3C 0,#6B1010 100%);background:-ms-linear-gradient(45deg,#230C3C 0,#6B1010 100%);background:linear-gradient(45deg,#230C3C 0,#6B1010 100%);box-shadow:inset 0 3px 7px rgba(0,0,0,.2),inset 0 -3px 7px rgba(0,0,0,.2)}.fail-link,.fail-link : hover,.fail-link :active,.fail-link:visited{color:#000}.tsheader{background:#424242;color:#FFF}.subtle{background:#CCC;color:#000}.tsindlevel1{background:#424242;color:#FFF}.table-bordered>tbody>tr>td,.table-bordered>tbody>tr>th,.table-bordered>tfoot>tr>td,.table-bordered>tfoot>tr>th,.table-bordered>thead>tr>td,.table-bordered>thead>tr>th{border:1px solid #ABABAB}.jumbotron{background:-moz-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-webkit-gradient(linear,left top,left bottom,color-stop(0,rgba(170,170,170,0)),color-stop(100%,rgba(170,170,170,.65)));background:-webkit-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-o-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-ms-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:linear-gradient(to bottom,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);-webkit-box-shadow:inset 0 3px 7px rgba(0,0,0,.2),inset 0 -3px 7px rgba(0,0,0,.2);-moz-box-shadow:inset 0 3px 7px rgba(0,0,0,.2),inset 0 -3px 7px rgba(0,0,0,.2);box-shadow:inset 0 3px 7px rgba(0,0,0,.2),inset 0 -3px 7px rgba(0,0,0,.2)}body{//color:#CCC;background:#FFF;/*text-shadow:1px 1px 2px rgba(150,150,150,1)*/}a,a:active,a:focus,a:hover,a:visited,a[href]:after,a[href^='#']:after,a[href^='javascript:']:after{color:#000}.logo{padding:3px;// margin-bottom:20px;background:#000;background:-moz-linear-gradient(top,rgba(0,0,0,1) 0,rgba(69,72,77,1) 100%);background:-webkit-gradient(linear,left top,left bottom,color-stop(0,rgba(0,0,0,1)),color-stop(100%,rgba(69,72,77,1)));background:-webkit-linear-gradient(top,rgba(0,0,0,1) 0,rgba(69,72,77,1) 100%);background:-o-linear-gradient(top,rgba(0,0,0,1) 0,rgba(69,72,77,1) 100%);background:-ms-linear-gradient(top,rgba(0,0,0,1) 0,rgba(69,72,77,1) 100%);background:linear-gradient(to bottom,rgba(0,0,0,1) 0,rgba(69,72,77,1) 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#000000', endColorstr='#45484d', GradientType=0 );color:#FFF;box-shadow:0 9px 12px #888}.post{height:125px;background:-moz-linear-gradient(top,rgba(0,0,0,.65) 0,rgba(0,0,0,0) 87%,rgba(0,0,0,0) 91%,rgba(0,0,0,0) 94%,rgba(0,0,0,0) 99%,rgba(0,0,0,0) 100%);background:-webkit-gradient(linear,left top,left bottom,color-stop(0,rgba(0,0,0,.65)),color-stop(87%,rgba(0,0,0,0)),color-stop(91%,rgba(0,0,0,0)),color-stop(94%,rgba(0,0,0,0)),color-stop(99%,rgba(0,0,0,0)),color-stop(100%,rgba(0,0,0,0)));background:-webkit-linear-gradient(top,rgba(0,0,0,.65) 0,rgba(0,0,0,0) 87%,rgba(0,0,0,0) 91%,rgba(0,0,0,0) 94%,rgba(0,0,0,0) 99%,rgba(0,0,0,0) 100%);background:-o-linear-gradient(top,rgba(0,0,0,.65) 0,rgba(0,0,0,0) 87%,rgba(0,0,0,0) 91%,rgba(0,0,0,0) 94%,rgba(0,0,0,0) 99%,rgba(0,0,0,0) 100%);background:-ms-linear-gradient(top,rgba(0,0,0,.65) 0,rgba(0,0,0,0) 87%,rgba(0,0,0,0) 91%,rgba(0,0,0,0) 94%,rgba(0,0,0,0) 99%,rgba(0,0,0,0) 100%);background:linear-gradient(to bottom,rgba(0,0,0,.65) 0,rgba(0,0,0,0) 87%,rgba(0,0,0,0) 91%,rgba(0,0,0,0) 94%,rgba(0,0,0,0) 99%,rgba(0,0,0,0) 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#a6000000', endColorstr='#00000000', GradientType=0 )}.crimson{background:rgba(185,0,0,.96)}.yellow{background:#D28B00}.cobalt{background:#225E82}.olive{background:#003E00}.lavender{background:#79005A}.paper,.paper_table,.piechart_right{background-color:#FFF;border-radius:7px;box-shadow:1px 1px 5px #7D7878}.paper{padding:15px;margin:7px 5px}.logo-main img{height:59px}.logo-main p{padding:4px;margin:6px}.small{height:110px}.timer div{margin:-6px 3px}.footer p{margin:20px 0}.paper_table{padding:15px;margin:5px 3px}.sticky{margin-left:23px}.piechart_right{margin:7px 0 7px 8px;width:73%}.logo-bar p{margin-top:10px}.time{margin-top:41px}"
					+ "	  </style>"
					+ "		<!--<link rel='stylesheet' href='http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css' />-->"
					+ "		<!--<script src='http://code.jquery.com/jquery-1.9.1.min.js'></script>"
					+ "		<script src='http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js'></script>"
					+ "	-->"
					+ "   </head>"
					+ "   <body> "
					+ "   "
					+ "		<div data-role='page'  id='pg2'>"
					+ "         <!--Header Start -->"
					+ "         <div class='logo'>"
					+ "            <div class='container'>"
					+ "               <div class='row'>"
					+ "                  <!-- <div class='col-xs-1 logo-main' >"
					+ "                    <a href='#' data-transition='flow'><img  src= ''/></a>"
					+ "                  </div>-->"
					+ "                  <div class='col-xs-12 logo-main text-center'>"
					+ "                     <p>Cisco Automation Execution Report</p>"
					+ "                  </div>"
					+ "                 <!-- "
					+ "				 <div class='col-xs-4 logo-bar' >"
					+ "                     <p>Target OS - Windows Vista <br/>Executed By - chirag@wipro.com</p>"
					+ "                  </div>-->"
					+ "               </div>"
					+ "            </div>"
					+ "         </div>"
					+ "         <div class='jumbotron_report'>"
					+ "            <div class='container'>"
					+ "               <div class='row'>"
					+ "                  <div class='col-sm-12 '>"
					+ "                     <h3 class='col-sm-2'>Test Name: </h3>"
					+ "                     <h3 class = 'col-sm-10'>"+ Filename//"E2E_Validation of Subscription Project status change to Pre Sales"
					+ "</h3>"
					+ "<div class='col-xs-5 logo-bar'>"
					+ "						<p>Target OS - "+System.getProperty("os.name")
					+ "						<br>Executed By - "+(System.getProperty("user.name"))
					+ "						<br>Execution Data ID - "+dataId
					+ "						</p>"
					+ "						</div>       "
					+ "                  </div>"
					+ "               </div>"
					+ "            </div>"
					+ "         </div>"
					+ "		 "
					+ "		 <div class='container'>"
					+ "            <div class='row'>    "
					+ "          <div class='col-sm-12'>"
					+ "				  <!--<h3 class='col-md-6' id= 'CTO-CostRollup'>Scenario : CTO-CostRollupWithSomeBigBigScena</h3>-->"
					+ "                  <h3 class='col-md-6' align='left' id= 'CTO-CostRollup'><strong>Executed on</strong> : "
					+ currentDateTime//"25th Jul 2015; 10:30am"
					+ "</h3>  "
					+ "           <table class='table table-bordered'>"
					+ "                     <tr class=''>"
					+ "                        <th class='col-md-1 tsindlevel1 '>Step#</th>"
					+ "                        <th class='col-md-4 tsindlevel1 '>Description</th>"
					+ "                        <th class='col-md-3 tsindlevel1 '>Expected Result</th>"
					+ "                        <th class='col-md-3 tsindlevel1 '>Actual Reuslt</th>"
					+ "                        <th class='col-md-1  tsindlevel1 '>Status</th>"
					+ "                     </tr>");
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception :", e);
		}
		imageCounter = 1;
		return writer;
	}

	public void createFolders()
	{
		try
		{
			Date Now = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyyy hh mm ss a zzz");

			String currentDateTime = ft.format(Now);
			if (currentDateTime.contains("-")) {
				currentDateTime = currentDateTime.substring(0, currentDateTime
						.indexOf("-"));
			} else if (currentDateTime.contains("+")) {
				currentDateTime = currentDateTime.substring(0, currentDateTime
						.indexOf("+"));
			}
			System.out.println("Execution Date & Time :" + currentDateTime);
			setDateTime(currentDateTime);

			String path = String.valueOf(System.getProperty("user.dir")) + System.getProperty("file.separator") + "Reports";
			File file = new File(path);
			if (file.exists())
			{
				setReportDirPath(path);
			}
			else
			{
				file.mkdir();
				setReportDirPath(path);
			}
			countExecution(currentDateTime);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception :", e);
		}
	}

	public void countExecution(String currentDateTime)
	{
		try
		{
			count = 1;
			String reportsDirectoryPath = getReportDirPath();
			File dir = new File(String.valueOf(reportsDirectoryPath) + System.getProperty("file.separator") + "Reports(" + currentDateTime + ")");
			if (!dir.exists())
			{
				dir.mkdir();
				String directoryPath = String.valueOf(reportsDirectoryPath) + System.getProperty("file.separator") + "Reports(" + currentDateTime + ")" + System.getProperty("file.separator") + "logs";
				dir = new File(directoryPath);
				dir.mkdir();

				directoryPath = String.valueOf(reportsDirectoryPath) + System.getProperty("file.separator") + "Reports(" + currentDateTime + ")" + System.getProperty("file.separator") + "images";
				dir = new File(directoryPath);
				dir.mkdir();
				createLog4jFile(reportsDirectoryPath + 
						System.getProperty("file.separator") + "Reports(" + currentDateTime + ")" + 

          System.getProperty("file.separator") + "log(" + currentDateTime + ")");
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception :", e);
		}
	}

	public void copyFile(String sourceFile, String targetFile)
	{
		File f = new File(sourceFile);
		try
		{
			if (f.exists())
			{
				FileInputStream in = new FileInputStream(f);
				FileOutputStream out = new FileOutputStream(targetFile);
				int c;
				while ((c = in.read()) != -1) {
					out.write(c);
				}
				in.close();
				out.close();
			}
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception :", e);
		}
	}

	

	

	public void screenShotHyperLink(BufferedWriter writer)
	{
		try
		{
			writer.write("<TD class='tsgen' width=50px align=center>");
			writer.write("<a href='..\\images\\" + imageName + "' ><img class='screen' src = 'http://fdk-stage.cisco.com/content/dam/en/i/test/Screenshot.jpg'></a>");

			writer.write("</td>");
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception :", e);
		}
	}
	
	public void log( BufferedWriter writer, String ScreenshotName, String msg, String status)
	{
		int counter = 1;
		if (writer != null) {
			counter = this.value;
		}
		String[] messages = msg.split(";");
		String stepDesc = ".";
		String expectedMsg = ".";
		String actualMsg = ".";
		stepDesc = messages[0];
		if (messages.length > 1)
		{
			expectedMsg = messages[1];
			actualMsg = messages[2];
		}
		logMsg = actualMsg;
		addLogToFile( writer, ScreenshotName, counter, stepDesc, expectedMsg, actualMsg, status);

		this.value = (counter + 1);
	}
	
	public void addLogToFile( BufferedWriter writer, String ScreenshotName, int stepNum, String stepDesc, String expectedMsg, String actualMsg, String status)
	{
		try
		{

			writer.write("<TR>"
					+ " <TD class='tsindlevel2' width=75px>"+stepNum+"</TD>"
					+ "  <TD class='tsgen' width=155px>"+stepDesc+"</TD>"
					+ " <TD class='tsgen' width=285px>"+expectedMsg+"</TD>"
					+ "<TD class='tsgen' width=285px>"+actualMsg+ "</TD>");

			
			if (status.equalsIgnoreCase("Passed"))
			{
				if (writer != null) {
					passedcount += 1;
				}
				writer.write("<TD class='tsgen success success' width=50px align=center>"
						+ "Passed"
						+ "<img class='screen' src = ''></TD>"+ " </TR>");
			}
					
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception :", e);
		}
	}
	public void logMessage(String stepDesc, String expectedMsg, String actualMsg, String status)
	{
		if (status.equalsIgnoreCase("Passed"))
		{
			log( null, String.valueOf(stepDesc) + ";" + expectedMsg + ";" + actualMsg, "Passed", status);
			infoToLog4j("Status : " + status + " :: " + actualMsg);
		}
		else
		{
			log( null, String.valueOf(stepDesc) + ";" + expectedMsg + ";" + actualMsg, "Failed", status);
			setActuvalResult(actualMsg);
			setExpectedResult(expectedMsg);
			errorToLog4j("Status : " + status + " :: " + actualMsg);
		}
		System.out.println("Status : " + status + " :: " + actualMsg);
	}

	public void screenShotHyperLinkNew(BufferedWriter writer, String imgName)
	{
		try
		{
			writer.write("<TD class='tsgen' width=50px align=center>");
			writer.write("<a href='..\\images\\" + imgName + "' ><img class='screen' src = 'http://fdk-stage.cisco.com/content/dam/en/i/test/Screenshot.jpg'></a>");

			writer.write("</td>");
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception :", e);
		}
	}

	public void after(BufferedWriter writer, long tcStartTime, long tcEndTime, String osType, String browserType, String browserVersion)
	{
		try
		{
			addTestCaseDetailsToLogfile(writer, tcStartTime, tcEndTime, osType, browserType, browserVersion);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception :", e);
		}
	}

	public void closewriter(BufferedWriter writer)
	{
		try
		{
			writer.close();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Exception :", e);
		}
	}

	public void addTestCaseDetailsToLogfile(BufferedWriter writer, long tcStartTime, long tcEndTime, String osType, String browserType, String browserVersion)
	{
		try
		{
			String totalExecutionTime = "";
			try
			{
				//writer.write("</table>");
				Date d1 = new Date(tcStartTime);
				Date d2 = new Date(tcEndTime);
				SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("dd-MM-yyyy");
				String todayDate = DATE_FORMAT1.format(new Date());
				
				long timeMillis = tcEndTime - tcStartTime;

				//Modified - cpandit
				/*long seconds = timeMillis / 1000L;
				long minutes = seconds %= 3600L / 60L;
				long secRemaining = seconds - minutes % (60L * 60L);
				totalExecutionTime = String.valueOf(String.valueOf(minutes) + " mins :" + secRemaining + " secs");
				*/
				
				long millis = timeMillis % 1000;
				timeMillis/=1000;
				long seconds = timeMillis % 60;
				timeMillis/=60;
				long minutes = timeMillis % 60;
				timeMillis/=60;
				long hours = timeMillis;
				
				totalExecutionTime = seconds+"."+ millis +" secs";
				if (minutes > 0){
					totalExecutionTime = minutes + " mins : "+  totalExecutionTime;
				}
				if (hours > 0){
					totalExecutionTime = hours+" hrs : "+ totalExecutionTime;
				}
				System.out.println("totalExecutionTime: "+totalExecutionTime);//udsTimeDiff(timeMillis));
				
				
				
				String status = "Passed";
				if (failedcount > 0) {
					status = "Failed";
				}
				writer.write(""
				+ "  </table>"
				+ "<div class='text-right'>"
				+ "                     <p class=''>Reporting UI crafted by - <a href='' >Chirag Pandit (cpandit)</a></p>"
				+ "                  </div>"
				+ "               </div>"
				+ "            </div>"
				+ "         </div>"
				+ "         <div class='jumbotron_report '>"
				+ "				<div class='container'>"
				+ "               <div class='row'>"
				+ "                  <div class='col-sm-12'>"
				+ "                     <div class='col-sm-12'>"
				+ "                        <div class='footer'>"
				+ "                           <h1 align='center'>Overall Test Status - "+status.toUpperCase()+"</h1>"
				+ "                        </div>"
				+ "                     </div>"
				+ "                     <div class = 'col-sm-6'>"
				+ "                        <table class = 'table table-bordered'>"
				+ "                           <tr>"
				+ "                              <th class='summaryheader' colspan=2>Execution Time Summary</th>"
				+ "                           </tr>"
				+ "                           <tr>"
				+ "                              <td class ='col-md-3' >Test Executed On</td>"
				+ "                              <td class ='col-md-3'>"
				+""+todayDate//+ "4th Sep 2015"
				+ "</td>"
				+ "                           </tr>"
				+ "                           <tr>"
				+ "                              <td class =''>Time Taken</td>"
				+ "                              <td class =''>"
				+totalExecutionTime//2 Hours 26 Seconds"wd
				+ "</td>"
				+ "                           </tr>"
				+ "                           <tr>"
				+ "                              <td class =''>Started On</td>"
				+ "                              <td class =''>"
				+ d1//"01:00:10 PM"
				+ "</td>"
				+ "                           </tr>"
				+ "                           <tr>"
				+ "                              <td class =''>Ended On</td>"
				+ "                              <td class =''>"
				+ d2//"03:57:01 PM"
				+ "</td>"
				+ "                           </tr>"
				+ "                        </table>"
				+ "                     </div>"
				+ "                     <div class = 'col-sm-6'>"
				+ "                        <table class = 'table table-bordered'>"
				+ "                           <tr>"
				+ "                              <th class='summaryheader' colspan=2>Execution Steps Summary</th>"
				+ "                           </tr>"
				+ "                           <tr>"
				+ "                              <td class ='col-md-5' >Operating System</td>"
				+ "                              <td class ='col-md-1'>"+System.getProperty("os.name")+"</td>"
				+ "                           </tr>"
				+ "                           <tr>"
				+ "                              <td class =''>Browser Type</td>"
				+ "                              <td class =''>"+browserType+"</td>"
				+ "                           </tr>"
				+ "                           <tr>"
				+ "                              <td class =''>Browser Version</td>"
				+ "                              <td class =''>"+browserVersion+"</td>"
				+ "                           </tr>"
				+ "                           <tr>"
				+ "                              <td class =''>System User</td>"
				+ "                              <td class =''>"+System.getProperty("user.name")+"</td>"
				+ "                           </tr>"
				+ "                        </table>"
				+ "                     </div>"
				+ "                  </div>"
				+ "                  <div class='footer container'>"
				+ "                     <p class=''>Cisco Systems, Inc. Cisco Confidential.</p>"
				+ "                  </div>"
				+ "               </div>"
				+ "            </div>"
				+ "         </div>"
				+ "      </div>"
				+ "   </body>"
				+ "</html>" );
				
				/*writer.write("<hr class='divline'><br><table class='subheader' width=100%><tr><tr><td width=100% class='subheader'>
				 * Test Execution Summary</td></tr><tr><td width=100% class='subcontents'></td></tr></tr></table><hr class='divline'><br><table><tr><td><table class = 'releasesummary'><tr><td class='summaryheader' colspan=2>
				 * Execution Time Summary</td><
				 * /tr><tr><td class ='summaryelement' width=100>Test Executed On</td>
				 * <td class ='summarybody' width=450>" + todayDate + "</td></tr><tr><td class ='summaryelement' width=100>
				 * Test Execution Start Time</td><td class ='summarybody' width=450>" + d1 + "</td></tr><tr><td class ='summaryelement'>
				 * Test Execution End Time</td><td class ='summarybody'>" + d2 + "</td></tr><tr><td class ='summaryelement'>
				 * Total Execution Time</td><td class ='summarybody'>" + totalExecutionTime + "</td></tr></table></td><td></td>" + "<td><table class = 'releasesummary'><tr><td class='summaryheader' colspan=2>
				 * Execution Details Summary</td></tr><tr><td class ='summaryelement' width=100>Operating System</td><td class ='summarybody'width=450>" + osType + "</td></tr><tr><td class ='summaryelement'>Browser Type</td><td class ='summarybody'>" + browserType + "</td></tr><tr><td class ='summaryelement'>Browser Version</td><td class ='summarybody'>" + browserVersion + "</td></tr><tr><td class ='summaryelement'>System User</td><td class ='summarybody'>" + 
          		System.getProperty("user.name") + "</td></tr></table></td></tr></table>" + "<hr class='divline'><hr class='divline'><br><table class='subheader' width=100%><tr><tr><td width=100% class='subheader' align='center'>Overall Test Status : ");
				if ("Passed".equalsIgnoreCase(status)) {
					writer.write("<span class = 'testpassed'>PASSED</span>");
				} else {
					writer.write("<span class = 'testfailed'>FAILED</span>");
				}
				writer.write("</td></tr></tr></table><hr class='divline'><br><center><span>CISCO CONFIDENTIAL - Copyright &copy; www.cisco.com </span></center></body></html>");
				*/
			
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Exception :", e);
			}
		}
		finally
		{
			this.value = 1;
			failedcount = 0;
		}
	}

	public static String getCurrentTime(long tcStartTime)
	{
		String todayTime = "";
		SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("dd-MM-yyyy");
		String todayDate = DATE_FORMAT1.format(new Date());
		Date d1 = new Date(tcStartTime);
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("hh-mm-ss");
		String time = DATE_FORMAT.format(d1);
		todayTime = String.valueOf(todayDate) + "-" + time;
		return todayTime;
	}

	public static void infoToLog4j(String msg)
	{
		log4j.info(msg);
	}

	public static void errorToLog4j(String msg)
	{
		log4j.error(msg);
	}

	public static void debugToLog4j(String msg)
	{
		log4j.debug(msg);
	}

	public static void fatalToLog4j(String msg)
	{
		log4j.fatal(msg);
	}

	public static void warnToLog4j(String msg)
	{
		log4j.warn(msg);
	}

	public void createLog4jFile(String path)
			throws IOException
			{
	//	PropertyConfigurator.configure(log4jProperties);
		MyHTMLLayout layout = new MyHTMLLayout();
		FileAppender appender = new FileAppender(layout, path + ".html", false);
		log4jPath = path + ".html";
		log4j.addAppender(appender);

		infoToLog4j("Logging Initialized");
			}
}
