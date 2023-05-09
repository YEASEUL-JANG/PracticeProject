package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
public class Category {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item",
    joinColumns = @JoinColumn(name="category_id"),
    inverseJoinColumns = @JoinColumn(name="item_id")) //중간테이블을 생성함으로써 다대다 관계를 일대다 관계로 변경
    private List<Item> items = new ArrayList<>();

    //셀프로 양방향 연관관계 거는 방법
    @ManyToOne
    @JoinColumn(name="parent_id")
    private Category parent;
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
