package com.bfh.mdm.repository.tt;

import com.bfh.mdm.entity.tt.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
