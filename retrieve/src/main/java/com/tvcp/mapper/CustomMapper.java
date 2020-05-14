package com.tvcp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tvcp.domain.CustomDomain;

@Mapper
public interface CustomMapper {

	List<String> initLabel();

	List<String> initName();

	List<String> initAge();

	List<String> initType();

	List<String> initChannel();

	List<CustomDomain> searchCustom(List<String> ageList, List<String> channelList, List<String> labelList,
			List<String> nameList, Integer pageNum, Integer pageSize, List<String> typeList, Integer sex);

	Integer queryByConditionCount(List<String> ageList, List<String> channelList, List<String> labelList,
			List<String> nameList, List<String> typeList, Integer sex);

	CustomDomain queryDetail(Integer customId);

}
