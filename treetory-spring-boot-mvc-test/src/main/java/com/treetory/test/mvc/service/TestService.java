package com.treetory.test.mvc.service;

import java.util.List;
import java.util.Map;

public interface TestService {

	public List<Map<String, Object>> selectTestMulti();
	
	public Map<String, Object> selectTestSingle(Map<String, Object> param);
	
	public int insertTest(Map<String, Object> param);
	
	public int updateTest(Map<String, Object> param);
	
	public int deleteTest(Map<String, Object> param);
	
}
