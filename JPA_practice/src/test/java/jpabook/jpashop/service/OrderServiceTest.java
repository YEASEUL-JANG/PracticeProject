package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //테스트 하나 끝나면 롤백시킴
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        Member member = createMember();
        Book book = createBook("시골 jpa", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order getorder = orderRepository.findOne(orderId);

        //Assert.assertEquals(message, 기대값, 실제값)
        Assert.assertEquals("상품 주문시 상태는 order", OrderStatus.ORDER, getorder.getStatus());
        Assert.assertEquals("주문한 상품 종류수가 정확해야 함.", 1, getorder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격 * 수량이다.", 10000* orderCount, getorder.getTotalPrice());
        Assert.assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());

    }

    @Test
    public void 주문취소() throws Exception{
        Member member = createMember();
        Book book = createBook("시골 jpa", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        orderService.cancleOrder(orderId);
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("주문취소 후 주문상태",OrderStatus.CANCEL,getOrder.getStatus());
        Assert.assertEquals("주문취소 후 남은 재고수량",10,book.getStockQuantity());

    }
    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        Member member = createMember();
        Item item = createBook("시골 jpa", 10000, 10);
        int orderCount = 11;
        //when
        orderService.order(member.getId(),item.getId(), orderCount);

        //then
        fail("재고수량 예외가 발생해야 함.");
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","동작대로","12345"));
        em.persist(member);
        return member;
    }


}