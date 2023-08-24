package com.practice.java.interface_ex;


import java.util.Scanner;

public class customCooking implements Cooking{

    @Override
    public void cleaning() {
        System.out.println("자리를 청소합니다.");
    }

    @Override
    public void Buying() {
            System.out.println("재료를 준비합니다.");
        }

    @Override
    public void cooking() {
        System.out.println("요리를 시작합니다.");
    }

    @Override
    public void tasting() {
        String taste="";
            System.out.println("맛을봅니다..");
            System.out.println("맛이있나요? (y/n)");
            Scanner scan = new Scanner(System.in);
            taste = scan.next();
        System.out.println(taste.equals("y")?"맛있다!":"맛없다ㅜ!");
        }
        void letscooking(){
            start();
            cleaning();
            Buying();
            cooking();
            tasting();
        }
    }

