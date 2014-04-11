package com.iwork.common.utils.page;

/**
 * @author Administrator
 * 
 * @description 数据库方言接口
 * 
 */

public interface Dialect {

	public static enum Type {
		
		MYSQL {
			public String getValue() {
				return "mysql";
			}
		},
		MSSQL {
			public String getValue() {
				return "sqlserver";
			}
		},
		ORACLE {
			public String getValue() {
				return "oracle";
			}
		}
	}

	/**
	 * @descrption 获取分页SQL
	 * 
	 * @param sql
	 *            原始查询SQL
	 * @param offset
	 *            开始记录索引（从零开始）
	 * @param limit
	 *            每页记录大小
	 * @return 返回数据库相关的分页SQL语句
	 */
	public abstract String getPageSql(String sql, int offset, int limit);

}
