package com.tkrs.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tkrs.controller.vo.CbondBaseInfoVo;

public class SortList<T> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sort(List<T> targetList, final String sortField, final String sortMode) {
		Collections.sort(targetList, new Comparator() {
			public int compare(Object obj1, Object obj2) {
				int retVal = 0;
				try {
					Method method1 = ((T) obj1).getClass().getMethod(sortField, null);
					Method method2 = ((T) obj2).getClass().getMethod(sortField, null);
					if (sortMode != null && "desc".equals(sortMode)) {
						retVal = method2.invoke(((T) obj2), null).toString()
								.compareTo(method1.invoke(((T) obj1), null).toString()); // 倒序
					} else {
						retVal = method1.invoke(((T) obj1), null).toString()
								.compareTo(method2.invoke(((T) obj2), null).toString()); // 正序
					}
				} catch (Exception e) {
					throw new RuntimeException();
				}
				return retVal;
			}
		});
	}
		public static void main(String[] args) {
			String a1 = "AA";
			String b1 = "AA-";
			int i = a1.compareTo(b1);
			System.out.println(i);
			
		}
}
