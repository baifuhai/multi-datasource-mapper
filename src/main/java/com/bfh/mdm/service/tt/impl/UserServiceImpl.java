package com.bfh.mdm.service.tt.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfh.mdm.entity.tt.UserEntity;
import com.bfh.mdm.mapper.tt.UserMapper;
import com.bfh.mdm.service.tt.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public UserEntity getById(Integer id) {
		return baseMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public UserEntity getById2(Integer id) {
		return baseMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public UserEntity getById3(Integer id) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName("a");
		baseMapper.insert(userEntity);
		return baseMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public UserEntity getById4(Integer id) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName("a");
		baseMapper.insert(userEntity);
		return baseMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	@Override
	public UserEntity getById5(Integer id) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName("a");
		baseMapper.insert(userEntity);
		return baseMapper.selectById(id);
	}

}
