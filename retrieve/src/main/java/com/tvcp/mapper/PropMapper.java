package com.tvcp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.tvcp.domain.PropDomain;

@Mapper
public interface PropMapper {

	void importExcel(List<PropDomain> propList);

	List<PropDomain> searchProp(@Param("lableList")List<String> lableList, @Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize);

	Integer queryByConditionCount(List<String> list);

	PropDomain queryDetail(Integer id);

	List<String> initLabel();

}
