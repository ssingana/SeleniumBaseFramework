package com.web.ui.automation.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import com.web.ui.automation.configurations.WebdriverConfiguration;

public class ScreenshotUtil {

	private static final Logger LOGGER = Logger.getLogger(ScreenshotUtil.class);

	public static String getScreenshotFileName(WebDriver driver, ITestResult result) {
		String imageFileName = null;
		if (WebdriverConfiguration.isCaptureScreenshotEnabled()) {
			try {
				byte[] screenshotByes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				imageFileName = result.getMethod().getMethodName() + ".png";
				String imageFilePath = result.getTestContext().getOutputDirectory() + File.separator + "screenshots"
						+ File.separator + imageFileName;
				FileUtils.writeByteArrayToFile(new File(imageFilePath), screenshotByes);
			} catch (Exception e) {
				if (e != null) {
					LOGGER.error("Capturing Screenshot Failed : " + e.getMessage());
				}
				return imageFileName;
			}
		}
		return imageFileName;
	}
	
	public static String getFilePath(ITestResult result, String fileName) {
		return result.getTestContext().getOutputDirectory() + File.separator + "screenshots"
				+ File.separator + fileName;
	}

}
