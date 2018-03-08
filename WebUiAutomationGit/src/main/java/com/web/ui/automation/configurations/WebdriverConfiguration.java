package com.web.ui.automation.configurations;

import java.util.Properties;

import org.testng.log4testng.Logger;

public class WebdriverConfiguration {

	private static final Logger LOGGER = Logger.getLogger(WebdriverConfiguration.class);
	public static Properties properties = new Properties();
	public static String startTime;
	public static String endTime;

	static {
		loadProperties();
	}

	private static void loadProperties() {
		try {
			if (properties.isEmpty()) {
				properties.load(WebdriverConfiguration.class.getResourceAsStream("/config/webdriver.properties"));
				// System.out.println(getApplicationUrl() + " ::::: " + getImplicitWait() + "
				// ::::: " + getBrowserName());
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while loading configuration properties " + e.getMessage());
			System.exit(1);
		}
	}

	public static String getApplicationUrl() {
		return properties.getProperty("application.url");
	}

	public static String getBrowserName() {
		return properties.getProperty("browser.name");
	}

	public static int getImplicitWait() {
		try {
			return Integer.parseInt(properties.getProperty("implicitWait"));
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getPageLoadTimeout() {
		try {
			return Integer.parseInt(properties.getProperty("pageLoadTimeout"));
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getWaitTimeOut() {
		try {
			return Integer.parseInt(properties.getProperty("waitTimeOut"));
		} catch (Exception e) {
			return 0;
		}
	}

	public static boolean getRunOnGrid() {
		try {
			return Boolean.parseBoolean(properties.getProperty("runOnGrid"));
		} catch (Exception e) {
			return false;
		}
	}

	public static String getHubUrl() {
		return properties.getProperty("hubURL");
	}

	public static boolean isCaptureScreenshotEnabled() {
		try {
			return Boolean.parseBoolean(properties.getProperty("captureScreenshot"));
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean getWindowMaximize() {
		try {
			return Boolean.parseBoolean(properties.getProperty("windowMaximize"));
		} catch (Exception e) {
			return false;
		}
	}

	public static void setBrowserVersion(String version) {
		properties.setProperty("version", version);
	}

	public static String getBrowserVersion() {
		return properties.getProperty("version");
	}

	public static void setPlatform(String platform) {
		properties.setProperty("platform", platform);
	}

	public static String getPlatform() {
		return properties.getProperty("platform");
	}

	public static void setBrowserName(String browserName) {
		properties.setProperty("browserName", browserName);
	}

	public static boolean isCustomReportEnabled() {
		try {
			return Boolean.parseBoolean(properties.getProperty("custom.report.enable"));
		} catch (Exception e) {
			return false;
		}
	}

	public static String getProfile() {
		return properties.getProperty("browser.profile");
	}

	public static String getDateFormat() {
		return properties.getProperty("date.format");
	}

	public static String getFileDownloadPath() {
		return properties.getProperty("file.download.path");
	}

	public static boolean isDownloadEnabled() {
		try {
			return Boolean.parseBoolean(properties.getProperty("file.download.enabled"));
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isHeadlessEnabled() {
		try {
			return Boolean.parseBoolean(properties.getProperty("browser.headless.enabled"));
		} catch (Exception e) {
			return false;
		}
	}
}
