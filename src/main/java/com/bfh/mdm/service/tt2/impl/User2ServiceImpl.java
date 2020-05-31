package com.bfh.mdm.service.tt2.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfh.mdm.entity.tt2.User2Entity;
import com.bfh.mdm.mapper.tt2.User2Mapper;
import com.bfh.mdm.service.tt2.User2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class User2ServiceImpl extends ServiceImpl<User2Mapper, User2Entity> implements User2Service {

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public User2Entity getById(Integer id) {
		return baseMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public User2Entity getById2(Integer id) {
		User2Entity user2Entity = new User2Entity();
		user2Entity.setUserName("a");
		baseMapper.insert(user2Entity);
		return baseMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public User2Entity getById3(Integer id) {
		User2Entity user2Entity = new User2Entity();
		user2Entity.setUserName("a");
		baseMapper.insert(user2Entity);
		return baseMapper.selectById(id);
	}

}
