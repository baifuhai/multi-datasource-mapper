package com.bfh.mdm.service.tt2.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfh.mdm.entity.tt2.Person2Entity;
import com.bfh.mdm.mapper.tt2.Person2Mapper;
import com.bfh.mdm.service.tt2.Person2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class Person2ServiceImpl extends ServiceImpl<Person2Mapper, Person2Entity> implements Person2Service {

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Person2Entity getById(Integer id) {
		return baseMapper.selectById(id);
	}

}
