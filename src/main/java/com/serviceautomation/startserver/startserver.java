package com.serviceautomation.startserver;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class startserver {

	
	@Test
	public void startserver() throws IOException, InterruptedException{
		Runtime.getRuntime().exec("cmd /c start C:\\Users\\aksaini\\Desktop\\a.bat");
		//time so that server can start running
		Thread.sleep(10000);
	}
}
