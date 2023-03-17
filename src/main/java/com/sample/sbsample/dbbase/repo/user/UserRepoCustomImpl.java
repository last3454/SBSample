package com.sample.sbsample.dbbase.repo.user;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepoCustomImpl implements UserRepoCustom {

	private final JPAQueryFactory query;
}