package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //내장타입이라는 뜻
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //protected: 같은 패키지 내 클래스와 상속받은 클래스에서만 생성허용
    //기본적으로 값타입은 변경이 어렵게 만드는것이 좋다.
    protected Address(){

    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
