package com.ui.web.automation.test;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.web.ui.automation.custom.annotations.Email;
import com.web.ui.automation.util.ExcelUtil;

public class SampleTest {

	@Test
	@Email(id = "email.to@email.com")
	public void TestCase1() {
		throw new SkipException("Not yet Implemented.");
	}

	@Test
	@Email(id = "email.to@email.com")
	public void TestCase2() {
		Assert.assertTrue(false);
	}

}
