package com.group.libraryapp.domain.book

import java.lang.IllegalArgumentException
import javax.persistence.*

@Entity
class Book(
        val name: String,

        @Enumerated(EnumType.STRING)
        val type: BookType,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
) {

    init {
        if(name.isBlank()){
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }
    //정적 메소드를 통해 북 객체를 만드는 함수를 만들어 놓음
    companion object{
        fun fixture( // =" 디폴트 값 "
                name: String = "책 이름",
                type: BookType = BookType.COMPUTER,
                id: Long? = null,
        ): Book {
            return Book(
                    name=name,
                    type=type,
                    id=id,
            )
        }
    }
}