package com.tkrs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tkrs.controller.vo.CBondRatingVo;
import com.tkrs.controller.vo.CbondPageVo;
import com.tkrs.domain.CbondRateDomain;

@Mapper
public interface CbondRateMapper {

	List<CbondRateDomain> queryAll(CbondRateDomain cbondRateDomain);

	String queryRateFormerByCode(String code);

	List<CbondRateDomain> queryAllCode(@Param("cbondPageVo")CbondPageVo cbondPageVo, @Param("rateFormer1")String rateFormer1, @Param("rateFormer2")String rateFormer2);

}
