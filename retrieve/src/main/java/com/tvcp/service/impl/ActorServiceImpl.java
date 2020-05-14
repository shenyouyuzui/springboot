package com.tvcp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvcp.controller.vo.ActorPageVo;
import com.tvcp.controller.vo.ActorVo;
import com.tvcp.controller.vo.AgeRangeVo;
import com.tvcp.domain.ActorDomain;
import com.tvcp.mapper.ActorMapper;
import com.tvcp.service.ActorService;

@Service("actorService")
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorMapper actorMapper;

	@Override
	public void importExcel(List<ActorDomain> actorList) {

		actorMapper.importExcel(actorList);
	}

	@Override
	public List<ActorDomain> searchActor(ActorPageVo actorPageVo) {
		if (actorPageVo.getSex()!=null && actorPageVo.getSex() == 2) {
			actorPageVo.setSex(null);
		}
		String coninfo =null;
		List<AgeRangeVo> ageList = actorPageVo.getAgeList();
		StringBuffer sb = new StringBuffer();
		if(actorPageVo.getHobbyList()!=null) {
			List<String> hobbyList = actorPageVo.getHobbyList();
			for (int i = 0; i < hobbyList.size(); i++) {
				if(i!=hobbyList.size()-1) {
					sb.append(hobbyList.get(i)).append("|");
				}else {
					sb.append(hobbyList.get(i));
				}
			}
			
		}
		if(!StringUtils.isEmpty(sb.toString())) {
			coninfo=sb.toString();
		}
		return actorMapper.searchActor(actorPageVo.getAgeList(),coninfo, actorPageVo.getCountryList(),
				actorPageVo.getSex(),actorPageVo.getPageNum(),actorPageVo.getPageSize());
	}

	@Override
	public ActorDomain queryDetail(Integer id) {
		return actorMapper.queryDetail(id);
	}

	@Override
	public Integer queryByConditionCount(ActorPageVo actorPageVo) {
		if (actorPageVo.getSex()!= null && actorPageVo.getSex() == 2) {
			actorPageVo.setSex(null);
		}
		String coninfo =null;
		StringBuffer sb = new StringBuffer();
		if(actorPageVo.getHobbyList()!=null) {
			List<String> hobbyList = actorPageVo.getHobbyList();
			for (int i = 0; i < hobbyList.size(); i++) {
				if(i!=hobbyList.size()-1) {
					sb.append(hobbyList.get(i)).append("|");
				}else {
					sb.append(hobbyList.get(i));
				}
			}
			
		}
		if(!StringUtils.isEmpty(sb.toString())) {
			coninfo=sb.toString();
		}
		return actorMapper.queryByConditionCount(actorPageVo.getAgeList(), coninfo, actorPageVo.getCountryList(),
				actorPageVo.getSex());
	}

	@Override
	public List<String> initCountry() {
		
		return actorMapper.initCountry();
	}

	@Override
	public List<String> initHobby() {
		return actorMapper.initHobby();
	}

	
}
