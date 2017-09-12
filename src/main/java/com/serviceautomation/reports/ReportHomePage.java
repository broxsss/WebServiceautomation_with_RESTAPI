package com.serviceautomation.reports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportHomePage
{
	private static String mainReportLoaction = "";
	private static String reportLogLoaction = "";
	private static final Logger LOGGER = Logger.getLogger(ReportHomePage.class.getName());
	//static Properties commonProperties = PropertiesFileReader.getInstance().readProperties("common.properties");
	public static String sonarUrl = null;
	public static int totalTcs = 0;
	public static int noOfPassed = 0;
	public static int noOfFailed = 0;
	public static String mailReport = "";
	private static String log4jName = "";
	static HashMap<String, String> verifyMap = new HashMap<String, String>();

	static
	{
		pbuildNo = null;
		pbuildURL = null;
		pbuildId = null;
		ReportHeader = "<html><head><link href='http://fdk-stage.cisco.com/content/dam/en/i/test/style.css' rel='stylesheet' type='text/css' /></head><hr class='divline'><table class='reportheader' width=100%><TR><td height=50px align=left><img src = 'http://fdk-stage.cisco.com/content/dam/en/i/test/cisco_logo.jpg'></TD><BR><td height=50px align=right><Table class='developer'><TR><td class='desc1'><Center><table><TR><TD class='desccpy'>Automation Test Execution Report</TD></TR><TR><TD class='dev'>Tool Used : Selenium WebDriver </TD></TR></Table></TD></TR></Table></td><BR></tr></table><hr class='divline'><BR><table class='subheader' width=100%><tr><td width=100% class='subheader'>Build Summary</td></tr><tr><td width=100% class='subcontents'></td></tr></table><hr class='divline'><BR><br><table><tr><td></td><td></td><td><table class = 'releasesummary'><tr><td class='summaryheader'colspan=2>Parent Job Build Details</td></tr><tr><td class ='summaryelement' width=100>Build ID</td><td class ='summarybody'width=450>$$ParentBuildID$$</td></tr><tr><td class ='summaryelement' width=100>Build Number</td><td class ='summarybody'width=450>$$ParentBuildNumber$$</td></tr><tr><td class ='summaryelement' width=100>Build URL</td><td class ='summarybody' width=450><a href='$$ParentBuildURL$$'>$$ParentBuildURL$$</a></td></tr></table></td><td></td><td></td><td></td><td><table class = 'releasesummary'><tr><td class='summaryheader'colspan=2>Current Job Build Details</td></tr><tr><td class ='summaryelement' width=100>Build ID</td><td class ='summarybody'width=450>$$BuildID$$</td></tr><tr><td class ='summaryelement' width=100>Build Number</td><td class ='summarybody'width=450>$$BuildNumber$$</td></tr><tr><td class ='summaryelement' width=100>Build URL</td><td class ='summarybody' width=450><a href='$$BuildURL$$'>$$BuildURL$$</a></td></tr></table></td><td></td><td></td></tr></table> <br/><hr class='divline'><BR><table class='subheader' width=100%><tr><td width=100% class='subheader'>Test Execution Summary</td></tr><tr><td width=100% class='subcontents'></td></tr></tr></table><hr class='divline'><br><table><tr><td><table class = 'releasesummary'><tr><td class='summaryheader'colspan=2>Execution Time Summary</td></tr><tr><td class ='summaryelement' width=100>Test Execution Start Time</td><td class ='summarybody'width=450>$$TestExecutionStartTime$$</td></tr><tr><td class ='summaryelement' width=100>Test Execution End Time</td><td class ='summarybody'width=450>$$TestExecutionEndTime$$</td></tr><tr><td class ='summaryelement'>Total Execution Time</td><td class ='summarybody'>$$TotalExecutionTime$$</td></tr></table></td><td></td><td><table class = 'releasesummary'><tr><td class='summaryheader'colspan=2>Execution Details Summary</td></tr><tr><td class ='summaryelement' width=100> # Total Test Scripts</td><td class ='summarybody'width=450>$$NumberOfTestScript$$</td></tr><tr><td class ='summaryelement'>TCs PASSED#(%)</td><td class ='summarybody'>$$NumberOfPASSED$$</td></tr><tr><td class ='summaryelement'>TCs Failed#(%)</td><td class ='summarybody'>$$NumberOfFAILED$$</td></tr></table></td></tr></table><br/><br/><hr class='divline'><BR><table class='subheader'><tr><td class='subheader' align=center >Total Scripts Execution</td></td><tr><td class='subcontents'></td></tr></table><table class='teststeps'><tr><td class='tsheader' width=75px>S.No</td><td class='tsheader' width=100px>Test Case</td><td class='tsheader' width=100px>Executed_ON</td><td class='tsheader' width=100px>Execution Time</td><td class='tsheader' width=100px>Status</td></tr>";
		ReportBody = "<tr><td class='summarybody' >$$SNo$$</td><td class='summarybody' ><a href='logs\\$$DeatiledExecutionReportFileLink$$'>$$FileName$$</a></td><td class='summarybody' >$$BrowserType$$</td><td class='summarybody' >$$ExecutionTime$$</td><td class='summarybody' ><p style=font-family:verdana;type:bold;color:$$StatusColor$$;><b>$$Status$$</b></p></td></tr>";
		ReportFooter = "</table></br></br><hr class='divline'><br><center><span>CISCO CONFIDENTIAL - Copyright &copy; www.cisco.com </span></center></body></html>";
	}

	static String ReportHeader = "<!DOCTYPE html><html lang='en'>   <head>      <title>         Automation Batch Execution Summary Report - Cisco      </title>      <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>      <meta charset='utf-8'>      <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css'>      <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>      <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>      <script type='text/javascript' src='https://www.google.com/jsapi'></script>      <script type='text/javascript'>         google.load('visualization', '1', {packages:['corechart']});         google.setOnLoadCallback(drawChart);         function drawChart() {                    var data = google.visualization.arrayToDataTable("
			+ "[ ['Test Scenario', 'Number of testcases'], ['Passed',     5],['Failed',      3]           ]);                   "
			+ " var options = {             title: 'Tests Automated'  , slices: [{color: '#5cb85c'}, {color: '#dc3912'}]         };                    "
			+ "var chart = new google.visualization.PieChart(document.getElementById('totalTests'));                    chart.draw(data, options);         }      </script>      <meta name='viewport' content='width=device-width, initial-scale=1'>      <style type='text/css'>	  .jumbotron_report,.jumbotron_report_fail{color:#FFF;-webkit-box-shadow: inset 0 -49px 111px rgba(0,0,0,.2), inset 0 -46px 111px rgba(0,0,0,.2);;-moz-box-shadow: inset 0 -49px 111px rgba(0,0,0,.2), inset 0 -46px 111px rgba(0,0,0,.2);;padding-top:27px;padding-bottom:27px;filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#00aaaaaa', endColorstr='#a6aaaaaa', GradientType=0 )}.subtle,.tsheader,.tsindlevel1{font-weight:700;text-shadow:none}.jumbotron,.jumbotron_report,.jumbotron_report_fail{filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#00aaaaaa', endColorstr='#a6aaaaaa', GradientType=0 )}.logo-main,.timer strong{font-size:27px}.footer p,.sticky{text-align:center}@font-face{font-family:GraublauWeb;src:url(fonts/trenchLogo.otf) format('opentype')}@font-face{font-family:GraublauWeb;font-weight:700;src:url(fonts/trenchLogo.otf) format('opentype')}.jumbotron_report{background:-moz-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-webkit-gradient(linear,left top,left bottom,color-stop(0,rgba(170,170,170,0)),color-stop(100%,rgba(170,170,170,.65)));background:-webkit-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-o-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-ms-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:linear-gradient(to bottom,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-webkit-linear-gradient(45deg,#270C3C 0,#104e6b 100%);background:-o-linear-gradient(45deg,#270C3C 0,#104e6b 100%);background:-ms-linear-gradient(45deg,#270C3C 0,#104e6b 100%);background:linear-gradient(45deg,#270C3C 0,#104e6b 100%);box-shadow: inset 0 -49px 111px rgba(0,0,0,.2), inset 0 -46px 111px rgba(0,0,0,.2);}.jumbotron_report_fail{background:-moz-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-webkit-gradient(linear,left top,left bottom,color-stop(0,rgba(170,170,170,0)),color-stop(100%,rgba(170,170,170,.65)));background:-webkit-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-o-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-ms-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:linear-gradient(to bottom,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-webkit-linear-gradient(45deg,#230C3C 0,#6B1010 100%);background:-o-linear-gradient(45deg,#230C3C 0,#6B1010 100%);background:-ms-linear-gradient(45deg,#230C3C 0,#6B1010 100%);background:linear-gradient(45deg,#230C3C 0,#6B1010 100%);box-shadow: inset 0 -49px 111px rgba(0,0,0,.2), inset 0 -46px 111px rgba(0,0,0,.2);}.fail-link,.fail-link : hover,.fail-link :active,.fail-link:visited{color:#000}.tsheader{background:#424242;color:#FFF}.subtle{background:#CCC;color:#000}.tsindlevel1{background:#424242;color:#FFF}.table-bordered>tbody>tr>td,.table-bordered>tbody>tr>th,.table-bordered>tfoot>tr>td,.table-bordered>tfoot>tr>th,.table-bordered>thead>tr>td,.table-bordered>thead>tr>th{border:1px solid #ABABAB}.jumbotron{background:-moz-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-webkit-gradient(linear,left top,left bottom,color-stop(0,rgba(170,170,170,0)),color-stop(100%,rgba(170,170,170,.65)));background:-webkit-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-o-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:-ms-linear-gradient(top,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);background:linear-gradient(to bottom,rgba(170,170,170,0) 0,rgba(170,170,170,.65) 100%);-webkit-box-shadow: inset 0 -49px 111px rgba(0,0,0,.2), inset 0 -46px 111px rgba(0,0,0,.2);;-moz-box-shadow: inset 0 -49px 111px rgba(0,0,0,.2), inset 0 -46px 111px rgba(0,0,0,.2);;box-shadow: inset 0 -49px 111px rgba(0,0,0,.2), inset 0 -46px 111px rgba(0,0,0,.2);}body{//color:#CCC;background:#FFF;/*text-shadow:1px 1px 2px rgba(150,150,150,1)*/}a,a:active,a:focus,a:hover,a:visited,a[href]:after,a[href^='#']:after,a[href^='javascript:']:after{color:#000}.logo{padding:3px;// margin-bottom:20px;background:#000;background:-moz-linear-gradient(top,rgba(0,0,0,1) 0,rgba(69,72,77,1) 100%);background:-webkit-gradient(linear,left top,left bottom,color-stop(0,rgba(0,0,0,1)),color-stop(100%,rgba(69,72,77,1)));background:-webkit-linear-gradient(top,rgba(0,0,0,1) 0,rgba(69,72,77,1) 100%);background:-o-linear-gradient(top,rgba(0,0,0,1) 0,rgba(69,72,77,1) 100%);background:-ms-linear-gradient(top,rgba(0,0,0,1) 0,rgba(69,72,77,1) 100%);background:linear-gradient(to bottom,rgba(0,0,0,1) 0,rgba(69,72,77,1) 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#000000', endColorstr='#45484d', GradientType=0 );color:#FFF;box-shadow:0 9px 12px #888}.post{height:125px;background:-moz-linear-gradient(top,rgba(0,0,0,.65) 0,rgba(0,0,0,0) 87%,rgba(0,0,0,0) 91%,rgba(0,0,0,0) 94%,rgba(0,0,0,0) 99%,rgba(0,0,0,0) 100%);background:-webkit-gradient(linear,left top,left bottom,color-stop(0,rgba(0,0,0,.65)),color-stop(87%,rgba(0,0,0,0)),color-stop(91%,rgba(0,0,0,0)),color-stop(94%,rgba(0,0,0,0)),color-stop(99%,rgba(0,0,0,0)),color-stop(100%,rgba(0,0,0,0)));background:-webkit-linear-gradient(top,rgba(0,0,0,.65) 0,rgba(0,0,0,0) 87%,rgba(0,0,0,0) 91%,rgba(0,0,0,0) 94%,rgba(0,0,0,0) 99%,rgba(0,0,0,0) 100%);background:-o-linear-gradient(top,rgba(0,0,0,.65) 0,rgba(0,0,0,0) 87%,rgba(0,0,0,0) 91%,rgba(0,0,0,0) 94%,rgba(0,0,0,0) 99%,rgba(0,0,0,0) 100%);background:-ms-linear-gradient(top,rgba(0,0,0,.65) 0,rgba(0,0,0,0) 87%,rgba(0,0,0,0) 91%,rgba(0,0,0,0) 94%,rgba(0,0,0,0) 99%,rgba(0,0,0,0) 100%);background:linear-gradient(to bottom,rgba(0,0,0,.65) 0,rgba(0,0,0,0) 87%,rgba(0,0,0,0) 91%,rgba(0,0,0,0) 94%,rgba(0,0,0,0) 99%,rgba(0,0,0,0) 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#a6000000', endColorstr='#00000000', GradientType=0 )}.crimson{background:rgba(185,0,0,.96)}.yellow{background:#D28B00}.cobalt{background:#225E82}.olive{background:#003E00}.lavender{background:#79005A}.paper,.paper_table,.piechart_right{background-color:#FFF;border-radius:7px;box-shadow:1px 1px 5px #7D7878}.paper{padding:15px;margin:7px 5px}.logo-main img{height:59px}.logo-main p{padding:4px;margin:6px}.small{height:110px}.timer div{margin:-6px 3px}.footer p{margin:20px 0}.paper_table{padding:15px;margin:5px 3px}.sticky{margin-left:23px}.piechart_right{margin:7px 0 7px 8px;width:73%}.logo-bar p{margin-top:10px}.time{margin-top:41px} #totalTests {	background: #FFF;height:250px;}	  </style>		<!--<link rel='stylesheet' href='http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css' />-->		<!--<script src='http://code.jquery.com/jquery-1.9.1.min.js'></script>		<script src='http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js'></script>	-->   </head>   <body>   		<div data-role='page'  id='pg2'>         <!--Header Start --><!--         <div class='logo'>            <div class='container'>               <div class='row'>                  <div class='col-xs-12 logo-main text-center'>                     <p>Cisco Automation Batch Execution Report</p>                  </div>               </div>            </div>         </div>							<!-- $$ParentBuildID$$ $$ParentBuildNumber$$ $$ParentBuildURL$$ $$BuildID$$  $$BuildNumber$$ $$BuildURL$$-->         <div class='jumbotron_report'>            <div class='container'>               <div class='row'>                  <div class='col-sm-12'>                     <div class='col-sm-12'>                        <div class='footer'>                           <h1 align='center'> Cisco Automation Batch Execution Report </h1>                        </div>                     </div>                     <div class = 'col-sm-6'>                        <table class = 'table table-bordered'>                           <tr>                              <th class='summaryheader' colspan=2>Execution Time Summary</th>                           </tr><!--                           <tr>                              <td class ='col-md-3' >Test Executed On</td>                              <td class ='col-md-3'>4th Sep 2015</td>                           </tr>-->                           <tr>                              <td class =''>Time Taken</td>                              <td class =''>$$TotalExecutionTime$$</td>                           </tr>                           <tr>                              <td class =''>Started On</td>                              <td class =''>$$TestExecutionStartTime$$</td>                           </tr>                           <tr>                              <td class =''>Ended On</td>                              <td class =''>$$TestExecutionEndTime$$</td>                           </tr>                        </table>                     </div>                     <div class = 'col-sm-6'>                        <table   class = 'table table-bordered'>                           <tr>                              <th class='summaryheader' colspan=2>Execution Details Summary</th>                           </tr>                           <tr>                              <td class ='col-md-5' >Total Tests Scripts</td>                              <td class ='col-md-1'>$$NumberOfTestScript$$</td>                           </tr>                           <tr>                              "
			+ "<td class =''>Number of SubSteps Passed</td>                              <td class =''>$$NumberOfPASSED$$</td>                           </tr>                           <tr>                              "
			+ "<td class =''>Number of SubSteps Failed</td>                              <td class =''>$$NumberOfFAILED$$</td>                           </tr>                        </table>                     </div>                  </div>               </div>            </div>         </div>		 		 <div class='container'>            <div class='row'>               <div class='col-sm-12'>                  				  <!--<h3 class='col-md-6' id= 'CTO-CostRollup'>Scenario : CTO-CostRollupWithSomeBigBigScena</h3>-->				  <br/>                  <h3 class='col-md-6' align='left' id= 'CTO-CostRollup'> </h3>                  <table class='table table-bordered'>                     <tr class=''>                        <th class='col-md-1 tsindlevel1 '>Step#</th>                        <th class='col-md-6 tsindlevel1 '>Test Case</th>                        <th class='col-md-2 tsindlevel1 text-center'>Browser Type</th>                        <th class='col-md-2 tsindlevel1 text-center'>Execution Time</th>                        <th class='col-md-1  tsindlevel1 text-center '>Status</th>                     </tr>";
	//"<html><head><link href='http://fdk-stage.cisco.com/content/dam/en/i/test/style.css' rel='stylesheet' type='text/css' /></head><body><hr class='divline'><table class='reportheader' width=100%><TR><td height=50px align=left><img src = 'http://fdk-stage.cisco.com/content/dam/en/i/test/cisco_logo.jpg'></TD><BR><td height=50px align=right><Table class='developer'><TR><td class='desc1'><Center><table><TR><TD class='desccpy'>Automation Test Execution Report</TD></TR><TR><TD class='dev'>Tool Used : Selenium WebDriver </TD></TR></Table></TD></TR></Table></td><BR></tr></table><hr class='divline'><BR><br><table><tr><td></td><td></td><td><table class = 'releasesummary'><tr><td class='summaryheader'colspan=2>Sonar Qube</td></tr><tr><td class ='summaryelement' width=100>Sonar URL</td><td class ='summarybody'width=450><a href='$$SonarURL$$'>Link to Sonar Results</a></td></tr></table></td><td><table class = 'releasesummary'><tr><td class='summaryheader' colspan=2>Log4j</td></tr><tr><td class ='summaryelement' width=100>Log4j Location</td><td class ='summarybody'width=450><a href='$$log4j$$'>Click here to view Log4j file</a></td></tr><tr></tr></table></td><td></td><td></td></tr></table> <br/><hr class='divline'><BR><table class='subheader' width=100%><tr><td width=100% class='subheader'>Build Summary</td></tr><tr><td width=100% class='subcontents'></td></tr></tr></table><hr class='divline'><BR><br><table><tr><td></td><td></td><td><table class = 'releasesummary'><tr><td class='summaryheader'colspan=2>Parent Job Build Details</td></tr><tr><td class ='summaryelement' width=100>Build ID</td><td class ='summarybody'width=450>$$ParentBuildID$$</td></tr><tr><td class ='summaryelement' width=100>Build Number</td><td class ='summarybody'width=450>$$ParentBuildNumber$$</td></tr><tr><td class ='summaryelement' width=100>Build URL</td><td class ='summarybody' width=450><a href='$$ParentBuildURL$$'>$$ParentBuildURL$$</a></td></tr></table></td><td></td><td></td><td></td><td><table class = 'releasesummary'><tr><td class='summaryheader'colspan=2>Current Job Build Details</td></tr><tr><td class ='summaryelement' width=100>Build ID</td><td class ='summarybody'width=450>$$BuildID$$</td></tr><tr><td class ='summaryelement' width=100>Build Number</td><td class ='summarybody'width=450>$$BuildNumber$$</td></tr><tr><td class ='summaryelement' width=100>Build URL</td><td class ='summarybody' width=450><a href='$$BuildURL$$'>$$BuildURL$$</a></td></tr></table></td><td></td><td></td></tr></table> <br/><hr class='divline'><BR><table class='subheader' width=100%><tr><td width=100% class='subheader'>Test Execution Summary</td></tr><tr><td width=100% class='subcontents'></td></tr></tr></table><hr class='divline'><br><table><tr><td><table class = 'releasesummary'><tr><td class='summaryheader'colspan=2>Execution Time Summary</td></tr><tr><td class ='summaryelement' width=100>Test Execution Start Time</td><td class ='summarybody'width=450>$$TestExecutionStartTime$$</td></tr><tr><td class ='summaryelement' width=100>Test Execution End Time</td><td class ='summarybody'width=450>$$TestExecutionEndTime$$</td></tr><tr><td class ='summaryelement'>Total Execution Time</td><td class ='summarybody'>$$TotalExecutionTime$$</td></tr></table></td><td></td><td><table class = 'releasesummary'><tr><td class='summaryheader'colspan=2>Execution Details Summary</td></tr><tr><td class ='summaryelement' width=100> # Total Test Scripts</td><td class ='summarybody'width=450>$$NumberOfTestScript$$</td></tr><tr><td class ='summaryelement'>TCs PASSED#(%)</td><td class ='summarybody'>$$NumberOfPASSED$$</td></tr><tr><td class ='summaryelement'>TCs Failed#(%)</td><td class ='summarybody'>$$NumberOfFAILED$$</td></tr></table></td></tr></table><br/><br/><hr class='divline'><BR><table class='subheader'><tr><td class='subheader' align=center >Total Scripts Execution</td></td><tr><td class='subcontents'></td></tr></table><table class='teststeps'><tr><td class='tsheader' width=75px>S.No</td><td class='tsheader' width=100px>Test Case</td><td class='tsheader' width=100px>Browser Type</td><td class='tsheader' width=100px>Execution Time</td><td class='tsheader' width=100px>Status</td></tr>";
	
	static String ReportBody = " <TR><TD class='tsindlevel2' width=75px>$$SNo$$</TD>"
			+ "<TD class='tsgen' width=155px><a href='logs\\$$DeatiledExecutionReportFileLink$$'>$$FileName$$</a></TD>"
			+ "<TD class='tsgen text-center' width=285px>$$BrowserType$$</TD>"
			+ "<TD class='tsgen text-center' width=285px>$$ExecutionTime$$</TD>"
			+ "<TD class='tsgen "
			//success success 
			+" text-center' width=50px align=center><a href='logs\\$$DeatiledExecutionReportFileLink$$'>$$Status$$</a><img class='screen' src = ''></TD></TR>";
			//"<tr><td class='summarybody' >$$SNo$$</td><td class='summarybody' ><a href='logs\\$$DeatiledExecutionReportFileLink$$'>$$FileName$$</a></td><td class='summarybody' >$$BrowserType$$</td><td class='summarybody' >$$ExecutionTime$$</td><td class='summarybody' ><p style=font-family:verdana;type:bold;color:$$StatusColor$$;><b>$$Status$$</b></p></td></tr>";
	
	static String ReportFooter = "</table>"
			+ "<!--<div id='totalTests' class='container'><p >Total Tests.</p></div>-->"
			+ "<div class='footer container'>"
			+ "<p class=''>Cisco Systems, Inc. Cisco Confidential.</p></div></div></div></div></body></html>";
			//"</table></br></br><hr class='divline'><br><center><span>CISCO CONFIDENTIAL - Copyright &copy; www.cisco.com </span></center></body></html>";
	
	
	private static String MailReportHeader = "<html><head></head><body style='font-size: 70%;color: #000;margin: 10px;background-color: #FFFFFF;font-family: verdana, helvetica, arial, sans-serif'><p style='font-family:Arial; font-weight:bold;font-size:14px; color:#06C;'>Hi Team,</p><p style='font-family:Arial; font-weight:bold;font-size:14px; color:#06C;'>Please Find the Attached Automation Execution Report.</p><br><table style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;width:100%;border:0'><tr><td align=center style='font-family: verdana, helvetica, arial, sans-serif;color:#C71585;background-color: transparent;font-size: 90%;font-weight: bold;text-align: center;padding: 0 0 0 0;border:0;'><u style='border-bottom:1px solid #3333FF;'>Execution Summary By ATOM</td><td align=right style='font-family: verdana, helvetica, arial, sans-serif;background-color: transparent;font-size: 87%;font-weight: bold;text-align: right;padding: 0 0 0 0;border: 0;'>Tool Used:Selenium Webdriver</td></tr></table><br><table ><tr><td></td><td></td><td><table class = 'releasesummary' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;background-color: transparent;color: #000;border: 1px solid #000;border-collapse: collapse;padding-top: 5px;padding-bottom: 5px;left: 200px;width:100%'><tr><td class='summaryheader' colspan=2 style='font-family: verdana, helvetica, arial, sans-serif;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 1px 0 0 0;border: 1px solid #000'>Sonar Qube </td></tr><tr><td class ='summaryelement'  style='font-family: verdana, helvetica, arial, sans-serif;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'>Sonar URL</td><td class ='summarybody'  style='font-family: verdana, helvetica, arial, sans-serif;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 1500'><a href='$$SonarURL$$'>Link to Sonar Results</a></td></tr></table></td><td><table class = 'releasesummary' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;background-color: transparent;color: #000;border: 1px solid #000;border-collapse: collapse;padding-top: 5px;padding-bottom: 5px;left: 200px;width:100%'><tr><td class='summaryheader' colspan=2 style='font-family: verdana, helvetica, arial, sans-serif;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 1px 0 0 0;border: 1px solid #000'>Log4j</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'>Log4j Location</td><td class ='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 1500'>$$log4jPath$$</td></tr></table></td></table><br/><table class='reportheader' width='100%' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 200%;width: 100%;font-weight: bold;color: #923;padding: 3px;border: 0 none;border-collapse: collapse'><TR><table class='subheader' width=100% style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;width: 100%;border: 0;background-color: transparent;border-collapse: collapse'><tr><td width=100% class='subheader' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 85%;font-weight: bold;padding-top: 5px;padding-bottom: 5px;background-color: transparent'><u style='border-bottom:1px solid #3333FF;'>Build Summary</td></tr><tr></tr></tr></table><table style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;width:100%'><tr><td style='font-family: verdana, helvetica, arial, sans-serif'><table class = 'releasesummary'  style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;background-color: transparent;color: #000;border: 1px solid #000;border-collapse: collapse;padding-top: 5px;padding-bottom: 5px;left: 200px;width:100%'><tr><td class='summaryheader'colspan=2 style='font-family: verdana, helvetica, arial, sans-serif;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 1px 0 0 0;border: 1px solid #000'>Parent Job Build Details</td></tr><tr><td class ='summaryelement'  style='font-family: verdana, helvetica, arial, sans-serif;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'>Build ID</td><td class ='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1500'>$$ParentBuildID$$</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'>Build Number</td><td class ='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1500'>$$ParentBuildNumber$$</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'>Build URL</td><td class ='summarybody'  style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1500'><a href='$$ParentBuildURL$$' style='font: 16px Arial, Helvetica, sans-serif'/>$$ParentBuildURL$$</a></td></tr></table></td><td style='font-family: verdana, helvetica, arial, sans-serif'><table class = 'releasesummary' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;background-color: transparent;color: #000;border: 1px solid #000;border-collapse: collapse;padding-top: 5px;padding-bottom: 5px;left: 200px;width: 100%'><tr><td class='summaryheader'colspan=2 style='font-family: verdana, helvetica, arial, sans-serif;width: 100%;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 1px 0 0 0;border: 1px solid #000'>Current Job Build Details</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;width: 30%;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'>Build ID</td><td class ='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1500'>$$BuildID$$</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;width: 30%;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'>Build Number</td><td class ='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1500'>$$BuildNumber$$</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;width: 30%;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'>Build URL</td><td class ='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;wwidth: 1500'><a href='$$BuildURL$$' style='font: 16px Arial, Helvetica, sans-serif'/ >$$BuildURL$$</a></td></tr></table></td></tr></table> <table class='subheader' width=100% style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;width: 100%;border: 0;background-color: transparent;border-collapse: collapse'><tr><td width=100% class='subheader' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 85%;font-weight: bold;padding-top: 5px;padding-bottom: 5px;background-color: transparent'><u style='border-bottom:1px solid #3333FF;'>Test Execution Summary</td></tr><tr></tr></tr></table><table style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;width:100%'><tr><td style='font-family: verdana, helvetica, arial, sans-serif'><table class = 'releasesummary' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;background-color: transparent;color: #000;border: 1px solid #000;border-collapse: collapse;padding-top: 5px;padding-bottom: 5px;left: 200px;width:100%'><tr><td class='summaryheader'colspan=2 style='font-family: verdana, helvetica, arial, sans-serif;width: 30%;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 1px 0 0 0;border: 1px solid #000'>Execution Time Summary</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;width: 50%;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 800'>Test Execution Start Time</td><td class ='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1200'>$$TestExecutionStartTime$$</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;width: 50%;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 800'>Test Execution End Time</td><td class ='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1200'>$$TestExecutionEndTime$$</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;width: 50%;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 800'>Total Execution Time</td><td class ='summarybody'  style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1200'>$$TotalExecutionTime$$</td></tr></table></td><td style='font-family: verdana, helvetica, arial, sans-serif'></td><td style='font-family: verdana, helvetica, arial, sans-serif'><table class = 'releasesummary' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;background-color: transparent;color: #000;border: 1px solid #000;border-collapse: collapse;padding-top: 5px;padding-bottom: 5px;left: 200px;width:100%'><tr><td class='summaryheader'colspan=2 style='font-family: verdana, helvetica, arial, sans-serif;width: 30%;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 1px 0 0 0;border: 1px solid #000'>Execution Details Summary</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;width: 30%;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'> # Total Test Scripts</td><td class ='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1500'>$$NumberOfTestScript$$</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;width: 30%;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'>TCs PASSED#(%)</td><td class ='summarybody'  style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1500'>$$NumberOfPASSED$$</td></tr><tr><td class ='summaryelement' style='font-family: verdana, helvetica, arial, sans-serif;width: 30%;background-color: #B8D4FF;font-size: 85%;text-align: center;padding: 1px 3px 3px 0;border: 1px solid #000;width: 500'>TCs Failed#(%)</td><td class ='summarybody'  style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000;width: 1500'>$$NumberOfFAILED$$</td></tr></table></td></tr></table><table class='subheader' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;width: 100%;border: 0;background-color: transparent;border-collapse: collapse'><tr><td class='subheader' align=left style='font-family: verdana, helvetica, arial, sans-serif;font-size: 85%;font-weight: bold;padding-top: 5px;padding-bottom: 5px;background-color: transparent'><u style='border-bottom:1px solid #3333FF;'>Total Scripts Execution</td></td><tr><td class='subcontents' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 85%;padding-top: 1px;padding-bottom: 1px;background-color: transparent'/></td></tr></table><table class='teststeps' style='font-family: verdana, helvetica, arial, sans-serif;font-size: 100%;width: 100%;background-color: #FFC;color: #000;border: 1px solid #000;border-collapse: collapse'><tr><td class='tsheader' width=75px style='font-family: verdana, helvetica, arial, sans-serif;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 0 0 0 0;border: 1px solid #000'>S.No</td><td class='tsheader' width=100px style='font-family: verdana, helvetica, arial, sans-serif;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 0 0 0 0;border: 1px solid #000'>Test Case</td><td class='tsheader' width=100px style='font-family: verdana, helvetica, arial, sans-serif;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 0 0 0 0;border: 1px solid #000'>Browser Type</td><td class='tsheader' width=100px style='font-family: verdana, helvetica, arial, sans-serif;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 0 0 0 0;border: 1px solid #000'>Execution Time</td><td class='tsheader' width=100px style='font-family: verdana, helvetica, arial, sans-serif;background-color: #09F;font-size: 85%;font-weight: bold;text-align: center;padding: 0 0 0 0;border: 1px solid #000'>Status</td></tr>";
	private static String MailReportBody = "<tr style='border:1px solid black;'><td class='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000' >$$SNo$$</td><td class='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000'>$$FileName$$</td><td class='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000'>$$BrowserType$$</td><td class='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000'>$$ExecutionTime$$</td><td class='summarybody' style='font-family: verdana, helvetica, arial, sans-serif;background-color: #DAEDFF;font-size: 85%;text-align: center;padding: 2px 2px 2px 10px;border: 1px solid #000'><p style=font-family:verdana;type:bold;color:$$StatusColor$$;><b>$$Status$$</b></p></td></tr>";
	//private static String MailReportFooter = "</table><p style='font-family:Arial; font-weight:bold;font-size:14px; color:#06C;'> \n\nThanks & Regards</p><p style='font-family:Arial; font-weight:bold;font-size:14px; color:#06C;'>" + 
		//	commonProperties.getProperty("regards").trim() + " </p>" + " <p style='font-family:Arial; font-weight:bold;font-size:14px; color:#06C;'> \n \n\n\n \nNote : This is an Auto Generated e-mail Please do not Reply.</p>\n" + "</body></html>";
	public static String buildNo;
	public static String buildURL;
	public static String buildId;
	public static String pbuildNo;
	public static String pbuildURL;
	public static String pbuildId;

	public static void createReportHomePage(String startTime, String endTime, long executionTime, String reportFolderDateTime)
			throws Exception
			{
		mainReportLoaction = String.valueOf(System.getProperty("user.dir")) + System.getProperty("file.separator") + "Reports" + System.getProperty("file.separator") + "Reports(" + reportFolderDateTime + ")" + System.getProperty("file.separator") + "mainReport.html";
		LOGGER.log(Level.INFO, "mainReportLoaction==" + mainReportLoaction);
		log4jName = "log(" + reportFolderDateTime + ").html";
		reportLogLoaction = String.valueOf(System.getProperty("user.dir")) + System.getProperty("file.separator") + "Reports" + System.getProperty("file.separator") + "Reports(" + reportFolderDateTime + ")" + System.getProperty("file.separator") + "logs";
		try
		{
			File report = new File(mainReportLoaction);
			if (report.exists()) {
				report.delete();
			} else {
				report.createNewFile();
			}
			FileWriter fw = new FileWriter(mainReportLoaction, true);
			fw.write(formatTheData(getRequiredDataFromDetailedLog(getTheRequiredDetailedReportName()), startTime, endTime, executionTime));
			fw.close();
			LOGGER.log(Level.INFO, "Sucess: Main report created sucessfully");
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Error: Main report creation failed :", e);
		}
			}

	public static String formatTheData(List<ArrayList<String>> allData, String TestExecutionStartTime, String TestExecutionEndTime, long executionTime)
	{
		ArrayList<String> reportHeader = new ArrayList();
		ArrayList<ArrayList<String>> reportBody = new ArrayList();
		String finalReport = "";
		ArrayList<Date> testExecutedList = new ArrayList();
		String totalExecutionTime = "";
		for (int i = 0; i < allData.size(); i++) {
			try
			{
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				date = dateFormat.parse((String)((ArrayList)allData.get(i)).get(0));
				testExecutedList.add(date);
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Exception :", e);
			}
		}
		reportHeader.add(TestExecutionStartTime);
		reportHeader.add(TestExecutionEndTime);
		for (int i = 0; i < allData.size(); i++) {
			try
			{
				"Passed".equalsIgnoreCase((String)((ArrayList)allData.get(i)).get(3));
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Exception :", e);
			}
		}
		long seconds = executionTime / 1000L;
		long minutes = seconds %= 3600L / 60L;
		long secRemaining = seconds - minutes % 60L * 60L;
		totalExecutionTime = String.valueOf(String.valueOf(minutes) + " mins :" + secRemaining + " secs");
		reportHeader.add(totalExecutionTime);
		for (int i2 = 0; i2 < allData.size(); i2++)
		{
			ArrayList<String> reportBodyLineLevel = new ArrayList();
			reportBodyLineLevel.add((String) ((ArrayList)allData.get(i2)).get(7));
			reportBodyLineLevel.add((String) ((ArrayList)allData.get(i2)).get(4));
			reportBodyLineLevel.add((String) ((ArrayList)allData.get(i2)).get(5));
			reportBodyLineLevel.add((String) ((ArrayList)allData.get(i2)).get(6));
			if ("Passed".equalsIgnoreCase((String)((ArrayList)allData.get(i2)).get(3))) {
				reportBodyLineLevel.add("green");
			} else {
				reportBodyLineLevel.add("red");
			}
			if ("Passed".equalsIgnoreCase((String)((ArrayList)allData.get(i2)).get(3))) {
				reportBodyLineLevel.add("Passed");
			} else {
				reportBodyLineLevel.add("Failed");
			}
			reportBody.add(reportBodyLineLevel);
		}
		finalReport = createMainReport(reportHeader, reportBody);
		return finalReport;
	}

	public static List<ArrayList<String>> getRequiredDataFromDetailedLog(LinkedHashSet<String> ListOfFiles)
	{
		Iterator<String> itr = ListOfFiles.iterator();
		String report = "";
		ArrayList<ArrayList<String>> dataSetPerTestReport = new ArrayList();
		do
		{
			String file = (String)itr.next();
			ArrayList<String> dataSet = new ArrayList();
			report = getReport(file);
			try
			{
				dataSet.add(getStringFromReport(report, file, "Test Executed On</td><td class ='summarybody' width=450>", "<"));
				verifyMap.put(file, getStringFromReport(report, file, "Test Execution Start Time</td><td class ='summarybody' width=450>", "<"));
				dataSet.add(getStringFromReport(report, file, "Test Execution Start Time</td><td class ='summarybody' width=450>", "<"));
				dataSet.add(getStringFromReport(report, file, "Test Execution End Time</td><td class ='summarybody'>", "<"));
				if ((report.contains("tick.png")) && (!report.contains("cross.png"))) {
					dataSet.add("Passed");
				} else {
					dataSet.add("Failed");
				}
				dataSet.add(getStringFromReport(report, file, "<TD class='tsgen' width=155px>", "<"));
				dataSet.add(getStringFromReport(report, file, "Browser Type</td><td class ='summarybody'>", "<"));
				dataSet.add(getStringFromReport(report, file, "Total Execution Time</td><td class ='summarybody'>", "<"));
				dataSet.add(file);
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "Error occured : While fetching data from log.", e);
			}
			dataSetPerTestReport.add(dataSet);
		} while (itr.hasNext());
		return dataSetPerTestReport;
	}

	public static LinkedHashSet<String> getTheRequiredDetailedReportName()
	{
		ArrayList<String> reportNameList = new ArrayList();
		ArrayList<String> ReportList = new ArrayList();
		File folder = new File(reportLogLoaction);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				reportNameList.add(listOfFiles[i].getName());
			}
		}
		for (int i = 0; i < reportNameList.size(); i++)
		{
			String dataId = "";
			int noOfReport = 0;
			String[] reportName = ((String)reportNameList.get(i)).split("_DataId_");
			for (int j = 0; j < reportNameList.size(); j++) {
				if (((String)reportNameList.get(j)).contains(reportName[0]))
				{
					noOfReport++;
					String[] getDataId = reportName[1].split("_");
					dataId = getDataId[0];
				}
			}
			if (noOfReport == 2) {
				ReportList.add(String.valueOf(reportName[0]) + "_DataId_" + dataId + "_2");
			} else {
				ReportList.add(String.valueOf(reportName[0]) + "_DataId_" + dataId + "_1");
			}
		}
		LinkedHashSet<String> finalReportList = new LinkedHashSet();
		finalReportList.addAll(reportNameList);
		ReportList.clear();
		ReportList.addAll(reportNameList);
		return finalReportList;
	}

	public static String getReport(String fileName)
	{
		String filePath = String.valueOf(reportLogLoaction) + System.getProperty("file.separator") + fileName;
		String report = "";
		String line = "";
		try
		{
			FileReader inputFile = new FileReader(filePath);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			while ((line = bufferReader.readLine()) != null) {
				report = String.valueOf(report) + line.trim();
			}
			bufferReader.close();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "Error while reading file line by line:" + e.getMessage());
		}
		return report;
	}

	public static String getStringFromReport(String report, String fileName, String firstString, String secondString)
	{
		String middleString = "";
		try
		{
			String[] splitReport = report.split(firstString);
			String[] splitAgainReport = splitReport[1].split(secondString);
			middleString = splitAgainReport[0];
		}
		catch (Exception e)
		{
			LOGGER.log(Level.INFO, "Please provide at least one log message in the log file >> " + fileName);
		}
		return middleString;
	}

	public static String createMainReport(List<String> reportHeader, List<ArrayList<String>> reportBody)
	{
		String createReportBody = "";
		String createMailReportBody = "";
		String newFileName = "";
		for (int i = 0; i < reportBody.size(); i++)
		{
			String reportbody = ReportBody;
			String mailReportBody = MailReportBody;
			String fileName = (String)((ArrayList)reportBody.get(i)).get(0);
			if (fileName.contains("_1."))
			{
				newFileName = fileName.replace("_1.", "_PrimaryExecution.");
				totalTcs += 1;
			}
			else if (fileName.contains("_2."))
			{
				newFileName = fileName.replace("_2.", "_SecondayExecution.");
			}
			
			System.out.println("((ArrayList)reportBody.get(i)).get(5)): "+((ArrayList)reportBody.get(i)).get(5));
			if ("Passed".equalsIgnoreCase((String)((ArrayList)reportBody.get(i)).get(5))) 
			{
				noOfPassed += 1;
			}
			createReportBody = createReportBody + reportbody.replace("$$SNo$$", String.valueOf(i + 1)).replace("$$DeatiledExecutionReportFileLink$$", (CharSequence)((ArrayList)reportBody.get(i)).get(0)).replace("$$FileName$$", newFileName).replace("$$TestCaseName$$", (CharSequence)((ArrayList)reportBody.get(i)).get(1)).replace("$$BrowserType$$", (CharSequence)((ArrayList)reportBody.get(i)).get(2)).replace("$$ExecutionTime$$", (CharSequence)((ArrayList)reportBody.get(i)).get(3)).replace("$$StatusColor$$", (CharSequence)((ArrayList)reportBody.get(i)).get(4)).replace("$$Status$$", (CharSequence)((ArrayList)reportBody.get(i)).get(5));

			createMailReportBody = createMailReportBody + mailReportBody.replace("$$SNo$$", String.valueOf(i + 1)).replace("$$DeatiledExecutionReportFileLink$$", (CharSequence)((ArrayList)reportBody.get(i)).get(0)).replace("$$FileName$$", newFileName).replace("$$TestCaseName$$", (CharSequence)((ArrayList)reportBody.get(i)).get(1)).replace("$$BrowserType$$", (CharSequence)((ArrayList)reportBody.get(i)).get(2)).replace("$$ExecutionTime$$", (CharSequence)((ArrayList)reportBody.get(i)).get(3)).replace("$$StatusColor$$", (CharSequence)((ArrayList)reportBody.get(i)).get(4)).replace("$$Status$$", (CharSequence)((ArrayList)reportBody.get(i)).get(5));
		}
		noOfFailed = totalTcs - noOfPassed;
		LOGGER.log(Level.INFO, "totalTcs==" + totalTcs);
		LOGGER.log(Level.INFO, "noOfPassed==" + noOfPassed);
		LOGGER.log(Level.INFO, "noOfFailed==" + noOfFailed);

		DecimalFormat decimalFormat = new DecimalFormat("#.##");

		float passPercentage = noOfPassed * 100.0F / totalTcs;
		Double passPer = Double.valueOf(Double.parseDouble(decimalFormat.format(passPercentage)));
		float failPercentage = noOfFailed * 100.0F / totalTcs;
		Double failedPer = Double.valueOf(Double.parseDouble(decimalFormat.format(failPercentage)));

		ReportHeader = ReportHeader.replace("$$TestExecutionStartTime$$", (CharSequence)reportHeader.get(0)).replace("$$TestExecutionEndTime$$", (CharSequence)reportHeader.get(1)).replace("$$TotalExecutionTime$$", (CharSequence)reportHeader.get(2)).replace("$$BuildURL$$", buildURL).replace("$$BuildID$$", buildId).replace("$$BuildNumber$$", buildNo).replace("$$NumberOfTestScript$$", String.valueOf(totalTcs)).replace("$$NumberOfPASSED$$", noOfPassed + "(" + passPer + "%)").replace("$$NumberOfFAILED$$", noOfFailed + "(" + failedPer + "%)").replace("$$ParentBuildURL$$", pbuildURL).replace("$$ParentBuildID$$", pbuildId).replace("$$ParentBuildNumber$$", pbuildNo).replace("$$SonarURL$$", sonarUrl).replace("$$log4j$$", log4jName);

		MailReportHeader = MailReportHeader.replace("$$TestExecutionStartTime$$", (CharSequence)reportHeader.get(0)).replace("$$TestExecutionEndTime$$", (CharSequence)reportHeader.get(1)).replace("$$TotalExecutionTime$$", (CharSequence)reportHeader.get(2)).replace("$$BuildURL$$", buildURL).replace("$$BuildID$$", buildId).replace("$$BuildNumber$$", buildNo).replace("$$NumberOfTestScript$$", String.valueOf(totalTcs)).replace("$$NumberOfPASSED$$", noOfPassed + "(" + passPer + "%)").replace("$$NumberOfFAILED$$", noOfFailed + "(" + failedPer + "%)").replace("$$ParentBuildURL$$", pbuildURL).replace("$$ParentBuildID$$", pbuildId).replace("$$ParentBuildNumber$$", pbuildNo).replace("$$SonarURL$$", sonarUrl);
		String mainReport = ReportHeader + createReportBody + ReportFooter;

		//mailReport = MailReportHeader + createMailReportBody + MailReportFooter;
		return mainReport;
	}
}
