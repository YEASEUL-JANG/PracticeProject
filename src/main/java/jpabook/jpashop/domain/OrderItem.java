package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice;
    private int count;

    //다른 클래스에서 new로 생성되는 것을 막음(유지보수)
    protected OrderItem() {
    }

    //==생성메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }
    //==비지니스 로직==//

    /**
     * 재고수량 원복
     */
    public void cancel() {

        getItem().addStock(count);
    }

    //==조회 로직==//

    /**
     * 주문 총가격(수량*가격)
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

}
