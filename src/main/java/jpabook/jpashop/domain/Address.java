package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Embeddable //내장타입이라는 뜻
@Getter
@Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
