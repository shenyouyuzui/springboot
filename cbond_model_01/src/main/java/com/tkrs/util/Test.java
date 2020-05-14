package com.tkrs.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.tkrs.domain.CbondListDomain;

public class Test {

	/*private MongoTemplate mongoTemplate;

	// 查询一条数据，精确匹配使用is，模糊匹配使用regex；
	public CbondListDomain getByCode(String code) {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(code));
		CbondListDomain one = mongoTemplate.findOne(query, CbondListDomain.class);
		return one;

	}

	// 查询多条数据,limit(a).skip(b),b表示跳过当前，从当前数字开始，a表示查询a条数据
	public List<CbondListDomain> getPageListByCode(int begin, int end, String code) {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(code));
		List<CbondListDomain> list = mongoTemplate.find(query.limit(end - begin).skip(begin), CbondListDomain.class);
		return list;
	}

	// 模糊查询
	public long getListCountByConInfo(List<String> codeList) {
		Query query = new Query();
		Pattern pattern = Pattern.compile("^.*" + codeList + ".*$", Pattern.CASE_INSENSITIVE);
		for (String string : codeList) {
			query.addCriteria(Criteria.where(string).regex(pattern));
		}
		return mongoTemplate.count(query, CbondListDomain.class);

	}

	// gte:大于等于；lte：小于等于;字段类型要和数据库中的数据类型保持一致,Direction是个枚举，代表升降序
	public List<CbondListDomain> getList(int end, int begin, String orderField, List<CbondListDomain> list,
			Direction direction) {
		Query query = new Query();
		for (CbondListDomain cbondListDomain : list) {
			query.addCriteria(Criteria.where("cbondListDomain.getIssueAmount()").gte(1.2));
		}

		return mongoTemplate.find(
				query.limit(end - begin).skip(begin).with(new Sort(new Sort.Order(direction, orderField))),
				CbondListDomain.class);

	}

	// 查询字段不存在的数据
	public List<CbondListDomain> getList(String key) {
		Query query = new Query();
		query.addCriteria(Criteria.where(key).not());
		return mongoTemplate.find(query, CbondListDomain.class);
	}

	// 查询数量
	public long getPageInfoCount(String condition) {
		Query query = new Query();
		query.addCriteria(Criteria.where(condition).is(""));
		return mongoTemplate.count(query, CbondListDomain.class);

	}

	// 更新一条数据的一个字段
	public UpdateResult updateCode(CbondListDomain cbondListDomain) {
		String code = cbondListDomain.getCode();
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(code));
		return mongoTemplate.updateFirst(query, Update.update("paymentType", ""), CbondListDomain.class);
	}

	// 更新一条数据的多个字段
	private void update(CbondListDomain cbondListDomain, int crawlResult) {
		List<String> fileds = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		fileds.add("code");
		fileds.add("interestType");
		fileds.add("par");
		values.add("030056");
		values.add(crawlResult);
		values.add(Calendar.getInstance().getTime());
		updateByConInfo(cbondListDomain, fileds, values);
	}

	private void updateByConInfo(CbondListDomain cbondListDomain, List<String> fileds, List<Object> values) {
		Update update = new Update();
		for (int i = 0; i < fileds.size(); i++) {
			String field = fileds.get(i);
			Object value = values.get(i);
			update.set(field, value);
		}
		mongoTemplate.updateFirst(new Query(Criteria.where("code").is(cbondListDomain.getCode())), update,
				CbondListDomain.class);
	}

	// 删除数据
	public void deleteObject(Class<CbondListDomain> clazz, String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		mongoTemplate.remove(query, clazz);
	}

	// 保存一条数据
	public void saveObject(Object object) {
		mongoTemplate.insert(object);
	}

	// 保存多条数据
	public void saveObjects(List<CbondListDomain> list) {
		for (CbondListDomain cbondListDomain : list) {
			mongoTemplate.insert(cbondListDomain);
		}
	}
*/
}
