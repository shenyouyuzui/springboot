package com.tvcp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvcp.controller.vo.CustomPageVo;
import com.tvcp.domain.CustomDomain;
import com.tvcp.mapper.CustomMapper;
import com.tvcp.service.CustomService;

@Service("customerService")
public class CustomServiceImpl implements CustomService {

	@Autowired
	private CustomMapper customerMapper;

	@Override
	public List<String> initLabel() {

		return customerMapper.initLabel();
	}

	@Override
	public List<String> initName() {
		// TODO Auto-generated method stub
		return customerMapper.initName();
	}

	@Override
	public List<String> initAge() {
		// TODO Auto-generated method stub
		return customerMapper.initAge();
	}

	@Override
	public List<String> initType() {
		// TODO Auto-generated method stub
		return customerMapper.initType();
	}

	@Override
	public List<String> initChannel() {
		// TODO Auto-generated method stub
		return customerMapper.initChannel();
	}

	@Override
	public List<CustomDomain> searchCustom(CustomPageVo customPageVo) {
		if (customPageVo.getSex() != null && customPageVo.getSex() == 2) {
			customPageVo.setSex(null);
		}
		List<String> nameList = customPageVo.getNameList();
		return customerMapper.searchCustom(customPageVo.getAgeList(), customPageVo.getChannelList(),
				customPageVo.getLabelList(), customPageVo.getNameList(), customPageVo.getPageNum(),
				customPageVo.getPageSize(), customPageVo.getTypeList(), customPageVo.getSex());
	}

	@Override
	public Integer queryByConditionCount(CustomPageVo customPageVo) {
		if (customPageVo.getSex() != null && customPageVo.getSex() == 2) {
			customPageVo.setSex(null);
		}
		return customerMapper.queryByConditionCount(customPageVo.getAgeList(), customPageVo.getChannelList(),
				customPageVo.getLabelList(), customPageVo.getNameList(), customPageVo.getTypeList(),
				customPageVo.getSex());
	}

	@Override
	public CustomDomain queryDetail(Integer customId) {
		// TODO Auto-generated method stub
		return customerMapper.queryDetail(customId);
	}

}
