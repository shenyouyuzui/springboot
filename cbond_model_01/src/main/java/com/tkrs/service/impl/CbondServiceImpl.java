package com.tkrs.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tkrs.common.DataConstant;
import com.tkrs.controller.vo.CBondRatingVo;
import com.tkrs.controller.vo.CbondBaseInfoVo;
import com.tkrs.controller.vo.CbondInfoVo;
import com.tkrs.controller.vo.CbondPageVo;
import com.tkrs.controller.vo.CbondRiskVo;
import com.tkrs.domain.CbondListDomain;
import com.tkrs.domain.CbondRateDomain;
import com.tkrs.domain.CbondYtmDomain;
import com.tkrs.domain.ModeHistoryDomain;
import com.tkrs.mapper.CbondYtmMapper;
import com.tkrs.mapper.CbondListMapper;
import com.tkrs.mapper.CbondRateMapper;
import com.tkrs.service.CbondService;
import com.tkrs.util.DoubleUtil;
import com.tkrs.util.RedisUtil;
import com.tkrs.util.SortList;

@Service("cbondService")
public class CbondServiceImpl implements CbondService {

	@Autowired
	private CbondListMapper cbondListDao;
	@Autowired
	private CbondYtmMapper cbondYtmDao;
	@Autowired
	private CbondRateMapper cbondRateDao;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public List<CbondBaseInfoVo> queryDataByCondition(CbondPageVo cbondsVo) {
		return null;
	}

	@Override
	public int queryByConditionCount(CbondPageVo cbondsVo) {
		// 根据评级、评级公司从monggo得到总记录数
		CbondRateDomain cbondRateDomain = new CbondRateDomain();
		cbondRateDomain.setCompany(cbondsVo.getCompany());
		cbondRateDomain.setRateFormer(cbondsVo.getRateFormer());
		List<CbondRateDomain> list = cbondRateDao.queryAll(cbondRateDomain);
		int count = list.size();
		return count;
	}

	@Override
	public CbondBaseInfoVo queryCbondByName(CbondRiskVo cbondRiskVo) {
		CbondBaseInfoVo cbondBaseInfoVo = new CbondBaseInfoVo();
		// 1.根据债券名称从表中获取code
		String code = cbondYtmDao.queryCodeByName(cbondRiskVo);
		// 2.根據code獲取評級
		String rateFormer = cbondRateDao.queryRateFormerByCode(code);
		// 3.根据code从redis获取动态风险值和多因子风险值
		StringBuilder kfmKey = new StringBuilder().append(DataConstant.DYNAMIC_ASSESSMENT)
				.append(DataConstant.SYMBOL_BAR_CHE).append(code);
		StringBuilder mfKey = new StringBuilder().append(DataConstant.MULTIPLE_FACTORS)
				.append(DataConstant.SYMBOL_BAR_CHE).append(code);
		String kmvModelRisk = getRiskValue(kfmKey.toString());
		String mfModeRisk = getRiskValue(mfKey.toString());
		cbondBaseInfoVo.setSecName(cbondRiskVo.getSecName());
		cbondBaseInfoVo.setRateFormer(rateFormer);
		cbondBaseInfoVo.setKmvModelRisk(kmvModelRisk);
		cbondBaseInfoVo.setMfModeRisk(mfModeRisk);
		return cbondBaseInfoVo;
	}

	private String getRiskValue(String keyName) {
		Double riskValue = 0.0;
		if (redisUtil.exists(keyName)) {
			String object = (String) redisUtil.get(keyName);
			JSONObject tempJson = JSON.parseObject(object);
			Map<String, Object> map = JSONObject.toJavaObject(tempJson, Map.class);
			List<String> list = new LinkedList<>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				list.add(key);
			}
			String maxKey = Collections.max(list);
			JSONObject targetJson = (JSONObject) tempJson.get(maxKey);
			if (keyName.contains(DataConstant.DYNAMIC_ASSESSMENT)) {
				riskValue = targetJson.getDoubleValue(DataConstant.KMV_VALUE);
			} else if (keyName.contains(DataConstant.MULTIPLE_FACTORS)) {
				riskValue = targetJson.getDoubleValue(DataConstant.MF_VALUE);
			}
		}
		DoubleUtil doubleUtil = new DoubleUtil();
		BigDecimal bg=new BigDecimal(riskValue+"");
		String str = bg.toPlainString();
		String target = doubleUtil.SubFourPoint(str);
		//riskValue  = Double.parseDouble(target);
		return target;
	}

	@Override
	public CbondInfoVo queryCbondInfo(CbondBaseInfoVo cbondBaseInfoVo) throws ParseException {
		// 1.根据上市公司名称查询code和pq_close
		CbondYtmDomain cbondYtmDomain = cbondYtmDao.queryInfoByName(cbondBaseInfoVo);
		// 2.根據code去查詢基本信息
		CbondListDomain cbondListDomain = cbondListDao.queryInfoByCode(cbondYtmDomain.getCode());
		// 3.根據code去獲取理念風險值
		StringBuilder kfmKey = new StringBuilder().append(DataConstant.DYNAMIC_ASSESSMENT)
				.append(DataConstant.SYMBOL_BAR_CHE).append(cbondYtmDomain.getCode());
		StringBuilder mfKey = new StringBuilder().append(DataConstant.MULTIPLE_FACTORS)
				.append(DataConstant.SYMBOL_BAR_CHE).append(cbondYtmDomain.getCode());
		List<ModeHistoryDomain> kmvList = getEachYear(kfmKey.toString());
		List<ModeHistoryDomain> mfList = getEachYear(mfKey.toString());
		// 对集合进行排序
		SortList<ModeHistoryDomain> sortList = new SortList<>();
		if(kmvList!=null){
			sortList.sort(kmvList, "getDate", "asc");
		}
		if(mfList!=null){
			sortList.sort(mfList, "getDate", "asc");
		}
		CbondInfoVo cbondInfoVo = new CbondInfoVo();
		cbondInfoVo.setSecName(cbondBaseInfoVo.getSecName());
		cbondInfoVo.setKmvModelRisk(Double.parseDouble(cbondBaseInfoVo.getKmvModelRisk()));
		cbondInfoVo.setMfModeRisk(Double.parseDouble(cbondBaseInfoVo.getMfModeRisk()));
		cbondInfoVo.setCode(cbondYtmDomain.getCode());
		cbondInfoVo.setPcClose(cbondYtmDomain.getPqClose());
		cbondInfoVo.setCouponRate(cbondListDomain.getCouponRate());
		cbondInfoVo.setInterestFrequency(cbondListDomain.getInterestFrequency());
		cbondInfoVo.setInterestType(cbondListDomain.getInterestType());
		cbondInfoVo.setIpoDate(cbondListDomain.getIpoDate());
		cbondInfoVo.setIssueAmout(cbondListDomain.getIssueAmount());
		cbondInfoVo.setPar(cbondListDomain.getPar());
		cbondInfoVo.setPaymentType(cbondListDomain.getPaymentType());
		cbondInfoVo.setQuotedCompany(cbondListDomain.getCnName());
		cbondInfoVo.setRateFormer(cbondBaseInfoVo.getRateFormer());
		cbondInfoVo.setRateIssueFormer(cbondListDomain.getRateIssueFormer());
		cbondInfoVo.setKmvModelHistoryList(kmvList);
		cbondInfoVo.setMfModelHistoryList(mfList);
		cbondInfoVo.setMaturituDate(cbondListDomain.getMaturityDate());
		return cbondInfoVo;
	}

	private List<ModeHistoryDomain> getEachYear(String keyName) throws ParseException {
		if (redisUtil.exists(keyName)) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String object = (String) redisUtil.get(keyName);
			JSONObject tempJson = JSON.parseObject(object);
			Map<String, Object> map = JSONObject.toJavaObject(tempJson, Map.class);
			List<ModeHistoryDomain> list = new LinkedList<>();
			DoubleUtil doubleUtil = new DoubleUtil();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				double riskValue = 0.0;
				ModeHistoryDomain modeHistoryDomain = new ModeHistoryDomain();
				String key = entry.getKey();
				JSONObject targetJson = (JSONObject) tempJson.get(key);
				if (keyName.contains(DataConstant.DYNAMIC_ASSESSMENT)) {
					riskValue = targetJson.getDoubleValue(DataConstant.KMV_VALUE);
				} else if (keyName.contains(DataConstant.MULTIPLE_FACTORS)) {
					riskValue = targetJson.getDoubleValue(DataConstant.MF_VALUE);
				}
				BigDecimal bg=new BigDecimal(riskValue+"");
				String str = bg.toPlainString();
				String subFourPoint = doubleUtil.SubFourPoint(str);
				riskValue = Double.parseDouble(subFourPoint);
				Date date = df.parse(key);
				String strDate = df.format(date);
				modeHistoryDomain.setDate(strDate);
				modeHistoryDomain.setRiskValue(riskValue);
				list.add(modeHistoryDomain);
			}
			return list;
		}

		return null;
	}

	@Override
	public List<String> queryAllCbondName() {

		return cbondYtmDao.queryAllCbondName();
	}

	@Override
	public List<CbondRateDomain> queryAllCode(CbondPageVo cbondPageVo, String rateFormer1, String rateFormer2) {

		return cbondRateDao.queryAllCode(cbondPageVo,rateFormer1, rateFormer2);
	}

	@Override
	public String queryNameByCode(String code) {

		return cbondYtmDao.queryNameByCode(code);
	}

	@Override
	public String getValue(String key) {

		return getRiskValue(key);
	}

	@Override
	public String queryRateFormerByCode(String code) {
		
		return cbondRateDao.queryRateFormerByCode(code);
	}

}