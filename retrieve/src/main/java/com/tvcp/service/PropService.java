package com.tvcp.service;

import java.util.List;

import com.tvcp.controller.vo.PropPageVo;
import com.tvcp.controller.vo.PropVo;
import com.tvcp.domain.PropDomain;

public interface PropService {

	void importExcel(List<PropDomain> propList);

	List<PropDomain> searchProp(PropPageVo propPageVo);

	Integer queryByConditionCount(PropPageVo propPageVo);

	PropDomain queryDetail(Integer id);

	List<String> initLabel();


}
