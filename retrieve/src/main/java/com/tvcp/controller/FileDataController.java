package com.tvcp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tvcp.common.DataConstant;
import com.tvcp.common.ResultData;
import com.tvcp.domain.ActorDomain;
import com.tvcp.domain.AreaDomain;
import com.tvcp.domain.PropDomain;
import com.tvcp.service.ActorService;
import com.tvcp.service.AreaService;
import com.tvcp.service.PropService;
import com.tvcp.util.ExcelUtil;

@Controller
@RequestMapping("excelData")
public class FileDataController {

	private static final Logger log = LoggerFactory.getLogger(FileDataController.class);
	
	@Autowired
	private ActorService actorService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private PropService propService;

	@RequestMapping(value="importExcel",method=RequestMethod.POST)
	@ResponseBody
	public ResultData<Object> importExcel(HttpServletRequest request) {
		log.info("开始数据导入");
		ResultData<Object> result = new ResultData<Object>();
		ExcelUtil eu = new ExcelUtil();
		Workbook workbook = eu.readExcel(DataConstant.EXCELURL);
		List<ActorDomain> actorList = new ArrayList<>();
		List<AreaDomain> areaList = new ArrayList<>();
		List<PropDomain> propList = new ArrayList<>();
		try {
			if (workbook != null) {
				Sheet sheet1 = workbook.getSheet("演员v2");
				int rowNum = sheet1.getLastRowNum();
				for (int i = 3; i < rowNum; i++) {
					Row row = sheet1.getRow(i);
					if (row != null) {
						ActorDomain actorDomain = new ActorDomain();
						actorDomain.setName(eu.getCellFormatValue(row.getCell(0)));
						String sex =eu.getCellFormatValue(row.getCell(1));
						if (sex.equals("男")) {
							actorDomain.setSex(1);
						} else {
							actorDomain.setSex(0);
						}
						actorDomain.setBirthday(eu.getCellFormatValue(row.getCell(2)));
						actorDomain.setAge(eu.getCellFormatValue(row.getCell(3)));
						actorDomain.setCountry(eu.getCellFormatValue(row.getCell(4)));
						actorDomain.setHobby(eu.getCellFormatValue(row.getCell(5)));
						actorDomain.setDetail(eu.getCellFormatValue(row.getCell(6)));
						actorList.add(actorDomain);
					} else {
						break;
					}
				}
				Sheet sheet2 = workbook.getSheet("场地v2");
				int rowNum2 = sheet1.getLastRowNum();
				for (int i = 3; i < rowNum2; i++) {
					Row row = sheet2.getRow(i);
					if (row != null) {
						AreaDomain areaDomain = new AreaDomain();
						areaDomain.setName(eu.getCellFormatValue(row.getCell(0)));
						areaDomain.setNature(eu.getCellFormatValue(row.getCell(1)));
						areaDomain.setYears(eu.getCellFormatValue(row.getCell(2)));
						areaDomain.setType(eu.getCellFormatValue(row.getCell(3)));
						areaDomain.setFeature(eu.getCellFormatValue(row.getCell(4)));
						areaDomain.setStage(eu.getCellFormatValue(row.getCell(5)));
						areaDomain.setAddress(eu.getCellFormatValue(row.getCell(6)));
						areaDomain.setInformation(eu.getCellFormatValue(row.getCell(7)));
						areaDomain.setExpense(eu.getCellFormatValue(row.getCell(8)));
						areaDomain.setUrl(eu.getCellFormatValue(row.getCell(9)));
						areaList.add(areaDomain);
					} else {
						break;
					}
				}
				Sheet sheet3 = workbook.getSheet("道具v2");
				int rowNum3 = sheet3.getLastRowNum();
				for (int i = 3; i < rowNum3; i++) {
					Row row = sheet3.getRow(i);
					if (row != null) {
						PropDomain propDomain = new PropDomain();
						propDomain.setSupplierName(eu.getCellFormatValue(row.getCell(0)));
						propDomain.setLabel(eu.getCellFormatValue(row.getCell(1)));
						propDomain.setUrl(eu.getCellFormatValue(row.getCell(2)));
						propList.add(propDomain);
					} else {
						break;
					}
				}
				actorService.importExcel(actorList);
				areaService.importExcel(areaList);
				propService.importExcel(propList);
				log.info("数据导入结束");
				return result;
			}else {
				log.info("excel文件为空");
				result.setStatus(DataConstant.DEAL_FAIL);
				result.setMsg(DataConstant.DEAL_FAIL_EMPTY_MODEL);
				return result;
			}
			
		} catch (Exception e) {
			log.error("逻辑错误：",e);
			result.setStatus(DataConstant.DEAL_FAIL);
			result.setMsg(DataConstant.DEAL_FAIL_LOGIC);
			return result;
		}
	}

}
