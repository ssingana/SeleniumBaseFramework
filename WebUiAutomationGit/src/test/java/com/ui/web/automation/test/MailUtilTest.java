package com.ui.web.automation.test;

import org.testng.annotations.Test;

import com.web.ui.automation.util.MailUtil;

public class MailUtilTest {

	MailUtil mailUtil = new MailUtil();
	String[] toList = new String[1];

	
	@Test
	public void testMailUtilMethodsWithAttachment() {
		toList[0]="email.to@email.com";
		mailUtil.sendMail(toList, "Test", new String("Testting Mail..."),"C:\\Users\\irg6kor\\eclipse-workspace\\WebUiAutomation\\smoketests.xml");
	}
}
