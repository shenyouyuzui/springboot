package com.tvcp.service;

import java.util.List;

import com.tvcp.controller.vo.AreaPageVo;
import com.tvcp.controller.vo.AreaVo;
import com.tvcp.domain.AreaDomain;

public interface AreaService {

	void importExcel(List<AreaDomain> areaList);

	List<AreaDomain> searchArea(AreaPageVo areaPageVo);

	Integer queryByConditionCount(AreaPageVo areaPageVo);

	AreaDomain queryDetail(Integer id);

	List<String> initNature();

	List<String> initYears();

	List<String> initType();

	List<String> initFeature();


}
