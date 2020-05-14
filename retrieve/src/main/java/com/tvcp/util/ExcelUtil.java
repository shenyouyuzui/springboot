package com.tvcp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	// 读取excel
	public Workbook readExcel(String filePath) {
		Workbook wb = null;
		if (filePath == null) {
			return null;
		}
		String extString = filePath.substring(filePath.lastIndexOf("."));
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			if (".xls".equals(extString)) {
				return wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(extString)) {
				return wb = new XSSFWorkbook(is);
			} else {
				return wb = null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	public String getCellFormatValue(Cell cell) {
		String cellValue = null;
		if (cell != null) {
			// 判断cell类型
			switch (cell.getCellTypeEnum()) {
			case NUMERIC:
				cellValue = String.valueOf(cell.getNumericCellValue());
				cellValue = cellValue.substring(0, cellValue.lastIndexOf('.'));
				break;
			case STRING:
				cellValue = String.valueOf(cell.getStringCellValue());
				break;
			case FORMULA: //公式
		        cellValue = String.valueOf(cell.getCellFormula());
		        break;
			case BLANK: //空值
		        cellValue = "";
		        break;
			case BOOLEAN: //Boolean
			    cellValue = String.valueOf(cell.getBooleanCellValue());
			    break;
			}
		}
		return cellValue;
	}
}
