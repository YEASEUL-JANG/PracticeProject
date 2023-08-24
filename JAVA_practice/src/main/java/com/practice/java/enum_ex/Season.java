package com.practice.java.enum_ex;

enum Season {
    WINTER(4), SPRING(3), FALL(2), SUMMER(1);
    private int num;

    Season(int num) {
        this.num = num;
    }
    public int getNum(){
        return this.num;
    }
}
