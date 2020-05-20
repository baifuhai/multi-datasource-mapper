package com.bfh.mdm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfh.mdm.entity.tt2.User2Entity;
import com.bfh.mdm.mapper.tt2.User2Mapper;
import com.bfh.mdm.service.User2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class User2ServiceImpl extends ServiceImpl<User2Mapper, User2Entity> implements User2Service {
}
