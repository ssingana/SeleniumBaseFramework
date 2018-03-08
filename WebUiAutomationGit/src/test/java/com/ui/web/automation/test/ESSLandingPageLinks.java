package com.ui.web.automation.test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.web.ui.automation.browser.BrowserHandle;
import com.web.ui.automation.driver.DriverManager;

public class ESSLandingPageLinks {

	@Test
	public void titleTest() {
		BrowserHandle browser = new BrowserHandle(DriverManager.getWebDriver());
		String actual = browser.getTitle();
		System.out.println("Title of the page : " + actual);
		String expected = "Employee Services Landing Page";
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void searchTest() throws Exception {
		BrowserHandle browser = new BrowserHandle(DriverManager.getWebDriver());
		WebElement txtSearch = browser.findElement("name=txtSearch");
		browser.waitForVisible(txtSearch);
		((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].value = arguments[1];", txtSearch,
				"RBEI Holiday");
		//txtSearch.sendKeys("RBEI Holiday");
	}
	
	@Test
	public void clickGeneralLinkTest() {
		BrowserHandle browser = new BrowserHandle(DriverManager.getWebDriver());
		WebElement general = browser.findElement("xpath=//h4[text()=='General']");
		general.click();
		Assert.assertTrue(true);
	}
}
