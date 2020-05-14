package com.tvcp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.tvcp.controller.vo.ActorDetailVo;
import com.tvcp.controller.vo.ActorPageVo;
import com.tvcp.controller.vo.ActorVo;
import com.tvcp.controller.vo.AreaVo;
import com.tvcp.domain.ActorDomain;
import com.tvcp.service.ActorService;

@Controller
@RequestMapping("actor")
public class ActorController {

	private static final Logger logger = LoggerFactory.getLogger(ActorController.class);

	@Autowired
	private ActorService actorService;

	@RequestMapping(value = "initData", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<Map<String, List>> initData(HttpServletRequest request) {
		ResultData<Map<String, List>> result = new ResultData<>();
		List<String> cList = actorService.initCountry();
		List<String> hobbyList = actorService.initHobby();
		if(CollectionUtils.isEmpty(hobbyList)){
			result.setMsg("暂无数据");
			return result;
		}
		// 去重
		List<String> hList = new ArrayList<>();
		for (int i = 0; i < hobbyList.size(); i++) {
			String hobby = hobbyList.get(i);
			if (hobby.contains("，")) {
				String[] arrHobby = hobby.split("，");
				for (int j = 0; j < arrHobby.length; j++) {
					if (!StringUtils.isEmpty(arrHobby[j]) && !hList.contains(arrHobby[j])) {
						hList.add(arrHobby[j]);
					}
				}
			} else if (!hList.contains(hobby)) {
				hList.add(hobby);
			}
		}
		Map<String, List> map = new HashMap<>();
		map.put("country", cList);
		map.put("hobby", hList);
		result.setData(map);
		return result;
	}

	@RequestMapping(value = "searchActor", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<PageResultVO<List<ActorVo>>> searchActor(HttpServletRequest request,
			@RequestBody ActorPageVo actorPageVo) {
		ResultData<PageResultVO<List<ActorVo>>> result = new ResultData<>();
		if (null == actorPageVo.getPageNum()) {
			actorPageVo.setPageNum(DataConstant.NUMBER_ONE);
		}
		if (null == actorPageVo.getPageSize()) {
			actorPageVo.setPageSize(DataConstant.NUMBER_TWTH);
		}
		try {
			Page page = new Page();
			page.setCurrentPage(actorPageVo.getPageNum());
			page.setShowCount(actorPageVo.getPageSize());
			actorPageVo.setPageNum((actorPageVo.getPageNum() - 1) * actorPageVo.getPageSize());
			List<ActorDomain> list = actorService.searchActor(actorPageVo);
			if (list == null) {
				PageResultVO<List<ActorVo>> pageResultVo = new PageResultVO<>();
				pageResultVo.setList(new ArrayList<ActorVo>());
				page.setTotalPage(DataConstant.NUMBER_ZERO);
				page.setTotalResult(DataConstant.NUMBER_ZERO);
				pageResultVo.setPage(page);
				result.setData(pageResultVo);
				return result;
			}
			String path = DataConstant.FILEURL + DataConstant.SYMBOL_DIVIDE + DataConstant.ACTOR;
			File file = new File(path);
			File[] files = file.listFiles();
			List<ActorVo> actorList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				ActorVo av = new ActorVo();
				String name = list.get(i).getName();
				av.setId(list.get(i).getId());
				av.setName(name);
				for (int a = 0; a < files.length; a++) {
					File f = files[a];
					if (name.equals(f.getName())) {
						File[] actorFiles = f.listFiles();
						for (int j = 0; j < actorFiles.length; j++) {
							File file2 = actorFiles[j];
							if (file2.getAbsolutePath().contains(DataConstant.MASTER_GRAPH)) {
								av.setImgPath(
										DataConstant.INVENT_FILEURL + DataConstant.ACTOR + DataConstant.SYMBOL_DIVIDE
												+ name + DataConstant.SYMBOL_DIVIDE + file2.getName());
							}
						}
					}
				}
				actorList.add(av);
			}
			Integer count = actorService.queryByConditionCount(actorPageVo);
			page.setTotalResult(count);
			Integer res = count % actorPageVo.getPageSize();
			Integer ret = count / actorPageVo.getPageSize();
			Integer totalPage = res == 0 ? ret : (ret + 1);
			page.setTotalPage(totalPage);
			// 设置返回结果
			PageResultVO<List<ActorVo>> pageResultVo = new PageResultVO<>();
			pageResultVo.setList(actorList);
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
	public ResultData<ActorDetailVo> queryDetail(HttpServletRequest request, @RequestBody ActorDomain actorDomain) {
		ResultData<ActorDetailVo> result = new ResultData<>();
		try {
			ActorDomain actor = actorService.queryDetail(actorDomain.getId());
			String path = DataConstant.FILEURL + DataConstant.SYMBOL_DIVIDE + DataConstant.ACTOR;
			File file = new File(path);
			File[] files = file.listFiles();
			ActorDetailVo av = new ActorDetailVo();
			List<String> imgList = new ArrayList<>();
			String name = actor.getName();
			av.setId(actor.getId());
			av.setName(name);
			av.setAge(actor.getAge());
			av.setBirthday(actor.getBirthday());
			av.setCountry(actor.getCountry());
			av.setSex(actor.getSex());
			av.setHobby(actor.getHobby());
			av.setDetail(actor.getDetail());
			for (int a = 0; a < files.length; a++) {
				File f = files[a];
				if (name.equals(f.getName())) {
					File[] actorFile = f.listFiles();
					for (int j = 0; j < actorFile.length; j++) {
						File file2 = actorFile[j];
						if (file2.getAbsolutePath().contains(DataConstant.INSET)) {
							imgList.add(DataConstant.INVENT_FILEURL + DataConstant.ACTOR + DataConstant.SYMBOL_DIVIDE
									+ name + DataConstant.SYMBOL_DIVIDE +file2.getName() );
						} else if (file2.getAbsolutePath().contains(DataConstant.VIDEO)) {
							av.setVideoUrl(DataConstant.INVENT_FILEURL + DataConstant.ACTOR + DataConstant.SYMBOL_DIVIDE
									+ name + DataConstant.SYMBOL_DIVIDE +file2.getName() );
						}
					}
				}
			}
			av.setImgList(imgList);
			result.setData(av);
			return result;
		} catch (Exception e) {
			logger.error(e.toString());
			result.setMsg(DataConstant.DEAL_FAIL_LOGIC);
			result.setStatus("1");
			return result;
		}

	}

}
