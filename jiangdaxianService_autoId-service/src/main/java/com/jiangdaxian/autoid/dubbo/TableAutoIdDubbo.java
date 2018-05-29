package com.jiangdaxian.autoid.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangdaxian.autoid.model.TableAutoIdModel;
import com.jiangdaxian.autoid.service.TableAutoIdService;

@Service("tableAutoIdDubbo")
public class TableAutoIdDubbo implements com.jiangdaxian.autoid.api.TableAutoIdApi{

	@Autowired
	private TableAutoIdService tableAutoIdService;
	
	/**
	 * 根据项目名和表名，获取一批ID
	 */
	public TableAutoIdModel getProjectTableId(String projectName, String tableName) throws Exception {
		return tableAutoIdService.getProjectTableId(projectName, tableName);
	}

}
