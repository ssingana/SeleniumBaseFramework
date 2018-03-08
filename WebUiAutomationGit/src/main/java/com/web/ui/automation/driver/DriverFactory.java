package com.web.ui.automation.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.internal.Utils;
import org.testng.log4testng.Logger;

import com.web.ui.automation.configurations.WebdriverConfiguration;

public class DriverFactory {
	private static final Logger LOGGER = Logger.getLogger(DriverFactory.class);

	public static WebDriver createInstance(String browserName) {
		WebDriver driver = null;
		if (browserName.toLowerCase().contains("firefox")) {
			driver = getFirefoxDriver();
			setPlatform(driver);
			setBrowserVersion(driver);
			return driver;
		}
		if (browserName.toLowerCase().contains("internetexplorer")) {
			driver = getInternetExplorerDriver();
			setPlatform(driver);
			setBrowserVersion(driver);
			return driver;
		}
		return driver;
	}

	private static void setBrowserVersion(WebDriver driver) {
		if (WebdriverConfiguration.getBrowserVersion() == null) {
			try {
				String version = ((RemoteWebDriver) driver).getCapabilities().getVersion();
				WebdriverConfiguration.setBrowserVersion(version);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
	}

	private static void setPlatform(WebDriver driver) {
		// ApplicationConfiguration.setPlatform(Platform.getCurrent().toString());
		if (WebdriverConfiguration.getPlatform() == null) {
			if (Platform.getCurrent().is(Platform.WINDOWS)) {
				WebdriverConfiguration.setPlatform(Platform.WINDOWS.toString());
			} else {
				WebdriverConfiguration.setPlatform(Platform.getCurrent().toString());
			}
		}
	}

	private static WebDriver getInternetExplorerDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		System.setProperty("webdriver.ie.driver",
				System.getProperty("user.dir") + "/drivers/" + Platform.WINDOWS.toString() + "/IEDriverServer32.exe");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,
				WebdriverConfiguration.getApplicationUrl());
		capabilities.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 5000);
		InternetExplorerOptions options = new InternetExplorerOptions(capabilities);
		return new InternetExplorerDriver(options);
	}

	private static WebDriver getFirefoxDriver() {
		WebDriver firefoxWebDriver = null;
		try {
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile firefoxProfile = profile.getProfile(WebdriverConfiguration.getProfile());
			String browserLogFile = "browser-logs.txt";
			Utils.writeFile(System.getProperty("user.dir") + "\\test-output", browserLogFile, "browser logs");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
					System.getProperty("user.dir") + "\\test-output\\browser-logs.txt");
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/drivers/" + Platform.WINDOWS.toString() + "/geckodriver32.exe");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(WebdriverConfiguration.isHeadlessEnabled());
			firefoxOptions.setCapability(FirefoxDriver.MARIONETTE, false);
			firefoxProfile.setPreference("browser.startup.page", "0");
			if (WebdriverConfiguration.isDownloadEnabled()) {
				setFileDownloadPreferences(firefoxProfile);
			}
			firefoxOptions.setProfile(firefoxProfile);
			return new FirefoxDriver(firefoxOptions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return firefoxWebDriver;
	}

	private static void setFileDownloadPreferences(FirefoxProfile firefoxProfile) {
		// profile.setPreference("browser.download.folderList", 2);
		// profile.setPreference("browser.download.manager.showWhenStarting", false);
		// profile.setPreference("browser.download.dir",
		// ApplicationConfiguration.getFileDownloadPath());
		// profile.setPreference("browser.helperApps.neverAsk.openFile",
		// "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		// profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
		// "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		// profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		// profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		// profile.setPreference("browser.download.manager.focusWhenStarting", false);
		// profile.setPreference("browser.download.manager.useWindow", false);
		// profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		// profile.setPreference("browser.download.manager.closeWhenDone", false);

		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
		firefoxProfile.setPreference("browser.download.dir", WebdriverConfiguration.getFileDownloadPath());
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document");

	}
}
