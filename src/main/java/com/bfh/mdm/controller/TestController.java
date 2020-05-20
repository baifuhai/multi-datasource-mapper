package com.bfh.mdm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bfh.mdm.entity.tt.UserEntity;
import com.bfh.mdm.entity.tt2.User2Entity;
import com.bfh.mdm.mapper.tt.UserMapper;
import com.bfh.mdm.mapper.tt2.User2Mapper;
import com.bfh.mdm.repository.tt.UserRepository;
import com.bfh.mdm.repository.tt2.User2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	User2Repository user2Repository;

	@Autowired
	UserMapper userMapper;

	@Autowired
	User2Mapper user2Mapper;

	@PostMapping("test01")
	public String test01() {
		{
			System.out.println(userRepository.findAll());
		}
		{
			System.out.println(user2Repository.findAll());
		}
		{
			Page<UserEntity> iPage = new Page<>(1, 2);
			userMapper.selectPage(iPage, null);
			System.out.println(iPage.getTotal());
			System.out.println(iPage.getRecords());
		}
		{
			Page<User2Entity> iPage = new Page<>(1, 2);
			user2Mapper.selectPage(iPage, null);
			System.out.println(iPage.getTotal());
			System.out.println(iPage.getRecords());
		}
		return "ok";
	}

}

