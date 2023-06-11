package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 총 주문 2개
 * userA
 * *JPA1 BOOK
 * JPA1 BOOK
 * userB
 * *SPRING1 BOOK
 * *SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;
    @PostConstruct //Bean이 생성된 후 자동으로 해당 메서드가 실행된다.
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1(){
            Member member = createMember("userA","서울","1","1111");

            Book book1 = getBook("JPA1 BOOK",10000,100);
            Book book2 = getBook("JPA2 BOOK",10000,100);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        public void dbInit2(){
            Member member = createMember("userB","전주","2","2222");

            Book book1 = getBook("SPRING1 BOOK",10000,100);
            Book book2 = getBook("SPRING2 BOOK",12000,100);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city,street,zipcode));
            em.persist(member);
            return member;
        }

        private Book getBook(String JPA1_BOOK,int price, int quantity) {
            Book book1 = new Book();
            book1.setName(JPA1_BOOK);
            book1.setPrice(price);
            book1.setStockQuantity(quantity);
            em.persist(book1);
            return book1;
        }
    }

}
