package com.group.libraryapp.repository.user.loanhistory

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class UserLoanHistoryQuerydslRepository(
        private val queryfactory: JPAQueryFactory,
) {
    fun find(bookName: String, status: UserLoanStatus?=null): UserLoanHistory? {
        return queryfactory.select(userLoanHistory)
                .from(userLoanHistory)
                .where(userLoanHistory.bookName.eq(bookName),
                        status?.let { userLoanHistory.status.eq(status) }//status 값이 null이 아닌 경우에만 비교한다.
                )
                .limit(1) //모든 검색 결과에서 1개만 가져온다.
                .fetchOne()
    }
    fun count(status: UserLoanStatus): Long{
        return queryfactory.select(userLoanHistory.count())
                .from(userLoanHistory)
                .where(
                        userLoanHistory.status.eq(status)
                )
                .fetchOne() ?:0L
    }
}