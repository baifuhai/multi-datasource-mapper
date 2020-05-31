package com.bfh.mdm.service.tt;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfh.mdm.entity.tt.UserEntity;

public interface UserService extends IService<UserEntity> {

	UserEntity getById(Integer id);

	UserEntity getById2(Integer id);

	UserEntity getById3(Integer id);

	UserEntity getById4(Integer id);

	UserEntity getById5(Integer id);

}
