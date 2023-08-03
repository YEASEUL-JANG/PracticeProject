package com.group.libraryapp.domain.user.loanhistory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User,Long>, UserRepositoryCustom {

    fun findByName(name: String) : User?
//    @Query("SELECT distinct u from User u left JOIN FETCH u.userLoanHistories")
//    fun findAllWithHistories(): List<User>
}