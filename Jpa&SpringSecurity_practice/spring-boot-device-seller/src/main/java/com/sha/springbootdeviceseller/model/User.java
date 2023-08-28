package com.sha.springbootdeviceseller.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false, length = 100)
    private String username;

    @Column(name="password" , nullable = false, length=100)
    private String password;

    @Column(name = "name",nullable = false, length = 100)
    private String name;

    @Column(name = "city",nullable = false, length = 200)
    private String city;

    @Column(name = "street",nullable = false, length = 200)
    private String street;

    @Column(name = "zipcode",nullable = false, length = 200)
    private String zipcode;

    @Column(name="create_time",nullable = false)
    private LocalDateTime createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role;

    //Transient : 데이터베이스에 저장되지 않고 일회성으로만 사용된다.
    @Transient
    private String token;

}
