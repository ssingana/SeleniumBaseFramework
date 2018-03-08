package com.web.ui.automation.listeners;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.IExecutionListener;
import org.testng.log4testng.Logger;

import com.web.ui.automation.configurations.WebdriverConfiguration;

public class ExecutionListenerImpl implements IExecutionListener {
	
	private static final Logger LOGGER = Logger.getLogger(ExecutionListenerImpl.class);

	@Override
	public void onExecutionStart() {
		LOGGER.info("Execution started");
		DateFormat dateFormat = new SimpleDateFormat(WebdriverConfiguration.getDateFormat());
		Date date = new Date();
		WebdriverConfiguration.startTime = dateFormat.format(date).toString();
	}

	@Override
	public void onExecutionFinish() {
	}

}
