package com.group.libraryapp.calculator

import java.lang.IllegalArgumentException

//data class : 비교 시 주소비교가 아닌 값비교를 사용한다.
data class Calculator(
        private var _number: Int
) {
    //public number를 받아서 내부의 _number에 접근할 수 있게 함.
    val number: Int
        get() = this._number
    fun add(operand: Int){
        this._number += operand
    }
    fun minus(operand: Int){
        this._number -= operand
    }
    fun multiply(operand: Int){
        this._number *= operand
    }
    fun devide(operand: Int){
        if(operand == 0){
            throw IllegalArgumentException("0으로 나눌 수 없습니다.")
        }
        this._number /= operand
    }
}