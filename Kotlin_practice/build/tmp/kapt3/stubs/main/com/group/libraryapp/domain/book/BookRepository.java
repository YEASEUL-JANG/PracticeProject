package com.group.libraryapp.domain.book;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0007H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\'\u00a8\u0006\u000b"}, d2 = {"Lcom/group/libraryapp/domain/book/BookRepository;", "Lorg/springframework/data/jpa/repository/JpaRepository;", "Lcom/group/libraryapp/domain/book/Book;", "", "Lcom/group/libraryapp/domain/user/loanhistory/UserRepositoryCustom;", "findByName", "bookName", "", "getStats", "", "Lcom/group/libraryapp/dto/book/response/BookStatResponse;", "library-app"})
public abstract interface BookRepository extends org.springframework.data.jpa.repository.JpaRepository<com.group.libraryapp.domain.book.Book, java.lang.Long>, com.group.libraryapp.domain.user.loanhistory.UserRepositoryCustom {
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.group.libraryapp.domain.book.Book findByName(@org.jetbrains.annotations.NotNull()
    java.lang.String bookName);
    
    @org.jetbrains.annotations.NotNull()
    @org.springframework.data.jpa.repository.Query(value = "select new com.group.libraryapp.dto.book.response.BookStatResponse(b.type, count(b.id)) from Book b GROUP BY b.type")
    public abstract java.util.List<com.group.libraryapp.dto.book.response.BookStatResponse> getStats();
}