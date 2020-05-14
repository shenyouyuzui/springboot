package com.tvcp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tvcp.controller.vo.AgeRangeVo;
import com.tvcp.domain.ActorDomain;

@Mapper
public interface ActorMapper {

	void importExcel(List<ActorDomain> actorList);

	ActorDomain queryDetail(Integer id);

	List<ActorDomain> searchActor(List<AgeRangeVo> agelist, String coninfo, List<String> countryList, Integer sex,
			Integer pageNum, Integer pageSize);

	Integer queryByConditionCount(List<AgeRangeVo> agelist, String coninfo, List<String> countryList, Integer sex);

	List<String> initCountry();

	List<String> initHobby();

}
