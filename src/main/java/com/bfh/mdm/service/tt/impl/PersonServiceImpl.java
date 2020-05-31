package com.bfh.mdm.service.tt.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfh.mdm.entity.tt.PersonEntity;
import com.bfh.mdm.mapper.tt.PersonMapper;
import com.bfh.mdm.service.tt.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, PersonEntity> implements PersonService {

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public PersonEntity getById(Integer id) {
		return baseMapper.selectById(id);
	}

}
