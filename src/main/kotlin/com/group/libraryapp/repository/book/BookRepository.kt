package com.group.libraryapp.repository.book

import com.group.libraryapp.domain.book.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
    fun findByName(bookName: String) : Book?

}