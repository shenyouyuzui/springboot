package com.tkrs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tkrs.controller.vo.CbondBaseInfoVo;
import com.tkrs.controller.vo.CbondRiskVo;
import com.tkrs.domain.CbondYtmDomain;

@Mapper
public interface CbondYtmMapper {

	String queryCodeByName(CbondRiskVo cbondRiskVo);

	CbondYtmDomain queryInfoByName(CbondBaseInfoVo cbondBaseInfoVo);

	List<String> queryAllCbondName();

	String queryNameByCode(String code);
}
