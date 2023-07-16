package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.loanhistory.User
import com.group.libraryapp.domain.user.loanhistory.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
//서비스하는 대상이 스프링이 관리하는 @Service 단이므로 SpringBootTest를 붙힌다.
@SpringBootTest
class UserServiceTest @Autowired constructor(
        private val userRepository: UserRepository,
        private val userService: UserService,

        ) {
    @AfterEach
    fun clean(){
        userRepository.deleteAll()
    }
    @Test
    fun saveUserTest(){
        //given
        val request = UserCreateRequest("yeseul",null)
        //when
        userService.saveUser(request)
        //then
        val results = userRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("yeseul")
        assertThat(results[0].age).isNull()

    }
    @Test

    fun getUserTest(){
        //given
        userRepository.saveAll(listOf(
                User("A", 20),
                User("B", null),
        ))
        //when
        val results = userService.getUsers()
        //then
        assertThat(results).hasSize(2)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A","B")

    }
    @Test
    @DisplayName("유저 업데이트가 정상 동작한다")
    fun updateUserNameTest() {
        //given
        val savedUser = userRepository.save(User("A",null))
        val request = UserUpdateRequest(savedUser.id!!,"B")
        //when
        userService.updateUserName(request)
        //then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제가 정상 동작한다.")
    fun deleteUserTest(){
        //given
        userRepository.save(User("A",null))
        //when
        userService.deleteUser("A")
        //then
        assertThat(userRepository.findAll()).isEmpty()
    }

}