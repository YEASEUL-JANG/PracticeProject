package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @JsonIgnore //양방향 연관관계에서 한쪽을 JsonIgnore해주어야 함. 안그럼 무한루프 걸림.
    @OneToMany(mappedBy = "member") //order테이블에 있는 member필드와 연관된거다.
    //나는 맵핑된 거울일 뿐이다.(주인은 orders)
    private List<Order> orders = new ArrayList<>();

}
