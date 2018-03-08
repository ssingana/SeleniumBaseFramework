package com.web.ui.automation.util;

import com.web.ui.automation.configurations.WebdriverConfiguration;

public class HtmlTagsUtil {

	public static String getStartTag(String title) {
		return new String("<!DOCTYPE html><html><head><title>" + title + "</title>");
	}

	public static String getStyleTag() {
		return new String(
				"<style> table, th, td { border: 1px solid black; border-collapse: collapse; } th, td { padding: 5px; text-align: left; } </style>");
	}

	public static String getHeadEndTag() {
		return new String("</head>");
	}

	public static String getBodyStartTag() {
		return new String("<body style='margin-left:80px; margin-right:80px'>");
	}

	public static String getBodyEndTag() {
		return new String("</body>");
	}

	public static String getEndTag() {
		return new String("</body></html>");
	}

	public static String getH2Tag(String header) {
		return new String("<h2>" + header + "</h2>");
	}

	public static String getH1Tag(String h1Title) {
		return "<h1 style='text-align: center'>" + h1Title + "</h1>";
	}

	public static StringBuffer getConfigurationTable() {
		return new StringBuffer("<table style='width:50%; float: left'><tr><th>Application URL</th><td>"
				+ WebdriverConfiguration.getApplicationUrl() + "</td></tr><tr><th>Browser Name</th><td>"
				+ WebdriverConfiguration.getBrowserName() + "</td></tr><tr><th>Browser Version</th><td>"
				+ WebdriverConfiguration.getBrowserVersion() + "</td></tr><tr><th>Platform</th><td>"
				+ WebdriverConfiguration.getPlatform() + "</td></tr><tr><th>Start Time (yyyy/mm/dd hh:mm:ss)</th><td>"
				+ WebdriverConfiguration.startTime + "</td></tr><tr><th>End Time (yyyy/mm/dd hh:mm:ss)</th><td>"
				+ WebdriverConfiguration.endTime + "</td></tr></table>");
	}

	public static StringBuffer getTestCountByStatusTable(int passedCount, int failedCount, int skippedCount,
			int ignoredCount) {
		int totalTests = passedCount + failedCount + skippedCount + ignoredCount;
		return new StringBuffer(
				"<table style='width:35%; float: left'><tr bgcolor='green'><th style='width:35%'>Passed Testcases</th><td>"
						+ passedCount + "  (" + CommonUtil.percentageCalculator(totalTests, passedCount)
						+ ")</td></tr><tr bgcolor='red'><th>Failed Testcases</th><td>" + failedCount + "  ("
						+ CommonUtil.percentageCalculator(totalTests, failedCount)
						+ ")</td></tr><tr bgcolor='yellow'><th>Skipped Testcases</th><td>" + skippedCount + "  ("
						+ CommonUtil.percentageCalculator(totalTests, skippedCount)
						+ ")</td></tr><tr bgcolor='lightblue'><th>Ignored Testcases</th><td>" + ignoredCount + "  ("
						+ CommonUtil.percentageCalculator(totalTests, ignoredCount) + "</td></tr>"
						+ "<tr><th><b>Total Testcases</b></th><td>" + totalTests + "</td></tr></table>");

	}

	public static StringBuffer getReportTableStartTag() {
		return new StringBuffer(
				"<table style='width:100%'><tr><th>Test Owner</th><th>Test Case</th><th>Test File Path</th><th>Status</th><th>Log Details</th></tr>");
	}

	public static String getEndTableTag() {
		return new String("</table>");
	}

	public static String getEndTRTag() {
		return new String("</tr>");
	}

	public static String getPassedStartTRTag() {
		return new String("<tr bgcolor='green'>");
	}

	public static String getFailedStartTRTag() {
		return new String("<tr bgcolor='red'>");
	}

	public static String getSkippedStartTRTag() {
		return new String("<tr bgcolor='yellow'>");
	}

	public static String getIgnoredStartTRTag() {
		return new String("<tr bgcolor='lightblue'>");
	}

	public static String getSuiteTD(String suiteName) {
		return new String("<td style='width:15%'>" + suiteName + "</td>");
	}

	public static String getTestNameTD(String testName) {
		return new String("<td style='width:15%'>" + testName + "</td>");
	}

	public static String getFullyQualifiedPathTD(String fullPath) {
		return new String("<td style='width:15%'>" + fullPath + "</td>");
	}

	public static String getStatusTD(String status) {
		return new String("<td style='width:15%'>" + status + "</td>");
	}

	public static String getErrorDetailsTD(String errorDetails) {
		if (errorDetails != null)
			return new String("<td style='width:15%'><a href=\"" + errorDetails + "\">Click For Log Details</a></td>");
		return "<td style='width:15%'></td>";
	}

	public static String getErrorMessageTD(String errorMessage) {
		if (errorMessage != null)
			return new String("<td style='width:15%'>" + errorMessage + "</td>");
		return "<td style='width:15%'></td>";
	}

}
