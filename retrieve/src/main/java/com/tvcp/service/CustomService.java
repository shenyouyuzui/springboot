package com.tvcp.service;

import java.util.List;

import com.tvcp.controller.vo.CustomPageVo;
import com.tvcp.domain.CustomDomain;

public interface CustomService {

	List<String> initLabel();

	List<String> initName();

	List<String> initAge();

	List<String> initType();

	List<String> initChannel();

	List<CustomDomain> searchCustom(CustomPageVo customPageVo);

	Integer queryByConditionCount(CustomPageVo customPageVo);

	CustomDomain queryDetail(Integer customId);

}
