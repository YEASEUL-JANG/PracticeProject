package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //자식들이 한테이블에 합쳐져서 나옴.
@DiscriminatorColumn(name="dtype") //자식테이블에 대한 명칭을 구분한다.
@Getter@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //비지니스 로직 (stockQuantity값을 변경하려면
    //핵심비지니스 로직을 통해서 변경해야 함.

    /**
     * 재고수량 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }
    /**
     * 재고수량 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
    if(restStock < 0) {
        throw new NotEnoughStockException("need more stock");
    }
    this.stockQuantity = restStock;
    }
}
