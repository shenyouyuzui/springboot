package com.tvcp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tvcp.domain.AreaDomain;

@Mapper
public interface AreaMapper {

	void importExcel(List<AreaDomain> areaList);

	List<AreaDomain> searchArea(List<String> featureList, List<String> natureList, List<String> stageList,
			List<String> typeList, List<String> yearsList, Integer pageNum, Integer pageSize);

	Integer queryByConditionCount(List<String> featureList, List<String> natureList, List<String> stageList,
			List<String> typeList, List<String> yearsList);

	AreaDomain queryDetail(Integer id);

	List<String> initFeature();

	List<String> initType();

	List<String> initYears();

	List<String> initNature();

}
