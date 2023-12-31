package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.repository.book.BookRepository
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.loanhistory.*
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.repository.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.repository.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.IllegalArgumentException

@SpringBootTest
class BookServiceTest @Autowired constructor(
        private val bookService: BookService,
        private val bookRepository: BookRepository,
        private val userRepository: UserRepository,
        private val userLoanHistoryRepository: UserLoanHistoryRepository
){
    @AfterEach
    fun clean(){
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록이 정상동작 한다.")
    fun saveBookTest(){
        //given
        val request = BookRequest("이상한 나라의 앨리스", BookType.COMPUTER)
        //when
        bookService.saveBook(request)
        //then
        val results = bookRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("이상한 나라의 앨리스")
        assertThat(results[0].type).isEqualTo(BookType.COMPUTER)
    }
    @Test
    @DisplayName("책 대출이 정상동작한다.")
    fun loanBookTest(){
        //given
        bookRepository.save(Book.fixture("이상한 나라의 앨리스"))
        val user = userRepository.save(User("yeaseul", 30))
        val request = BookLoanRequest("yeaseul","이상한 나라의 앨리스")
        //when
        bookService.loanBook(request)

        //then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookName).isEqualTo("이상한 나라의 앨리스")
        assertThat(results[0].user.id).isEqualTo(user.id)
        assertThat(results[0].status).isEqualTo(UserLoanStatus.LOANED)
    }

    @Test
    @DisplayName("이미 대출되어있는 책은 신규대출이 실패한다")
    fun loanfailTest() {
        //given
        bookRepository.save(Book.fixture("이상한 나라의 앨리스"))
        val user = userRepository.save(User("yeaseul", 30))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(user, "이상한 나라의 앨리스"))
        val request = BookLoanRequest("bumhyun", "이상한 나라의 앨리스")

        //when & then
        val message = assertThrows<IllegalArgumentException> {
            bookService.loanBook(request)
        }.message
        assertThat(message).isEqualTo("진작 대출되어 있는 책입니다")
    }
    @Test
    @DisplayName("책 반납이 정상 동작한다.")
    fun returnBookTest(){
        //given
        val user = userRepository.save(User("yeaseul", 30))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(user, "이상한 나라의 앨리스"))
        val request = BookReturnRequest("yeaseul", "이상한 나라의 앨리스")
        //when
        bookService.returnBook(request)

        //then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].status).isEqualTo(UserLoanStatus.RETURNED)
    }
    @Test
    @DisplayName("책 대여권수를 정상적으로 확인한다.")
    fun countLoanedBook(){
       val saveUser = userRepository.save(User("yeaseul",null))
        userLoanHistoryRepository.saveAll(listOf(
                UserLoanHistory.fixture(saveUser,"A"),
                UserLoanHistory.fixture(saveUser,"B",UserLoanStatus.RETURNED),
                UserLoanHistory.fixture(saveUser,"C",UserLoanStatus.RETURNED)
        ))
        val result = bookService.countLoanedBook()
        //then
        assertThat(result).isEqualTo(1)
    }

    @Test
    @DisplayName("분야별 책 권수를 정상 확인한다.")
    fun getBookStatisticsTest(){
        bookRepository.saveAll(listOf(
                Book.fixture("A",BookType.COMPUTER),
                Book.fixture("B",BookType.COMPUTER),
                Book.fixture("C",BookType.SCIENCE)
        ))
        val results = bookService.getBookStatistics()
        val computers = results.first{ result -> result.type==BookType.COMPUTER}
        val sciences = results.first{ result -> result.type==BookType.SCIENCE}

        //then
        assertThat(results).hasSize(2)
        assertThat(computers.count).isEqualTo(2L)
        assertThat(sciences.count).isEqualTo(1L)
    }
}