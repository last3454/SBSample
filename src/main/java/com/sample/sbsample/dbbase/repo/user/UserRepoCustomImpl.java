package com.sample.sbsample.dbbase.repo.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.sbsample.dbbase.entity.user.UserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepoCustomImpl implements UserRepoCustom {

	private final JPAQueryFactory query;

	@Override
	public UserEntity findByLoginIdIgnoreCase(String loginId) {
		return null;
	}
}