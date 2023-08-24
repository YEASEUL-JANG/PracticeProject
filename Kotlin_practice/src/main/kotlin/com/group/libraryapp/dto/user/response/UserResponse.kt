package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.loanhistory.User

class UserResponse(
        val id: Long,
        val name: String,
        val age: Int?
) {
//    constructor(user: User): this(
//        id = user.id!!,
//        name = user.name,
//        age = user.age
//    )

    companion object{
        fun of(user: User): UserResponse {
            return UserResponse(
                    id = user.id!!,
                    name = user.name,
                    age = user.age
            )
        }
    }
}
