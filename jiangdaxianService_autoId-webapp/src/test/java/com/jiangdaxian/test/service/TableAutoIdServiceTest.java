package com.jiangdaxian.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jiangdaxian.autoid.model.TableAutoIdModel;
import com.jiangdaxian.autoid.service.TableAutoIdService;
import com.jiangdaxian.jdxtest.dao.TableAutoIdDao;
import com.jiangdaxian.jdxtest.entity.TableAutoIdEntity;
import com.jiangdaxian.redis.RedisLock;
import com.jiangdaxian.test.BaseTestCase;

public class TableAutoIdServiceTest extends BaseTestCase {
	@Autowired
	private TableAutoIdDao tableAutoIdDao;
	@Autowired
	private TableAutoIdService tableAutoIdService;
	@Autowired
	private RedisLock redisLock;
	@Test
	public void testInsert() {
		TableAutoIdEntity tableAutoIdEntity = new TableAutoIdEntity();
		tableAutoIdEntity.setProjectName("jdxProjectName");
		tableAutoIdEntity.setTableName("jdxTableName");
		tableAutoIdEntity.setAddCount(100L);
		tableAutoIdEntity.setNowAutoId(1L);
		tableAutoIdDao.insert(tableAutoIdEntity);
	}
	
	@Test
	public void testGetProjectTableId() throws Exception {
		String PROJECT_TABLE_REDIS_LOCK = "PROJECT_TABLE_REDIS_LOCK:%s:%s";
		String projectName = "jdxProjectName";
		String tableName = "jdxTableName";
		String projectTableIdLock = String.format(PROJECT_TABLE_REDIS_LOCK, projectName, tableName);
		redisLock.unlock(projectTableIdLock);
		try {
			TableAutoIdModel tableAutoIdModel = tableAutoIdService.getProjectTableId(projectName, tableName);
			tableAutoIdModel = tableAutoIdService.getProjectTableId(projectName, tableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testGetProjectTableIdThread() throws Exception {
		for(int i=0;i<10;i++) {
			new Thread(new Runnable() {
				public void run() {
					TableAutoIdModel tableAutoIdModel;
					try {
						tableAutoIdModel = tableAutoIdService.getProjectTableId("jdxProjectName", "jdxTableName");
						System.out.println("result:"+JSONObject.toJSONString(tableAutoIdModel));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		Thread.sleep(10*1000);
	}
}
