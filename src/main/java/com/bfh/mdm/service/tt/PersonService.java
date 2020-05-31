package com.bfh.mdm.service.tt;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfh.mdm.entity.tt.PersonEntity;

public interface PersonService extends IService<PersonEntity> {

	PersonEntity getById(Integer id);

}
