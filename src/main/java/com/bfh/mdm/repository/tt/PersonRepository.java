package com.bfh.mdm.repository.tt;

import com.bfh.mdm.entity.tt.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
}
