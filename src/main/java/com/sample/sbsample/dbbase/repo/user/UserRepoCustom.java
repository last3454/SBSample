package com.sample.sbsample.dbbase.repo.user;

import com.sample.sbsample.dbbase.entity.user.UserEntity;

public interface UserRepoCustom {

	public UserEntity findByLoginIdIgnoreCase(String loginId);
}
