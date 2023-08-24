package com.practice.java.interface_ex;


    interface Cooking {
        String taste= "";
        default void start(){ //default로 시작하면 자식클래스에서 구현하지 않아도 됨.(기본메서드)
            System.out.println("요리를 시작한다.");
        }
        void cleaning();
        void Buying();
        void cooking();
        void tasting();
}




