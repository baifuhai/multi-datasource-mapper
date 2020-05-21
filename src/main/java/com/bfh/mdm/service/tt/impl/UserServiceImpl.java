package com.bfh.mdm.service.tt.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfh.mdm.entity.tt.UserEntity;
import com.bfh.mdm.mapper.tt.UserMapper;
import com.bfh.mdm.service.tt.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
