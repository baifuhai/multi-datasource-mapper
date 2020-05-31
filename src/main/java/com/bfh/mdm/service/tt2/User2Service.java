package com.bfh.mdm.service.tt2;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfh.mdm.entity.tt2.User2Entity;

public interface User2Service extends IService<User2Entity> {

	User2Entity getById(Integer id);

	User2Entity getById2(Integer id);

	User2Entity getById3(Integer id);

}
