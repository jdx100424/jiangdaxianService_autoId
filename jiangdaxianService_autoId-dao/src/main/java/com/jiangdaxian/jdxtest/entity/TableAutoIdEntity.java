package com.jiangdaxian.jdxtest.entity;

import java.io.Serializable;

public class TableAutoIdEntity implements Serializable {
	private Long id;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String projectName;
	public String tableName;
	public Long nowAutoId;// 目前可能最大的ID
	public Long addCount;// 每次拿多少个ID

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getNowAutoId() {
		return nowAutoId;
	}

	public void setNowAutoId(Long nowAutoId) {
		this.nowAutoId = nowAutoId;
	}

	public Long getAddCount() {
		return addCount;
	}

	public void setAddCount(Long addCount) {
		this.addCount = addCount;
	}

}
