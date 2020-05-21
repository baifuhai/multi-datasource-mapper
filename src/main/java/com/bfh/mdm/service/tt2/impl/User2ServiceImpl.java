package com.bfh.mdm.service.tt2.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfh.mdm.entity.tt2.User2Entity;
import com.bfh.mdm.mapper.tt2.User2Mapper;
import com.bfh.mdm.service.tt2.User2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class User2ServiceImpl extends ServiceImpl<User2Mapper, User2Entity> implements User2Service {
}
