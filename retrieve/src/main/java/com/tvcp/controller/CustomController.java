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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tvcp.common.DataConstant;
import com.tvcp.common.Page;
import com.tvcp.common.PageResultVO;
import com.tvcp.common.ResultData;
import com.tvcp.controller.vo.CustomDetailVo;
import com.tvcp.controller.vo.CustomPageVo;
import com.tvcp.controller.vo.CustomVo;
import com.tvcp.domain.CustomDomain;
import com.tvcp.service.CustomService;

@Controller
@RequestMapping("custom")
public class CustomController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomController.class);

	@Autowired
	private CustomService customerService;

	@RequestMapping(value = "initData", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<Map<String, List>> initData(HttpServletRequest request) {
		ResultData<Map<String, List>> result = new ResultData<>();
		List<String> labelList = customerService.initLabel();
		List<String> namelList = customerService.initName();
		List<String> ageList = customerService.initAge();
		List<String> typeList = customerService.initType();
		List<String> channelList = customerService.initChannel();
		List<String> sexList = new ArrayList<>();
		sexList.add("男");
		sexList.add("女");
		Map<String, List> map = new HashMap<>();
		map.put("name", namelList);
		map.put("sex", sexList);
		map.put("age", ageList);
		map.put("type", typeList);
		map.put("label", labelList);
		map.put("channel", channelList);
		result.setData(map);
		return result;
	}

	@RequestMapping(value = "searchCustom", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<PageResultVO<List<CustomVo>>> searchProp(HttpServletRequest request,
			@RequestBody CustomPageVo CustomPageVo) {
		ResultData<PageResultVO<List<CustomVo>>> result = new ResultData<>();
		if (null == CustomPageVo.getPageNum()) {
			CustomPageVo.setPageNum(DataConstant.NUMBER_ONE);
		}
		if (null == CustomPageVo.getPageSize()) {
			CustomPageVo.setPageSize(DataConstant.NUMBER_TWTH);
		}
		try {
			Page page = new Page();
			page.setCurrentPage(CustomPageVo.getPageNum());
			page.setShowCount(CustomPageVo.getPageSize());
			CustomPageVo.setPageNum((CustomPageVo.getPageNum() - 1) * CustomPageVo.getPageSize());
			List<CustomDomain> list = customerService.searchCustom(CustomPageVo);
			if (list == null) {
				PageResultVO<List<CustomVo>> pageResultVo = new PageResultVO<>();
				pageResultVo.setList(new ArrayList<CustomVo>());
				page.setTotalPage(DataConstant.NUMBER_ZERO);
				page.setTotalResult(DataConstant.NUMBER_ZERO);
				pageResultVo.setPage(page);
				result.setData(pageResultVo);
				return result;
			}
			String path = DataConstant.FILEURL + DataConstant.SYMBOL_DIVIDE + DataConstant.CUSTOM;
			File file = new File(path);
			File[] files = file.listFiles();
			List<CustomVo> customList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				CustomVo CustomVo = new CustomVo();
				CustomVo.setCustomId(list.get(i).getCustomId());
				String name = list.get(i).getName();
				CustomVo.setName(name);
				for (int a = 0; a < files.length; a++) {
					File f = files[a];
					if (name.equals(f.getName())) {
						File[] customFiles = f.listFiles();
						for (int j = 0; j < customFiles.length; j++) {
							File file2 = customFiles[j];
							if (file2.getAbsolutePath().contains(DataConstant.FRONT_VIEW)) {
								CustomVo.setImgPath(DataConstant.INVENT_FILEURL + DataConstant.CUSTOM + DataConstant.SYMBOL_DIVIDE
										+ name + DataConstant.SYMBOL_DIVIDE + file2.getName());
							}
						}
					}
				}
				customList.add(CustomVo);
			}
			Integer count = customerService.queryByConditionCount(CustomPageVo);
			page.setTotalResult(count);
			Integer res = count % CustomPageVo.getPageSize();
			Integer ret = count / CustomPageVo.getPageSize();
			Integer totalPage = res == 0 ? ret : (ret + 1);
			page.setTotalPage(totalPage);
			// 设置返回结果
			PageResultVO<List<CustomVo>> pageResultVo = new PageResultVO<>();
			pageResultVo.setList(customList);
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
	public ResultData<CustomDetailVo> queryDetail(HttpServletRequest request, @RequestBody CustomDomain customDomain) {
		ResultData<CustomDetailVo> result = new ResultData<>();
		try {
			CustomDomain custom = customerService.queryDetail(customDomain.getCustomId());
			String path = DataConstant.FILEURL + DataConstant.SYMBOL_DIVIDE + DataConstant.CUSTOM;
			File file = new File(path);
			File[] files = file.listFiles();
			CustomDetailVo customDetailVo = new CustomDetailVo();
			List<String> imgList = new ArrayList<>();
			String name = custom.getName();
			customDetailVo.setAge(custom.getAge());
			customDetailVo.setChannel(custom.getChannel());
			customDetailVo.setCustomId(custom.getCustomId());
			customDetailVo.setInfo(custom.getInfo());
			customDetailVo.setLabel(custom.getLabel());
			customDetailVo.setName(custom.getName());
			customDetailVo.setSex(custom.getSex());
			customDetailVo.setType(custom.getType());
			for (int a = 0; a < files.length; a++) {
				File f = files[a];
				if (name.equals(f.getName())) {
					File[] actorFile = f.listFiles();
					for (int j = 0; j < actorFile.length; j++) {
						File file2 = actorFile[j];
						if (file2.getAbsolutePath().contains(DataConstant.SIDE_VIEW) || file2.getAbsolutePath().contains(DataConstant.BACK_VIEW)) {
							imgList.add(DataConstant.INVENT_FILEURL + DataConstant.CUSTOM + DataConstant.SYMBOL_DIVIDE
									+ name + DataConstant.SYMBOL_DIVIDE +file2.getName() );
						} 
					}
				}
			}
			customDetailVo.setImgList(imgList);
			result.setData(customDetailVo);
			return result;
		} catch (Exception e) {
			logger.error(e.toString());
			result.setMsg(DataConstant.DEAL_FAIL_LOGIC);
			result.setStatus("1");
			return result;
		}

	}


}
