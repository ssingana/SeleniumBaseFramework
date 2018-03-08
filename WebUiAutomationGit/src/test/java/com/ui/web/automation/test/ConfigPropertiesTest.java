package com.ui.web.automation.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class ConfigPropertiesTest  {
	
	public WebDriver driver;
	
	
	@Test
	public void test2(){
		Assert.assertEquals("test","test");
	}
	
	@Test
	public void test3() {
		Assert.assertFalse(true);
	}
	
	@Test
	public void test4(){
		Assert.assertEquals("test", "test1");
	}
	@Test
	public void testCaseSkipException(){
			System.out.println("Im in skip exception");
			throw new SkipException("Skipping this exception");
		}
	
	
	@Test(enabled=false)
	public void test5(){
		Assert.assertEquals("test", "tes2t");
	}

}
