package com.tkrs.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tkrs.common.DataConstant;
import com.tkrs.common.Page;
import com.tkrs.common.PageResultVO;
import com.tkrs.common.ResultData;
import com.tkrs.controller.vo.CBondRatingVo;
import com.tkrs.controller.vo.CbondBaseInfoVo;
import com.tkrs.controller.vo.CbondInfoVo;
import com.tkrs.controller.vo.CbondPageVo;
import com.tkrs.controller.vo.CbondRiskVo;
import com.tkrs.domain.CbondListDomain;
import com.tkrs.domain.CbondRateDomain;
import com.tkrs.service.CbondService;
import com.tkrs.util.MyCollectionUtil;
import com.tkrs.util.OrderList;
import com.tkrs.util.SortList;

@Controller
@RequestMapping("/cbonds/")
public class CbondController {

	private static final Logger logger = LoggerFactory.getLogger(CbondController.class);

	@Autowired
	private CbondService cbondService;

	@PostMapping(value = "queryCbondList")
	@ResponseBody
	private ResultData<PageResultVO<List<CbondBaseInfoVo>>> queryCbondList(@RequestBody CbondPageVo cbondPageVo,
			HttpServletRequest request) {
		// 返回结果对象
		ResultData<PageResultVO<List<CbondBaseInfoVo>>> result = new ResultData<>();
		// 参数合规性校验
		if (null == cbondPageVo) {
			result.setMsg(DataConstant.DEAL_FAIL_EMPTY);
			result.setStatus(DataConstant.DEAL_FAIL);
			return result;
		}
		if (null == cbondPageVo.getOrderBy() || null == cbondPageVo.getCompany() || null == cbondPageVo.getRateFormer()
				|| null == cbondPageVo.getOrder()) {
			result.setMsg(DataConstant.DEAL_FAIL_EMPTY);
			result.setStatus(DataConstant.DEAL_FAIL);
			return result;
		}
		if (null == cbondPageVo.getPageNum()) {
			cbondPageVo.setPageNum(DataConstant.NUMBER_ONE);
		}
		if (null == cbondPageVo.getPageSize()) {
			cbondPageVo.setPageSize(DataConstant.NUMBER_TWTH);
		}
		String rateFormer = cbondPageVo.getRateFormer();
		String rateFormer1 = rateFormer+"+";
		String rateFormer2 = rateFormer+"-";
		// 查询出所有债权的code
		List<CbondRateDomain> list = cbondService.queryAllCode(cbondPageVo,rateFormer1,rateFormer2);
		//根据code查询评级
		for (int i = 0; i < list.size(); i++) {
			CbondRateDomain cbondRateDomain = list.get(i);
			String rf = cbondService.queryRateFormerByCode(cbondRateDomain.getCode());
			cbondRateDomain.setRateFormer(rf);
		}
		if(list.size()==0) {
			result.setMsg("选择的该类型暂无数据");
			result.setStatus("0");
			return result;
		}
		List<CbondBaseInfoVo> targestList = new LinkedList();
		for (int i = 0; i < list.size(); i++) {
			CbondBaseInfoVo cbondBaseInfoVo = new CbondBaseInfoVo();
			// 根据code查询债权名称
			String name = cbondService.queryNameByCode(list.get(i).getCode());
			// 根据code查询返回风险值
			StringBuilder kmvKey = new StringBuilder().append(DataConstant.DYNAMIC_ASSESSMENT)
					.append(DataConstant.SYMBOL_BAR_CHE).append(list.get(i).getCode());
			StringBuilder mfKey = new StringBuilder().append(DataConstant.MULTIPLE_FACTORS)
					.append(DataConstant.SYMBOL_BAR_CHE).append(list.get(i).getCode());
			// 获取风险值
			String kmv = cbondService.getValue(kmvKey.toString());
			String mf = cbondService.getValue(mfKey.toString());
			cbondBaseInfoVo.setKmvModelRisk(kmv);
			cbondBaseInfoVo.setMfModeRisk(mf);
			cbondBaseInfoVo.setRateFormer(list.get(i).getRateFormer());
			cbondBaseInfoVo.setSecName(name);
			targestList.add(cbondBaseInfoVo);
		}
		//对集合进行排序
		OrderList<CbondBaseInfoVo> sortList = new OrderList<>();
		String orderBy = cbondPageVo.getOrderBy();
		CbondBaseInfoVo cbondBaseInfoVo = new CbondBaseInfoVo();
		if(orderBy.equals(DataConstant.DYNAMIC_ASSESSMENT)){
			sortList.sort(targestList,"getKmvModelRisk" , cbondPageVo.getOrder());
		}else if(orderBy.equals(DataConstant.MULTIPLE_FACTORS)){
			sortList.sort(targestList,"getMfModeRisk" , cbondPageVo.getOrder());
		}
		// 总记录数
		int count = targestList.size();
		// 总页数
		Integer res = count % cbondPageVo.getPageSize();
		Integer ret = count / cbondPageVo.getPageSize();
		Integer totalPage = res == 0 ? ret : (ret + 1);
		// 将集合分成totalPage个集合
		List<List<CbondBaseInfoVo>> resultList = MyCollectionUtil.sliceListByBatchSize(targestList, cbondPageVo.getPageSize());
		// 获取当前页的数据
		List<CbondBaseInfoVo> targetList = resultList.get(cbondPageVo.getPageNum()-1);
		Page page = new Page();
		page.setCurrentPage(cbondPageVo.getPageNum());
		page.setTotalPage(totalPage);
		page.setTotalResult(count);
		// 设置返回结果
		PageResultVO<List<CbondBaseInfoVo>> pageResultVo = new PageResultVO<>();
		pageResultVo.setList(targetList);
		pageResultVo.setPage(page);
		result.setData(pageResultVo);
		return result;
	}

	/**
	 * 搜索框模糊查詢
	 * 
	 * @param cbondRiskVo
	 * @param request
	 * @return
	 */
	@PostMapping(value = "queryCbondByName")
	@ResponseBody
	private ResultData<CbondBaseInfoVo> queryCbondByName(@RequestBody CbondRiskVo cbondRiskVo,
			HttpServletRequest request) {
		// 返回结果对象
		ResultData<CbondBaseInfoVo> result = new ResultData<>();
		// 参数合规性校验
		if (null == cbondRiskVo) {
			result.setMsg(DataConstant.DEAL_FAIL_EMPTY);
			result.setStatus(DataConstant.DEAL_FAIL);
			return result;
		}
		if (null == cbondRiskVo.getSecName()) {
			result.setMsg(DataConstant.DEAL_FAIL_NAME);
			result.setStatus(DataConstant.DEAL_FAIL);
			return result;
		}
		CbondBaseInfoVo cbondBaseInfoVo = cbondService.queryCbondByName(cbondRiskVo);
		if (null == cbondBaseInfoVo) {
			result.setMsg(DataConstant.DEAL_FAIL_LOGIC);
			result.setStatus(DataConstant.DEAL_FAIL);
		}
		result.setData(cbondBaseInfoVo);
		return result;
	}

	@PostMapping(value = "queryCbondInfo")
	@ResponseBody
	private ResultData<CbondInfoVo> queryCbondInfo(@RequestBody CbondBaseInfoVo cbondBaseInfoVo,
			HttpServletRequest request) throws ParseException {
		// 返回结果对象
		ResultData<CbondInfoVo> result = new ResultData<>();
		// 参数合规性校验
		if (null == cbondBaseInfoVo) {
			result.setMsg(DataConstant.DEAL_FAIL_EMPTY);
			result.setStatus(DataConstant.DEAL_FAIL);
			return result;
		}
		if (null == cbondBaseInfoVo.getSecName()) {
			result.setMsg(DataConstant.DEAL_FAIL_NAME);
			result.setStatus(DataConstant.DEAL_FAIL);
			return result;
		}
		if (null == cbondBaseInfoVo.getKmvModelRisk() || null == cbondBaseInfoVo.getMfModeRisk()) {
			result.setMsg(DataConstant.DEAL_FAIL_EMPTY);
			result.setStatus(DataConstant.DEAL_FAIL);
			return result;
		}
		CbondInfoVo cbondInfoVo = cbondService.queryCbondInfo(cbondBaseInfoVo);
		if (null == cbondInfoVo) {
			result.setMsg(DataConstant.DEAL_FAIL_LOGIC);
			result.setStatus(DataConstant.DEAL_FAIL);
		}
		result.setData(cbondInfoVo);
		return result;
	}

	@PostMapping(value = "queryAllCbondName")
	@ResponseBody
	private ResultData<List<String>> queryAllCbondName(HttpServletRequest request) throws ParseException {
		// 返回结果对象
		ResultData<List<String>> result = new ResultData<>();
		// 查詢所有債權名稱
		List<String> list = cbondService.queryAllCbondName();
		if (list.size()==0) {
			result.setMsg(DataConstant.DEAL_FAIL_LOGIC);
			result.setStatus(DataConstant.DEAL_FAIL);
		}
		result.setData(list);
		return result;
	}

}
