package com.web.ui.automation.listeners;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import com.web.ui.automation.configurations.WebdriverConfiguration;
import com.web.ui.automation.custom.annotations.Email;
import com.web.ui.automation.driver.DriverFactory;
import com.web.ui.automation.driver.DriverManager;
import com.web.ui.automation.util.MailUtil;
import com.web.ui.automation.util.ScreenshotUtil;

public class InvokedMethodListenerImpl implements IInvokedMethodListener {

	private static final Logger LOGGER = Logger.getLogger(InvokedMethodListenerImpl.class);

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		/*
		 * String email =
		 * method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(
		 * Email.class).id(); MailUtil sendMail = new MailUtil();
		 * sendMail.sendMail(email.split(","), method.getTestMethod().getMethodName() +
		 * " Started", "Thanks");
		 */
		if (method.isTestMethod()) {
			String browserName = WebdriverConfiguration.getBrowserName();
			WebDriver driver = DriverFactory.createInstance(browserName);
			driver.manage().timeouts().pageLoadTimeout(WebdriverConfiguration.getPageLoadTimeout(), TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(WebdriverConfiguration.getImplicitWait(), TimeUnit.SECONDS);
			driver.manage().window().maximize();
			DriverManager.setWebDriver(driver);
			DriverManager.getWebDriver().get(WebdriverConfiguration.getApplicationUrl());
			LOGGER.info("WebDriver created and opened the url successfully");
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		try {
			if (method.isTestMethod()) {
				Email emailAnnotation = method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(Email.class);
				if(emailAnnotation != null) {
					testResult.setAttribute("TestCaseOwner", method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(Email.class).id());
				} else {
					testResult.setAttribute("TestCaseOwner", "Add Email Annotation");
				}
				if (testResult.getStatus() == ITestResult.FAILURE) {
					String screenshotFileName = ScreenshotUtil.getScreenshotFileName(DriverManager.getWebDriver(),
							testResult);
					if (screenshotFileName != null) {
						testResult.setAttribute("screenshotFileName", screenshotFileName);
					}
					Email email = method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(Email.class);
					
					MailUtil sendMail = new MailUtil();
					if (email != null) {
						String emailId = email.id();
						if (screenshotFileName != "" && screenshotFileName != null) {
							sendMail.sendMail(emailId.split(","),
									"Test Method : " + method.getTestMethod().getMethodName() + " Failed",
									getLogDetails(testResult.getThrowable()),
									ScreenshotUtil.getFilePath(testResult, screenshotFileName));
						} else {
							sendMail.sendMail(emailId.split(","),
									"Test Method : " + method.getTestMethod().getMethodName() + " Failed",
									getLogDetails(testResult.getThrowable()));
						}
					}
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//System.out.println("Finally block executing........to quit the driver object.......");
			WebDriver driver = DriverManager.getWebDriver();
			if (driver != null) {
				driver.quit();
				LOGGER.info("WebDriver exists successfully");
			}
		}
	}

	private String getLogDetails(Throwable ex) {
		if (ex != null) {
			StringBuffer body = new StringBuffer();
			body.append("Hello ,<br/><br/>");
			body.append("Please find here the log details for failed testcase.<br/><br/>");
			StringWriter stackTrace = new StringWriter();
			ex.printStackTrace(new PrintWriter(stackTrace));
			body.append((stackTrace.toString()).replace(System.getProperty("line.separator"), "<br/>\n"));
			body.append("<br/><br/>Thank you <br/> Automation Team.");
			//return (stackTrace.toString()).replace(System.getProperty("line.separator"), "<br/>\n");
			return body.toString();
		}
		return null;
	}

}
