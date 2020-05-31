package com.bfh.mdm.controller;

import com.bfh.mdm.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	TestService testService;

	@PostMapping("test01")
	public String test01() {
		testService.test01();
		return "ok";
	}

	@PostMapping("test02")
	public String test02() throws Exception {
		testService.test02();
		return "ok";
	}

}

