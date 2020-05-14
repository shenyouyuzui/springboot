package com.tkrs.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tkrs.controller.vo.CbondBaseInfoVo;

public class OrderList<T> {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sort(List<T> targetList, final String sortField, final String sortMode) {
		Collections.sort(targetList, new Comparator() {
			public int compare(Object obj1, Object obj2) {
				int retVal = 0;
				try {
					Method method1 = ((T) obj1).getClass().getMethod(sortField, null);
					Method method2 = ((T) obj2).getClass().getMethod(sortField, null);
					String d2Temp = (String) method2.invoke(((T) obj2), null);
					Double d2 = Double.parseDouble(d2Temp);
					String d1Temp = (String) method1.invoke(((T) obj1), null);
					Double d1 = Double.parseDouble(d1Temp);
					if (sortMode != null && "desc".equals(sortMode)) {
						retVal = d2.compareTo(d1);// 倒序
					} else {
						retVal = d1.compareTo(d2);// 正序
					}
				} catch (Exception e) {
					throw new RuntimeException();
				}
				return retVal;
			}
		});
	}
	
	
	public static void main(String[] args) {
		List<CbondBaseInfoVo> list = new ArrayList<CbondBaseInfoVo>();
		CbondBaseInfoVo cbondBaseInfoVo1 = new CbondBaseInfoVo();
		CbondBaseInfoVo cbondBaseInfoVo2 = new CbondBaseInfoVo();
		CbondBaseInfoVo cbondBaseInfoVo3 = new CbondBaseInfoVo();
		CbondBaseInfoVo cbondBaseInfoVo4 = new CbondBaseInfoVo();
		CbondBaseInfoVo cbondBaseInfoVo5 = new CbondBaseInfoVo();
		CbondBaseInfoVo cbondBaseInfoVo6 = new CbondBaseInfoVo();
		CbondBaseInfoVo cbondBaseInfoVo7 = new CbondBaseInfoVo();
		cbondBaseInfoVo1.setKmvModelRisk("30.12");
		cbondBaseInfoVo2.setKmvModelRisk("4.12");
		cbondBaseInfoVo3.setKmvModelRisk("25.12");
		cbondBaseInfoVo4.setKmvModelRisk("0.12");
		cbondBaseInfoVo5.setKmvModelRisk("0.0012");
		cbondBaseInfoVo6.setKmvModelRisk("9.12");
		cbondBaseInfoVo7.setKmvModelRisk("19.12");
		
		list.add(cbondBaseInfoVo1);
		list.add(cbondBaseInfoVo2);
		list.add(cbondBaseInfoVo3);
		list.add(cbondBaseInfoVo4);
		list.add(cbondBaseInfoVo5);
		list.add(cbondBaseInfoVo6);
		list.add(cbondBaseInfoVo7);
		
		OrderList<CbondBaseInfoVo> s = new OrderList<CbondBaseInfoVo>();
		s.sort(list, "getKmvModelRisk", "desc");
		for (int i = 0; i < list.size(); i++) {
			String risk = list.get(i).getKmvModelRisk();
			System.out.println(risk);
			
		}
	}

}
