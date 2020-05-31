package com.bfh.mdm.controller;

import com.bfh.mdm.service.test.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test2")
public class Test2Controller {

	@Autowired
	Test2Service test2Service;

	@PostMapping("test01")
	public String test01() {
		test2Service.test01();
		return "ok";
	}

	@PostMapping("test02")
	public String test02() {
		test2Service.test02();
		return "ok";
	}

	@PostMapping("test03")
	public String test03() {
		test2Service.test03();
		return "ok";
	}

	@PostMapping("test04")
	public String test04() {
		test2Service.test04();
		return "ok";
	}

	@PostMapping("test05")
	public String test05() {
		test2Service.test05();
		return "ok";
	}

	@PostMapping("test06")
	public String test06() {
		test2Service.test06();
		return "ok";
	}

	@PostMapping("test07")
	public String test07() {
		test2Service.test07();
		return "ok";
	}

	@PostMapping("test08")
	public String test08() {
		test2Service.test08();
		return "ok";
	}

	@PostMapping("test11")
	public String test11() {
		test2Service.test11();
		return "ok";
	}

	@PostMapping("test12")
	public String test12() {
		test2Service.test12();
		return "ok";
	}

	@PostMapping("test13")
	public String test13() {
		test2Service.test13();
		return "ok";
	}

	@PostMapping("test14")
	public String test14() {
		test2Service.test14();
		return "ok";
	}

	@PostMapping("test21")
	public String test21() {
		test2Service.test21();
		return "ok";
	}

}

