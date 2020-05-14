package com.tkrs.util;

public class DoubleUtil {
	
	/**
	 * @描述:把计算结果根据小数点的位数截取合适的值。即如果小数点位数小于等于4则不做处理，如果大于四则取前四位
	 * @方法名: SubFourPoint
	 * @param number
	 * @return
	 * @return String
	 */
	public  String SubFourPoint(String number) {
		int aa = (int) Math.floor(Double.parseDouble(number));		//获取整数部分
		String bb = number.replaceAll("\\d+\\.", "");  //获取小数部分
		String dd = "";
		if(bb.length() > 4) {
			String cc = bb.substring(0, 4);				//截取前四位小数
			if("0000".equals(cc)) {
				dd = String.valueOf(aa);
			}else {
				dd = String.valueOf(aa)+"."+cc;
			}
		}else {
			dd = number;				//不处理小数位，直接转成String类型
		}
		return dd;
	}

}
