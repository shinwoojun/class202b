package com.ohhoonim.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ohhoonim.common.service.IdGenService;
import com.ohhoonim.common.util.Utils;
import com.ohhoonim.dao.IdGenDao;

@Service("idGenService")
public class IdGenServiceImpl implements IdGenService {

	@Resource(name="idGenDao")
	IdGenDao idGenDao;
	
	@Override
	public String getNextId(String tableName) {
		StringBuffer sb = new StringBuffer();
		String nextId = idGenDao.getNextId(tableName);
		idGenDao.updateNextId(tableName);
		
		if (tableName != null) {
			String code = "";
			String[] codeSet = tableName.split("_");
			if (codeSet.length == 2) {
				code = codeSet[1];
				sb = sb.append(code);
				sb = sb.append(Utils.fixedNumber(nextId, 5));
			}
		}
		return sb.toString();
	}
	
//	북마크 그룹 아이디 생성
	@Override
	public String bookGroupIdGen(String memId) {
		String groupNo = idGenDao.getGroupNo(memId);
		idGenDao.updateGroupNo(memId);
		
		String groupId = Utils.fixedNumber(groupNo, 4);
		
		return groupId;
	}
}

