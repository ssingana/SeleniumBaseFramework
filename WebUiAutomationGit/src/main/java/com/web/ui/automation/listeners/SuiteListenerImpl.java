package com.web.ui.automation.listeners;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.log4testng.Logger;



public class SuiteListenerImpl implements ISuiteListener {

	
	private static final Logger LOGGER = Logger.getLogger(SuiteListenerImpl.class);
	
	@Override
	public void onFinish(ISuite suite) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		suite.setAttribute("suiteEndTime",dateFormat.format(date));	
		LOGGER.info(suite.getName() + " started the execution " + dateFormat.format(date));	
	}

	@Override
	public void onStart(ISuite suite) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		suite.setAttribute("suiteStartTime",dateFormat.format(date));
		LOGGER.info(suite.getName() + " ends the execution on " + dateFormat.format(date));
	}

}
