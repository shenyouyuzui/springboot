package com.tkrs.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.tkrs.controller.vo.CBondRatingVo;
import com.tkrs.controller.vo.CbondBaseInfoVo;
import com.tkrs.controller.vo.CbondInfoVo;
import com.tkrs.controller.vo.CbondPageVo;
import com.tkrs.controller.vo.CbondRiskVo;
import com.tkrs.domain.CbondRateDomain;

public interface CbondService {

	public List<CbondBaseInfoVo> queryDataByCondition(CbondPageVo cbondsVo) ;

	public int queryByConditionCount(CbondPageVo cbondsVo);

	public CbondBaseInfoVo queryCbondByName(CbondRiskVo cbondRiskVo);

	public CbondInfoVo queryCbondInfo(CbondBaseInfoVo cbondBaseInfoVo) throws ParseException;

	public List<String> queryAllCbondName();

	public List<CbondRateDomain> queryAllCode(CbondPageVo cbondPageVo, String rateFormer1, String rateFormer2);

	public String queryNameByCode(String code);

	public String getValue(String key);

	public String queryRateFormerByCode(String code);
 
}
