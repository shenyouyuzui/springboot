package com.tvcp.service;

import java.util.List;

import com.tvcp.controller.vo.ActorPageVo;
import com.tvcp.controller.vo.ActorVo;
import com.tvcp.domain.ActorDomain;

public interface ActorService {

	void importExcel(List<ActorDomain> actorList);

	List<ActorDomain> searchActor(ActorPageVo actorPageVo);

	ActorDomain queryDetail(Integer id);

	Integer queryByConditionCount(ActorPageVo actorPageVo);

	List<String> initCountry();

	List<String> initHobby();


}
