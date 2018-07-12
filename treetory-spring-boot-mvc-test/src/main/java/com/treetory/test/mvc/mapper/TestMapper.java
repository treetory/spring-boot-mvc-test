package com.treetory.test.mvc.mapper;

import java.util.List;
import java.util.Map;

import com.treetory.test.common.annotation.BaseDs;

@BaseDs
public interface TestMapper {

	public List<Map<String, Object>> selectTestMulti();

	public Map<String, Object> selectTestSingle(Map<String, Object> param);
	
	public int insertTest(Map<String, Object> param);
	
	public int updateTest(Map<String, Object> param);
	
	public int deleteTest(Map<String, Object> param);
	
}
