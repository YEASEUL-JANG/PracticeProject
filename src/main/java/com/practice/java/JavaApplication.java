package com.practice.java;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class JavaApplication {

	public static void main(String[] args){
		Integer integer = Integer.valueOf(5);
		Integer integer2 = Integer.valueOf(5);
		Integer integer3 = new Integer(5);
		System.out.println(integer==integer2?"true":"false");
		System.out.println(integer==integer3?"true":"false");
	}
}