package com.ui.web.automation.test;

import com.web.ui.automation.util.DatabaseUtil;

public class DatabaseUtilTest {

	public static void main(String[] args) {
		DatabaseUtil.insertDataIntoDB("C:\\IGPM\\Selenium\\ExcelUtilTestData\\EMPLOYEES_INS.xlsx");
		DatabaseUtil.deleteDataFromDB("C:\\IGPM\\Selenium\\ExcelUtilTestData\\EMPLOYEES_DEL.xlsx");
	}

}
