package com.sample.sbsample.dbbase.repo.user;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.sbsample.dbbase.entity.user.UserEntity;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, String> {

	public UserEntity findByLoginIdIgnoreCase(String loginId);
}
