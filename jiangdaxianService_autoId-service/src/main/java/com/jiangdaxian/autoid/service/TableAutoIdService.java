package com.jiangdaxian.autoid.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangdaxian.autoid.model.TableAutoIdModel;
import com.jiangdaxian.jdxtest.dao.TableAutoIdDao;
import com.jiangdaxian.jdxtest.entity.TableAutoIdEntity;

import com.jiangdaxian.redis.RedisLock;

/**
 * 分布式统一获取一批量的ID
 * 
 * @author dell
 *
 */
@Service
public class TableAutoIdService {
	private static final Logger LOG = LoggerFactory.getLogger(TableAutoIdService.class);

	//全局项目和表的REDIS锁，参数1，项目名，参数2，表名
	private static final String PROJECT_TABLE_REDIS_LOCK = "PROJECT_TABLE_REDIS_LOCK:%s:%s";
	
	@Autowired
	private RedisLock redisLock;
	@Autowired
	private TableAutoIdDao tableAutoIdDao;

	public TableAutoIdModel getProjectTableId(String projectName, String tableName) throws Exception {
		if (StringUtils.isBlank(projectName) || StringUtils.isBlank(tableName)) {
			throw new Exception("projectName or tableName is null");
		}
		String projectTableIdLock = String.format(PROJECT_TABLE_REDIS_LOCK, projectName, tableName);
		try {
			redisLock.lockByIncr(projectTableIdLock);
			TableAutoIdEntity tableAutoIdEntity = tableAutoIdDao.selectByProjectAndTable(projectName, tableName);
			if (tableAutoIdEntity == null || tableAutoIdEntity.getId() == null) {
				throw new Exception("tableAutoIdEntity is null");
			}
			
			long addNum = tableAutoIdEntity.getAddCount();
			long startNum = tableAutoIdEntity.getNowAutoId();
			// add number
			long resultlong = tableAutoIdDao.addNum(tableAutoIdEntity.getId());
			if (resultlong <= 0L) {
				throw new Exception("tableAutoIdEntity update fail");
			}

			TableAutoIdModel tableAutoIdModel = new TableAutoIdModel();
			tableAutoIdModel.setId(tableAutoIdEntity.getId());
			tableAutoIdModel.setProjectName(projectName);
			tableAutoIdModel.setTableName(tableName);
			tableAutoIdModel.setStartId(startNum);
			tableAutoIdModel.setEndId(startNum+addNum-1);
			return tableAutoIdModel;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new Exception("autoid get fail");
		} finally {
			redisLock.unlock(projectTableIdLock);
		}
	}
}
