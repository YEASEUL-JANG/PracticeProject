package com.practice.java.enum_ex;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class EnumApplication {
    public static void main(String[] args){
        System.out.println(Arrays.toString(Season.values()));
        System.out.println(Season.WINTER.getNum());
        for(Season s : Season.values()){
            if(s.getNum()==4){
                System.out.println(s.toString());
            }
        }
    }
}
