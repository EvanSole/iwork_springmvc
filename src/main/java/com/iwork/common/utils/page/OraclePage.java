package com.iwork.common.utils.page;

/**
 * 
 * @author Administrator
 * 
 * @description Oracle数据库实现
 * 
 */
public class OraclePage implements Dialect {

	public String getPageSql(String sql, int offset, int limit) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pageSql = new StringBuffer(sql.length() + 100);
		pageSql.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pageSql.append(sql);
		pageSql.append(" ) row_ ) where rownum_ > " + offset + " and rownum_ <= " + (offset + limit));
		if (isForUpdate) {
			pageSql.append(" for update");
		}
		return pageSql.toString();
	}

}


