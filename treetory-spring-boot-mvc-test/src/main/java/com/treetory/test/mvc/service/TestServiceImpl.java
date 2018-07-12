package com.treetory.test.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treetory.test.mvc.mapper.TestMapper;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapper tMapper;

	@Override
	public List<Map<String, Object>> selectTestMulti() {
		return tMapper.selectTestMulti();
	}

	@Override
	public Map<String, Object> selectTestSingle(Map<String, Object> param) {
		return tMapper.selectTestSingle(param);
	}

	@Override
	public int insertTest(Map<String, Object> param) {
		return tMapper.insertTest(param);
	}

	@Override
	public int updateTest(Map<String, Object> param) {
		return tMapper.updateTest(param);
	}

	@Override
	public int deleteTest(Map<String, Object> param) {
		return tMapper.deleteTest(param);
	}
	
}
