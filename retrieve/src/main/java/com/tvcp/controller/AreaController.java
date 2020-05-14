package com.tvcp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tvcp.common.DataConstant;
import com.tvcp.common.Page;
import com.tvcp.common.PageResultVO;
import com.tvcp.common.ResultData;
import com.tvcp.controller.vo.AreaPageVo;
import com.tvcp.controller.vo.AreaVo;
import com.tvcp.domain.AreaDomain;
import com.tvcp.service.AreaService;

@Controller
@RequestMapping("area")
public class AreaController {

	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "initData", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<Map<String, List>> initData(HttpServletRequest request) {
		ResultData<Map<String, List>> result = new ResultData<>();
		List<String> natureList = areaService.initNature();
		List<String> yearsList = areaService.initYears();
		List<String> typeList = areaService.initType();
		List<String> featureList = areaService.initFeature();
		if (CollectionUtils.isEmpty(natureList) && CollectionUtils.isEmpty(natureList)
				&& CollectionUtils.isEmpty(typeList) && CollectionUtils.isEmpty(featureList)) {
			result.setMsg("暂无数据");
			return result;
		}
		// 去重
		Map<String, List> map = new HashMap<>();
		map.put("nature", natureList);
		map.put("years", yearsList);
		map.put("type", typeList);
		map.put("feature", featureList);
		result.setData(map);
		return result;
	}

	@RequestMapping(value = "searchArea", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<PageResultVO<List<AreaVo>>> searchArea(HttpServletRequest request,
			@RequestBody AreaPageVo areaPageVo) {
		ResultData<PageResultVO<List<AreaVo>>> result = new ResultData<>();
		if (null == areaPageVo.getPageNum()) {
			areaPageVo.setPageNum(DataConstant.NUMBER_ONE);
		}
		if (null == areaPageVo.getPageSize()) {
			areaPageVo.setPageSize(DataConstant.NUMBER_TWTH);
		}
		try {
			Page page = new Page();
			page.setCurrentPage(areaPageVo.getPageNum());
			page.setShowCount(areaPageVo.getPageSize());
			areaPageVo.setPageNum((areaPageVo.getPageNum() - 1) * areaPageVo.getPageSize());
			List<AreaDomain> list = areaService.searchArea(areaPageVo);
			if (list == null) {
				PageResultVO<List<AreaVo>> pageResultVo = new PageResultVO<>();
				pageResultVo.setList(new ArrayList<AreaVo>());
				page.setTotalPage(DataConstant.NUMBER_ZERO);
				page.setTotalResult(DataConstant.NUMBER_ZERO);
				pageResultVo.setPage(page);
				result.setData(pageResultVo);
				return result;
			}
			String path = DataConstant.FILEURL + DataConstant.SYMBOL_DIVIDE + DataConstant.AREA;
			File file = new File(path);
			File[] files = file.listFiles();
			List<AreaVo> areaList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				AreaVo av = new AreaVo();
				av.setId(list.get(i).getId());
				String name = list.get(i).getName();
				av.setName(name);
				av.setNature(list.get(i).getNature());
				for (int a = 0; a < files.length; a++) {
					File f = files[a];
					if (name.equals(f.getName())) {
						File[] areaFiles = f.listFiles();
						for (int j = 0; j < areaFiles.length; j++) {
							File file2 = areaFiles[j];
							if (file2.getAbsolutePath().contains(DataConstant.IMG)) {
								av.setImgPath(
										DataConstant.INVENT_FILEURL + DataConstant.AREA + DataConstant.SYMBOL_DIVIDE
												+ name + DataConstant.SYMBOL_DIVIDE + file2.getName());
							}
						}
					}
				}
				areaList.add(av);
			}
			Integer count = areaService.queryByConditionCount(areaPageVo);
			page.setTotalResult(count);
			Integer res = count % areaPageVo.getPageSize();
			Integer ret = count / areaPageVo.getPageSize();
			Integer totalPage = res == 0 ? ret : (ret + 1);
			page.setTotalPage(totalPage);
			// 设置返回结果
			PageResultVO<List<AreaVo>> pageResultVo = new PageResultVO<>();
			pageResultVo.setList(areaList);
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
	public ResultData<AreaDomain> queryDetail(HttpServletRequest request, @RequestBody AreaDomain areaDomain) {
		ResultData<AreaDomain> result = new ResultData<>();
		AreaDomain area = areaService.queryDetail(areaDomain.getId());
		result.setData(area);
		return result;
	}

}
