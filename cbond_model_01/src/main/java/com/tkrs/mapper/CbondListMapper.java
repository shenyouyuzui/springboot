package com.tkrs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.tkrs.domain.CbondListDomain;

@Mapper
public interface CbondListMapper {

	CbondListDomain queryInfoByCode(String code);
	

}
