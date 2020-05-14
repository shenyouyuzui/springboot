package com.tvcp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.ReadingOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tvcp.common.DataConstant;
import com.tvcp.common.Page;
import com.tvcp.common.PageResultVO;
import com.tvcp.common.ResultData;
import com.tvcp.controller.vo.AreaVo;
import com.tvcp.controller.vo.PropDetailVo;
import com.tvcp.controller.vo.PropPageVo;
import com.tvcp.controller.vo.PropVo;
import com.tvcp.domain.AreaDomain;
import com.tvcp.domain.PropDomain;
import com.tvcp.service.PropService;

@Controller
@RequestMapping("prop")
public class PropController {

	private static final Logger logger = LoggerFactory.getLogger(PropController.class);

	@Autowired
	private PropService propService;

	@RequestMapping(value = "initData", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<Map<String, List>> initData(HttpServletRequest request) {
		ResultData<Map<String, List>> result = new ResultData<>();
		List<String> labelList = propService.initLabel();
		Map<String, List> map = new HashMap<>();
		map.put("label", labelList);
		result.setData(map);
		return result;
	}

	@RequestMapping(value = "searchProp", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<PageResultVO<List<PropVo>>> searchProp(HttpServletRequest request,
			@RequestBody PropPageVo propPageVo) {
		ResultData<PageResultVO<List<PropVo>>> result = new ResultData<>();
		if (null == propPageVo.getPageNum()) {
			propPageVo.setPageNum(DataConstant.NUMBER_ONE);
		}
		if (null == propPageVo.getPageSize()) {
			propPageVo.setPageSize(DataConstant.NUMBER_TWTH);
		}
		try {
			Page page = new Page();
			page.setCurrentPage(propPageVo.getPageNum());
			page.setShowCount(propPageVo.getPageSize());
			propPageVo.setPageNum((propPageVo.getPageNum() - 1) * propPageVo.getPageSize());
			List<PropDomain> list = propService.searchProp(propPageVo);
			if (list == null) {
				PageResultVO<List<PropVo>> pageResultVo = new PageResultVO<>();
				pageResultVo.setList(new ArrayList<PropVo>());
				page.setTotalPage(DataConstant.NUMBER_ZERO);
				page.setTotalResult(DataConstant.NUMBER_ZERO);
				pageResultVo.setPage(page);
				result.setData(pageResultVo);
				return result;
			}
			String path = DataConstant.FILEURL + DataConstant.SYMBOL_DIVIDE + DataConstant.PROP;
			File file = new File(path);
			File[] files = file.listFiles();
			List<PropVo> propList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				PropVo propVo = new PropVo();
				propVo.setId(list.get(i).getId());
				String name = list.get(i).getSupplierName();
				propVo.setSupplierName(name);
				for (int a = 0; a < files.length; a++) {
					File f = files[a];
					if (name.equals(f.getName())) {
						File[] propFiles = f.listFiles();
						for (int j = 0; j < propFiles.length; j++) {
							File file2 = propFiles[j];
							if (file2.getAbsolutePath().contains(DataConstant.MASTER_GRAPH)) {
								propVo.setImgPath(DataConstant.INVENT_FILEURL + DataConstant.PROP + DataConstant.SYMBOL_DIVIDE
										+ name + DataConstant.SYMBOL_DIVIDE + file2.getName());
							}
						}
					}
				}
				propList.add(propVo);
			}
			Integer count = propService.queryByConditionCount(propPageVo);
			page.setTotalResult(count);
			Integer res = count % propPageVo.getPageSize();
			Integer ret = count / propPageVo.getPageSize();
			Integer totalPage = res == 0 ? ret : (ret + 1);
			page.setTotalPage(totalPage);
			// 设置返回结果
			PageResultVO<List<PropVo>> pageResultVo = new PageResultVO<>();
			pageResultVo.setList(propList);
			pageResultVo.setPage(page);
			result.setData(pageResultVo);
			return result;
		} catch (Exception e) {
			logger.error(e.toString());
			result.setMsg(DataConstant.DEAL_FAIL_LOGIC);
			result.setStatus("1");
			return result;
		}

	}

	@RequestMapping(value = "queryDetail", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<PropDetailVo> queryDetail(HttpServletRequest request, @RequestBody PropDomain propDomain) {
		ResultData<PropDetailVo> result = new ResultData<>();
		PropDomain prop = propService.queryDetail(propDomain.getId());
		PropDetailVo pV = new PropDetailVo();
		if(prop!=null && prop.getUrl().contains("微信号")) {
			String path = DataConstant.FILEURL + DataConstant.SYMBOL_DIVIDE + DataConstant.PROP;
			File file = new File(path);
			File[] files = file.listFiles();
			String supplierName = prop.getSupplierName();
			List<String> imgList = new ArrayList<>();
			for (int a = 0; a < files.length; a++) {
				File f = files[a];
				if (supplierName.equals(f.getName())) {
					File[] actorFile = f.listFiles();
					for (int j = 0; j < actorFile.length; j++) {
						File file2 = actorFile[j];
						if (file2.getAbsolutePath().contains(DataConstant.INSET)) {
							imgList.add(DataConstant.INVENT_FILEURL + DataConstant.PROP + DataConstant.SYMBOL_DIVIDE
									+ supplierName + DataConstant.SYMBOL_DIVIDE +file2.getName() );
						}
					}
				}
			}
			pV.setImgList(imgList);
		}
		pV.setId(prop.getId());
		pV.setLabel(prop.getLabel());
		pV.setSupplierName(prop.getSupplierName());
		pV.setUrl(prop.getUrl());
		result.setData(pV);
		return result;
	}

}
