package com.group.libraryapp.domain.user.loanhistory

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User,Long> {

    fun findByName(name: String) : User?
}