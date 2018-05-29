package com.jiangdaxian.jdxtest.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jiangdaxian.jdxtest.entity.TableAutoIdEntity;

@Repository
public interface TableAutoIdDao {
	public TableAutoIdEntity selectByProjectAndTable(@Param("projectName")String projectName,@Param("tableName")String tableName);
	
	public long addNum(@Param("id")Long id);

	public void insert(@Param("tableAutoIdEntity")TableAutoIdEntity tableAutoIdEntity);
}
