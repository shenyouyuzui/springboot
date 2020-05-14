package com.tvcp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvcp.controller.vo.AreaPageVo;
import com.tvcp.controller.vo.AreaVo;
import com.tvcp.domain.AreaDomain;
import com.tvcp.mapper.AreaMapper;
import com.tvcp.service.AreaService;

@Service("areaService")
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaMapper areaMapper;

	@Override
	public void importExcel(List<AreaDomain> areaList) {
		// TODO Auto-generated method stub
		areaMapper.importExcel(areaList);

	}

	@Override
	public List<AreaDomain> searchArea(AreaPageVo areaPageVo) {
		// TODO Auto-generated method stub
		return areaMapper.searchArea(areaPageVo.getFeatureList(), areaPageVo.getNatureList(), areaPageVo.getStageList(),
				areaPageVo.getTypeList(), areaPageVo.getYearsList(), areaPageVo.getPageNum(), areaPageVo.getPageSize());
	}

	@Override
	public Integer queryByConditionCount(AreaPageVo areaPageVo) {
		// TODO Auto-generated method stub
		return areaMapper.queryByConditionCount(areaPageVo.getFeatureList(), areaPageVo.getNatureList(),
				areaPageVo.getStageList(), areaPageVo.getTypeList(), areaPageVo.getYearsList());
	}

	@Override
	public AreaDomain queryDetail(Integer id) {
		// TODO Auto-generated method stub
		return areaMapper.queryDetail(id);
	}

	@Override
	public List<String> initNature() {
		// TODO Auto-generated method stub
		return areaMapper.initNature();
	}

	@Override
	public List<String> initYears() {
		// TODO Auto-generated method stub
		return areaMapper.initYears();
	}

	@Override
	public List<String> initType() {
		// TODO Auto-generated method stub
		return areaMapper.initType();
	}

	@Override
	public List<String> initFeature() {
		// TODO Auto-generated method stub
		return areaMapper.initFeature();
	}

}
