package com.group.libraryapp.domain.user.loanhistory

interface UserRepositoryCustom {
    fun findAllWithHistories():List<User>
}