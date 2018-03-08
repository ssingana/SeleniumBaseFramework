package com.web.ui.automation.listeners;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.Utils;
import org.testng.log4testng.Logger;
import org.testng.xml.XmlSuite;

import com.web.ui.automation.configurations.MailConfiguration;
import com.web.ui.automation.configurations.WebdriverConfiguration;
import com.web.ui.automation.util.CommonUtil;
import com.web.ui.automation.util.HtmlTagsUtil;
import com.web.ui.automation.util.MailUtil;

public class ReporterImpl implements IReporter {

	private static final Logger LOGGER = Logger.getLogger(ReporterImpl.class);

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		DateFormat dateFormat = new SimpleDateFormat(WebdriverConfiguration.getDateFormat());
		Date date = new Date();
		WebdriverConfiguration.endTime = dateFormat.format(date).toString();
		try {
			if (WebdriverConfiguration.isCustomReportEnabled()) {
				generateDetailedReport(suites, outputDirectory, MailConfiguration.isSummaryMailEnabled());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void generateDetailedReport(List<ISuite> suites, String outputDirectory, Boolean isSummaryEmailEnabled) {

		int totalFailedTests = 0;
		int totalPassedTests = 0;
		int totalSkippedTests = 0;
		int totalExcudedTests = 0;

		for (ISuite suite : suites) {
			if (suite.getResults().size() == 0) {
				continue;
			}

			int suiteFailedTests = 0;
			int suitePassedTests = 0;
			int suiteSkippedTests = 0;
			int suiteIgnoredTests = 0;

			Map<String, ISuiteResult> results = suite.getResults();
			for (ISuiteResult suiteResult : results.values()) {
				ITestContext suiteTestContext = suiteResult.getTestContext();
				suiteFailedTests += suiteTestContext.getFailedTests().size();
				suitePassedTests += suiteTestContext.getPassedTests().size();
				suiteSkippedTests += suiteTestContext.getSkippedTests().size();
				suiteIgnoredTests += suiteTestContext.getExcludedMethods().size();
			}

			totalFailedTests += suiteFailedTests;
			totalPassedTests += suitePassedTests;
			totalSkippedTests += suiteSkippedTests;
			totalExcudedTests += suiteIgnoredTests;
		}

		String totalExecutionTime = CommonUtil.calculateExecutionTime();
		
		// Custom report for the index file starts here.

		StringBuffer indexFile = new StringBuffer();
		StringBuffer summaryFile = new StringBuffer();

		summaryFile.append(HtmlTagsUtil.getStartTag("Execution Summary"));
		summaryFile.append(HtmlTagsUtil.getStyleTag());
		summaryFile.append(HtmlTagsUtil.getHeadEndTag());
		summaryFile.append(HtmlTagsUtil.getBodyStartTag());
		summaryFile.append(HtmlTagsUtil.getH1Tag("Test Execution Summay (Total Time = " + totalExecutionTime + " )"));
		summaryFile.append(HtmlTagsUtil.getConfigurationTable());
		summaryFile.append(HtmlTagsUtil.getTestCountByStatusTable(totalPassedTests, totalFailedTests, totalSkippedTests,
				totalExcudedTests));
		summaryFile.append(HtmlTagsUtil.getReportTableStartTag());

		indexFile.append(HtmlTagsUtil.getStartTag("Execution Report Details"));
		indexFile.append(HtmlTagsUtil.getStyleTag());
		indexFile.append(HtmlTagsUtil.getHeadEndTag());
		indexFile.append(HtmlTagsUtil.getBodyStartTag());
		indexFile.append(HtmlTagsUtil.getH1Tag("Test Execution Detailed Report (Total Time = " + totalExecutionTime + " )"));
		// indexFile.append(HtmlTags.getH2Tag("Configuration Details"));
		indexFile.append(HtmlTagsUtil.getConfigurationTable());
		indexFile.append(HtmlTagsUtil.getTestCountByStatusTable(totalPassedTests, totalFailedTests, totalSkippedTests,
				totalExcudedTests));
		// indexFile.append(HtmlTags.getH2Tag("Test Report Details"));
		indexFile.append(HtmlTagsUtil.getReportTableStartTag());
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (ISuiteResult result : suiteResults.values()) {
				IResultMap passedTests = result.getTestContext().getPassedTests();
				Set<ITestResult> testResults = passedTests.getAllResults();
				for (ITestResult iTestResult : testResults) {
					indexFile.append(HtmlTagsUtil.getPassedStartTRTag());
					indexFile.append(HtmlTagsUtil.getSuiteTD(iTestResult.getAttribute("TestCaseOwner").toString()));
					indexFile.append(HtmlTagsUtil.getTestNameTD(iTestResult.getMethod().getMethodName()));
					indexFile.append(HtmlTagsUtil.getFullyQualifiedPathTD(iTestResult.getMethod().getQualifiedName()));
					indexFile.append(HtmlTagsUtil.getStatusTD(getStatusString(iTestResult.getStatus())));
					indexFile.append(HtmlTagsUtil.getErrorDetailsTD(getLogFilePath(iTestResult.getThrowable(),
							outputDirectory, iTestResult.getMethod().getMethodName())));
					indexFile.append(HtmlTagsUtil.getEndTRTag());
				}
				IResultMap failedTests = result.getTestContext().getFailedTests();
				testResults = failedTests.getAllResults();
				for (ITestResult iTestResult : testResults) {
					indexFile.append(HtmlTagsUtil.getFailedStartTRTag());
					indexFile.append(HtmlTagsUtil.getSuiteTD(iTestResult.getAttribute("TestCaseOwner").toString()));
					indexFile.append(HtmlTagsUtil.getTestNameTD(iTestResult.getMethod().getMethodName()));
					indexFile.append(HtmlTagsUtil.getFullyQualifiedPathTD(iTestResult.getMethod().getQualifiedName()));
					indexFile.append(HtmlTagsUtil.getStatusTD(getStatusString(iTestResult.getStatus())));
					indexFile.append(HtmlTagsUtil.getErrorDetailsTD(getLogFilePath(iTestResult.getThrowable(),
							outputDirectory, iTestResult.getMethod().getMethodName())));
					indexFile.append(HtmlTagsUtil.getEndTRTag());

					summaryFile.append(HtmlTagsUtil.getFailedStartTRTag());
					summaryFile.append(HtmlTagsUtil.getSuiteTD(iTestResult.getAttribute("TestCaseOwner").toString()));
					summaryFile.append(HtmlTagsUtil.getTestNameTD(iTestResult.getMethod().getMethodName()));
					summaryFile
							.append(HtmlTagsUtil.getFullyQualifiedPathTD(iTestResult.getMethod().getQualifiedName()));
					summaryFile.append(HtmlTagsUtil.getStatusTD(getStatusString(iTestResult.getStatus())));
					summaryFile.append(HtmlTagsUtil.getErrorMessageTD(iTestResult.getThrowable().getMessage()));
					summaryFile.append(HtmlTagsUtil.getEndTRTag());

				}
				IResultMap skippedTests = result.getTestContext().getSkippedTests();
				testResults = skippedTests.getAllResults();
				for (ITestResult iTestResult : testResults) {
					indexFile.append(HtmlTagsUtil.getSkippedStartTRTag());
					indexFile.append(HtmlTagsUtil.getSuiteTD(iTestResult.getAttribute("TestCaseOwner").toString()));
					indexFile.append(HtmlTagsUtil.getTestNameTD(iTestResult.getMethod().getMethodName()));
					indexFile.append(HtmlTagsUtil.getFullyQualifiedPathTD(iTestResult.getMethod().getQualifiedName()));
					indexFile.append(HtmlTagsUtil.getStatusTD(getStatusString(iTestResult.getStatus())));
					indexFile.append(HtmlTagsUtil.getErrorDetailsTD(getLogFilePath(iTestResult.getThrowable(),
							outputDirectory, iTestResult.getMethod().getMethodName())));
					indexFile.append(HtmlTagsUtil.getEndTRTag());
					
					summaryFile.append(HtmlTagsUtil.getSkippedStartTRTag());
					summaryFile.append(HtmlTagsUtil.getSuiteTD(iTestResult.getAttribute("TestCaseOwner").toString()));
					summaryFile.append(HtmlTagsUtil.getTestNameTD(iTestResult.getMethod().getMethodName()));
					summaryFile.append(HtmlTagsUtil.getFullyQualifiedPathTD(iTestResult.getMethod().getQualifiedName()));
					summaryFile.append(HtmlTagsUtil.getStatusTD(getStatusString(iTestResult.getStatus())));
					summaryFile.append(HtmlTagsUtil.getErrorMessageTD(iTestResult.getThrowable().getMessage()));
					summaryFile.append(HtmlTagsUtil.getEndTRTag());
				}

				Collection<ITestNGMethod> excludedMethods = result.getTestContext().getExcludedMethods();
				for (ITestNGMethod testngMethod : excludedMethods) {
					indexFile.append(HtmlTagsUtil.getIgnoredStartTRTag());
					indexFile.append(HtmlTagsUtil.getSuiteTD(suite.getName()));
					indexFile.append(HtmlTagsUtil.getTestNameTD(testngMethod.getMethodName()));
					indexFile.append(HtmlTagsUtil.getFullyQualifiedPathTD(testngMethod.getQualifiedName()));
					indexFile.append(HtmlTagsUtil.getStatusTD("excluded"));
					indexFile.append(HtmlTagsUtil.getErrorDetailsTD(null));
					indexFile.append(HtmlTagsUtil.getEndTRTag());
					
					summaryFile.append(HtmlTagsUtil.getIgnoredStartTRTag());
					summaryFile.append(HtmlTagsUtil.getSuiteTD(suite.getName()));
					summaryFile.append(HtmlTagsUtil.getTestNameTD(testngMethod.getMethodName()));
					summaryFile.append(HtmlTagsUtil.getFullyQualifiedPathTD(testngMethod.getQualifiedName()));
					summaryFile.append(HtmlTagsUtil.getStatusTD("excluded"));
					summaryFile.append(HtmlTagsUtil.getErrorMessageTD("Not Yet Implemented"));
					summaryFile.append(HtmlTagsUtil.getEndTRTag());
					
				}
			}
		}
		String fileName = "summary.html";
		summaryFile.append(HtmlTagsUtil.getEndTableTag());
		summaryFile.append(HtmlTagsUtil.getEndTag());
		Utils.writeFile(outputDirectory, fileName, summaryFile.toString());
		if (isSummaryEmailEnabled) {
			try {
				String cc = MailConfiguration.getMailCC();
				String to = MailConfiguration.getMailTo();
				String subject = "Execution Summary Report - Total : "
						+ (totalPassedTests + totalFailedTests + totalSkippedTests + totalExcudedTests) + "; Passed : "
						+ totalPassedTests + "; Failed : " + totalFailedTests + "; Skipped : " + totalSkippedTests
						+ "; Ignored : " + totalExcudedTests;
				StringBuffer body = new StringBuffer();
				body.append("Hello ,<br/><br/>");
				body.append("Execution Summary Report is in below mentioned location.<br/><br/>Ip Address : "
						+ CommonUtil.getSystemIpAddress() + "<br/>Folder Path : " + outputDirectory);
				body.append("<br/><br/>Thank you <br/> Automation Team.");
				MailUtil mail = new MailUtil();
				if (cc == null || cc.equals("")) {
					mail.sendMail(to.split(","), subject, body.toString(), outputDirectory + File.separator + fileName);
				} else {
					mail.sendMail(to.split(","), subject, body, cc.split(","),
							outputDirectory + File.separator + fileName);
				}
				LOGGER.info("Suites Summary mail sent");
			} catch (Exception e) {
				LOGGER.error("Mail sending failed!!");
				e.printStackTrace();
			}
		}
		indexFile.append(HtmlTagsUtil.getEndTableTag());
		indexFile.append(HtmlTagsUtil.getEndTag());
		fileName = "index.html";
		Utils.writeFile(outputDirectory, fileName, indexFile.toString());

		// custom report ends here.

	}

	private String getStatusString(int testResultStatus) {
		switch (testResultStatus) {
		case ITestResult.SUCCESS:
			return "Passed";
		case ITestResult.FAILURE:
			return "Failed";
		case ITestResult.SKIP:
			return "Skipped";
		case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
			return "SUCCESS_PERCENTAGE_FAILURE";
		}
		return null;
	}

	private String getLogFilePath(Throwable ex, String outputDirectory, String filename) {
		if (ex != null) {
			StringWriter stackTrace = new StringWriter();
			ex.printStackTrace(new PrintWriter(stackTrace));
			Utils.writeFile(outputDirectory + File.separator + "logs", filename + ".html",
					(stackTrace.toString()).replace(System.getProperty("line.separator"), "<br/>\n"));
			return "./logs/" + filename + ".html";
		}
		return null;
	}

}
