package com.web.ui.automation.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private XSSFSheet excelWorkSheet;
	private XSSFWorkbook excelWorkBook;
	private String excelFilePath;
	private String excelSheetName;
	private XSSFWorkbook databaseExcelWorkBook;
	private int numberOfColumns;

	/**
	 * This method used for taking the excel file path and creating the work book and assigning to work book variable.
	 * @param excelFilePath
	 * @throws Exception
	 */
	ExcelUtil(String excelFilePathWithDBdata) throws Exception {
		super();
		try {
			FileInputStream excelFile = new FileInputStream(excelFilePathWithDBdata);
			databaseExcelWorkBook = new XSSFWorkbook(excelFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	/**
	 * Constructor to create the excel util object for given file and the sheet name
	 * 
	 * @param excelFilePath
	 * @param excelSheetName
	 * @throws Exception
	 */
	public ExcelUtil(String excelFilePath, String excelSheetName) throws Exception {
		super();
		this.excelFilePath = excelFilePath;
		this.excelSheetName = excelSheetName;
		setExcelFile(excelFilePath, excelSheetName);
	}

	/**
	 * To set the excel file name.
	 * 
	 * @param Path
	 * @param SheetName
	 * @throws Exception
	 */

	private void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			FileInputStream excelFile = new FileInputStream(Path);
			excelWorkBook = new XSSFWorkbook(excelFile);
			excelWorkSheet = excelWorkBook.getSheet(SheetName);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	/**
	 * This method used to read the cell data.
	 * 
	 * @param rowNum
	 * @param colNum
	 * @return
	 * @throws Exception
	 */

	public String getCellData(int rowNum, int colNum) throws Exception {
		try {
			XSSFCell cell = excelWorkSheet.getRow(rowNum).getCell(colNum);
			String CellData = cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * This method to set the data at specified row and column.
	 * 
	 * @param result
	 * @param rowNum
	 * @param colNum
	 * @throws Exception
	 */

	public void setCellData(String result, int rowNum, int colNum) throws Exception {
		try {
			XSSFRow row = excelWorkSheet.getRow(rowNum);
			if (row == null) {
				excelWorkSheet.createRow(rowNum);
				row = excelWorkSheet.getRow(rowNum);
			}
			// System.out.println("Row Details : " + row);
			XSSFCell cell = excelWorkSheet.getRow(rowNum).getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(result);
			} else {
				cell.setCellValue(result);
			}

			FileOutputStream fileOut = new FileOutputStream(getExcelFilePath());
			excelWorkBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			// e.printStackTrace();
			throw (e);

		}

	}

	public String getExcelFilePath() {
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}

	public String getExcelSheetName() {
		return excelSheetName;
	}

	public void setExcelSheetName(String excelSheetName) {
		this.excelSheetName = excelSheetName;
	}
/**
 * This method used to frame the INSERT statements from the excel provided by the end user.
 * @return
 */
	public List<String> getInsertSatementList() {
		List<String> insertStatements = new ArrayList<String>();
		int numberOfSheets = databaseExcelWorkBook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			XSSFSheet sheet = databaseExcelWorkBook.getSheetAt(i);
			String tableName = sheet.getSheetName();
			String columnNames = getColumnNames(sheet);
			String insertSatement = "INSERT INTO " + tableName + "(" + columnNames + " ) VALUES (";
			List<String> valuesList = getValuesSelectList(sheet);
			for (String value : valuesList) {
				StringBuffer sb = new StringBuffer();
				sb.append(insertSatement);
				sb.append(value + ")");
				insertStatements.add(sb.toString());
			}
		}
		return insertStatements;
	}
/**
 * This method used to frame the DELETE statements from the excel provided by the end user.
 * @return
 */
	public List<String> getDeleteSatementList() {
		List<String> deleteStatements = new ArrayList<String>();
		int numberOfSheets = databaseExcelWorkBook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			XSSFSheet sheet = databaseExcelWorkBook.getSheetAt(i);
			String tableName = sheet.getSheetName();
			String columnNames = getColumnNames(sheet);
			String deleteSatement = "DELETE FROM " + tableName + " WHERE " + columnNames + " = ";
			List<String> valuesList = getValuesSelectList(sheet);
			for (String value : valuesList) {
				StringBuffer sb = new StringBuffer();
				sb.append(deleteSatement);
				sb.append(value);
				deleteStatements.add(sb.toString());
			}
		}
		return deleteStatements;
	}
/**
 * This method takes the excel sheet and get all the columns names from the very first row.
 * @param sheet
 * @return
 */
	private String getColumnNames(XSSFSheet sheet) {
		String columnNames = new String();
		int i = 0;
		XSSFRow row = sheet.getRow(0);
		if (row != null) {
			XSSFCell cell = row.getCell(i);
			while (cell != null) {
				columnNames = columnNames + cell.getStringCellValue() + ",";
				i++;
				cell = row.getCell(i);
			}
		}
		numberOfColumns = i;
		columnNames = columnNames.substring(0, columnNames.length() - 1);
		// System.out.println("Columns String : " + columnNames);
		return columnNames;
	}
/**
 * This method used to read the values from the excel and frame those values to fit into the INSERT or DELETE statements.
 * @param sheet
 * @return
 */
	private List<String> getValuesSelectList(XSSFSheet sheet) {
		List<String> values = new ArrayList<String>();
		int rowNum = 1;
		XSSFRow row = sheet.getRow(rowNum);
		while (row != null) {
			String dataValues = new String();
			for (int i = 0; i < numberOfColumns; i++) {
				XSSFCell cell = row.getCell(i);
				// System.out.println("CELL TYPE ENUM : : " + cell.getCellTypeEnum());
				if (cell != null) {
					if (cell.getCellTypeEnum().toString().equalsIgnoreCase("STRING"))
						dataValues = dataValues + "'" + cell.getStringCellValue() + "'" + ",";
					else if (cell.getCellTypeEnum().toString().equalsIgnoreCase("NUMERIC"))
						dataValues = dataValues + cell.getRawValue() + ",";
					else
						System.out.println("GET CELL TYPE ENUM NOT RETURNED");

				} else
					dataValues = dataValues + "" + ",";
			}
			dataValues = dataValues.substring(0, dataValues.length() - 1);
			// System.out.println("Data String : " + dataValues);
			values.add(dataValues);
			rowNum++;
			row = sheet.getRow(rowNum);
		}
		return values;

	}
}
