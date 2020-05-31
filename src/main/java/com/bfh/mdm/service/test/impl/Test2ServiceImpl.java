package com.bfh.mdm.service.test.impl;

import com.bfh.mdm.mapper.tt.PersonMapper;
import com.bfh.mdm.mapper.tt.UserMapper;
import com.bfh.mdm.mapper.tt2.Person2Mapper;
import com.bfh.mdm.mapper.tt2.User2Mapper;
import com.bfh.mdm.service.test.Test2Service;
import com.bfh.mdm.service.tt.PersonService;
import com.bfh.mdm.service.tt.UserService;
import com.bfh.mdm.service.tt2.Person2Service;
import com.bfh.mdm.service.tt2.User2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * jpa没有一级缓存
 * 没有suspend，TransactionSynchronizationManager.getResourceMap()就不会改变
 * 只有DataSourceTransactionManager的doBegin里面用到了@Transactional的readOnly属性，JpaTransactionManager试了，没看源码
 * 只读用propagation = Propagation.REQUIRES_NEW, readOnly = true
 */
@Slf4j
@Service
public class Test2ServiceImpl implements Test2Service {

	@Autowired
	UserMapper userMapper;

	@Autowired
	PersonMapper personMapper;

	@Autowired
	UserService userService;

	@Autowired
	PersonService personService;

	@Autowired
	User2Mapper user2Mapper;

	@Autowired
	Person2Mapper person2Mapper;

	@Autowired
	User2Service user2Service;

	@Autowired
	Person2Service person2Service;

	/**
	 * 同一个sqlSession，同一个executor，缓存累加
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void test01() {
		userMapper.selectById(1);
		personMapper.selectById(1);
		userMapper.selectById(2);
	}

	/**
	 * 同一个sqlSession，同一个executor，缓存累加
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void test02() {
		userService.getById(1);
		personService.getById(1);
		userService.getById(2);
	}

	/**
	 * 同一个sqlSession，同一个executor，缓存累加
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void test03() {
		userMapper.selectById(1);
		personService.getById(1);
		userMapper.selectById(2);
	}

	/**
	 * 第1行和第3行是同一个sqlSession，同一个executor，缓存累加
	 * 第2行是同一个sqlSession，同一个executor
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void test04() {
		userMapper.selectById(1);
		userService.getById2(1);
		userMapper.selectById(2);
	}

	// =============================================================================

	/**
	 * 同一个sqlSession，同一个executor，缓存累加
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public void test05() {
		userMapper.selectById(1);
		personMapper.selectById(1);
		userMapper.selectById(2);
	}

	/**
	 * 每一行是同一个sqlSession，同一个executor，缓存不累加
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public void test06() {
		userService.getById(1);
		personService.getById(1);
		userService.getById(1);
	}

	/**
	 * 第1行和第3行是同一个sqlSession，同一个executor，缓存累加
	 * 第2行是同一个sqlSession，同一个executor
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public void test07() {
		userMapper.selectById(1);
		userService.getById(1);
		userMapper.selectById(2);
	}

	/**
	 * 同上
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public void test08() {
		userMapper.selectById(1);
		userService.getById2(1);
		userMapper.selectById(2);
	}

	// =============================================================================

	/**
	 * 第1行和第3行是同一个sqlSession，同一个executor，缓存累加
	 * 第2行和第4行是同一个sqlSession，同一个executor，缓存累加
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void test11() {
		userMapper.selectById(1);
		user2Mapper.selectById(1);
		userMapper.selectById(2);
		user2Mapper.selectById(2);
	}

	/**
	 * 第1行和第3行是同一个sqlSession，同一个executor，缓存累加
	 * 第2行是同一个sqlSession，同一个executor
	 * 第4行是同一个sqlSession，同一个executor
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void test12() {
		userService.getById(1);
		user2Service.getById(1);
		userService.getById(2);
		user2Service.getById(1);
	}

	/**
	 * 第1行和第3行是同一个sqlSession，同一个executor，缓存累加
	 * 第2行是同一个sqlSession，同一个executor
	 * 第4行是同一个sqlSession，同一个executor
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void test13() {
		userMapper.selectById(1);
		user2Service.getById(1);
		userMapper.selectById(2);
		user2Service.getById(1);
	}

	/**
	 * 第1行和第3行是同一个sqlSession，同一个executor，缓存累加
	 * 第2行和第4行是同一个sqlSession，同一个executor，缓存累加（因为里面有insert操作，所以缓存被清空了）
	 * 第5行报错：Error updating database.  Cause: java.sql.SQLException: Connection is read-only
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void test14() {
		userMapper.selectById(1);
		user2Service.getById2(1);
		userMapper.selectById(2);
		user2Service.getById2(2);
		user2Service.getById3(2);
	}

	// =============================================================================

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void test21() {
		userService.getById3(1);// ok
		userService.getById4(1);// ok
		userService.getById5(1);// throws Exception: Error updating database.  Cause: java.sql.SQLException: Connection is read-only
	}

}
