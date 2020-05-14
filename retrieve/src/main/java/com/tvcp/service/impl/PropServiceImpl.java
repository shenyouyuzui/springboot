package com.tvcp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvcp.controller.vo.PropPageVo;
import com.tvcp.controller.vo.PropVo;
import com.tvcp.domain.PropDomain;
import com.tvcp.mapper.PropMapper;
import com.tvcp.service.PropService;

@Service("propService")
public class PropServiceImpl implements PropService {
	
	@Autowired
	private PropMapper propMapper;

	@Override
	public void importExcel(List<PropDomain> propList) {
		// TODO Auto-generated method stub
		propMapper.importExcel(propList);
	}

	@Override
	public List<PropDomain> searchProp(PropPageVo propPageVo) {
		// TODO Auto-generated method stub
		return propMapper.searchProp(propPageVo.getLabelList(),propPageVo.getPageNum(),propPageVo.getPageSize());
	}

	@Override
	public Integer queryByConditionCount(PropPageVo propPageVo) {
		// TODO Auto-generated method stub
		return propMapper.queryByConditionCount(propPageVo.getLabelList());
	}

	@Override
	public PropDomain queryDetail(Integer id) {
		
		return propMapper.queryDetail(id);
	}

	@Override
	public List<String> initLabel() {
		// TODO Auto-generated method stub
		return propMapper.initLabel();
	}

}
