package com.treetory.test.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.treetory.test.mvc.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService tService;
	
	@RequestMapping(
			value = "/test/select/multi",
			method = { RequestMethod.GET },
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, Object>> selectTestMulti(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return tService.selectTestMulti();
	}
	
}
