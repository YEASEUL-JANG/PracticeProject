package com.group.libraryapp.calculator


import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class JunitCalculatorTest {
    private lateinit var calculator: Calculator

    @BeforeEach
    fun setup(){
        calculator = Calculator(5)
    }
    @Test
    fun addTest() {
        //when
        calculator.add(3)
        //then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun minusTest() {
        //when
        calculator.minus(3)
        //then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun multiplyTest() {
        //when
        calculator.multiply(3)
        //then
        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    fun devideTest() {
        //when
        calculator.devide(2)
        //then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun devideExceptionTest() {
        //when & then
        //IllegalArgumentException 에러가 나는걸 기대한다
        val message = assertThrows<IllegalArgumentException>{
            calculator.devide(0)
        }.message
        assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
    }

}