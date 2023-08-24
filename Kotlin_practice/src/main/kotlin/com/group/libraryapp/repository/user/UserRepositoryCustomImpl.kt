package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.loanhistory.QUser.user
import com.group.libraryapp.domain.user.loanhistory.User
import com.querydsl.jpa.impl.JPAQueryFactory

class UserRepositoryCustomImpl(
        private val queryFactory: JPAQueryFactory,
) : UserRepositoryCustom {
    override fun findAllWithHistories(): List<User> {
        return queryFactory.select(user).distinct()
                .from(user)
                .leftJoin(user.userLoanHistories)
                .fetchJoin().fetch()
    }
}