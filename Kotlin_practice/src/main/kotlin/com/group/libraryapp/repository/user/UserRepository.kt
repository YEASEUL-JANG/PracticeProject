package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.loanhistory.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User,Long>, UserRepositoryCustom {

    fun findByName(name: String) : User?
//    @Query("SELECT distinct u from User u left JOIN FETCH u.userLoanHistories")
//    fun findAllWithHistories(): List<User>
}