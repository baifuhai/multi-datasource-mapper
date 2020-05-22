package com.bfh.mdm.service.tt.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bfh.mdm.entity.tt.UserEntity;
import com.bfh.mdm.entity.tt2.User2Entity;
import com.bfh.mdm.mapper.tt.UserMapper;
import com.bfh.mdm.mapper.tt2.User2Mapper;
import com.bfh.mdm.repository.tt.UserRepository;
import com.bfh.mdm.repository.tt2.User2Repository;
import com.bfh.mdm.service.tt.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	User2Repository user2Repository;

	@Autowired
	UserMapper userMapper;

	@Autowired
	User2Mapper user2Mapper;

	@Override
	public void test01() {
		{
			// test01 has TransactionInterceptor(DataSourceTransactionManager)
			// findAll has TransactionInterceptor(JpaTransactionManager)
			// only work on different datasource
			System.out.println(userRepository.findAll());//throw Exception
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
	}

	@Override
	public void test02() throws Exception {
		{
			UserEntity userEntity = new UserEntity();
			userEntity.setUserName("a");
			userRepository.save(userEntity);//throw Exception
		}
		{
			User2Entity user2Entity = new User2Entity();
			user2Entity.setUserName("b");
			user2Repository.save(user2Entity);//not rollback
		}
		{
			UserEntity userEntity = new UserEntity();
			userEntity.setUserName("a");
			userMapper.insert(userEntity);//rollback
		}
		{
			User2Entity user2Entity = new User2Entity();
			user2Entity.setUserName("b");
			user2Mapper.insert(user2Entity);//not rollback
		}
		throw new Exception("Exception");
	}

}
