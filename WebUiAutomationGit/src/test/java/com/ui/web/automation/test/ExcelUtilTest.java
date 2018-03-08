package com.ui.web.automation.test;

import com.web.ui.automation.util.ExcelUtil;

public class ExcelUtilTest {
	
	public static void main(String[] args) {
		try {
			ExcelUtil excelUtil = new ExcelUtil("C:\\Selenium\\ExcelUtilTestData\\TestData.xlsx", "Login");
			for(int row = 0; row <=6; row++) {
				for(int col=0;col<3;col++) {
					String cellData = excelUtil.getCellData(row, col);
					System.out.println("Cell data located at " + row + " " + col + " :" + cellData);
					if(col == 2) {
						System.out.println("this calls when the col == 2");
						excelUtil.setCellData("result", row, col);
					}
				}
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}

	
		
	}


}
