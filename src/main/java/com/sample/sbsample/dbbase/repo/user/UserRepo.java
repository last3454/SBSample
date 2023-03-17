package com.sample.sbsample.dbbase.repo.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.sbsample.dbbase.entity.user.UserEntity;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, String>, UserRepoCustom {
	public UserEntity findByLoginIdIgnoreCase(String loginId);
}
