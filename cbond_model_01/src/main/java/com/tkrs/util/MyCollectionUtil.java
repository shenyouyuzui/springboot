package com.tkrs.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class MyCollectionUtil {

	/**
	 * 把一个list分成若干个batchSize大小的小list，最后一个list的size可能小于batchSize如果不能整除的时候
	 * 
	 * @param list
	 * @param batchSize
	 * @return
	 */
	public static <T> List<List<T>> sliceListByBatchSize(final List<T> list, int batchSize) {
		List<List<T>> result = new ArrayList<List<T>>();
		if (CollectionUtils.isEmpty(list) || 0 >= batchSize) {
			return result;
		}

		final int n = (list.size() + batchSize - 1) / batchSize;
		for (int i = 0; i < n; i++) {
			result.add(list.subList(i * batchSize, Math.min((1 + i) * batchSize, list.size())));
		}

		return result;
	}
}
