package com.jiangdaxian.autoid.api;

import com.jiangdaxian.autoid.model.TableAutoIdModel;

/**
 * 底层获取一批ID
 * @author dell
 *
 */
public interface TableAutoIdApi {
	/**
	 * 根据项目名和表名，获取一批ID
	 * @param projectName
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public TableAutoIdModel getProjectTableId(String projectName, String tableName) throws Exception;
}
