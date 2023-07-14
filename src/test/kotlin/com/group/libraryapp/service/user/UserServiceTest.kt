package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
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
                User("A",20),
                User("B",null),
        ))
        //when
        val results = userService.getUsers()
        //then
        assertThat(results).hasSize(2)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A","B")

    }


}