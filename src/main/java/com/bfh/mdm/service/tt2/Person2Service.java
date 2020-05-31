package com.bfh.mdm.service.tt2;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfh.mdm.entity.tt2.Person2Entity;

public interface Person2Service extends IService<Person2Entity> {

	Person2Entity getById(Integer id);

}
