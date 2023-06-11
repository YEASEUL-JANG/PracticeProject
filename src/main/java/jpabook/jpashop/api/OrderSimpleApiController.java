package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.OrderSimpleQueryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne 관계 (엔티티가 직접 들어있음)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for(Order order : all){
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화

        }

        return all;
        //1. 연관관계에 있는 엔티티끼리 무한루프 걸리는 오류 -> 한쪽에 jsonIgnore 처리
        //2. Lazy타입인 엔티티의 프록시 타입문제 -> Hibernate5Module 빈등록
           //: 하지만 Hibernate5Module 를 사용하기 보다는 DTO로 변환해서 반환하는것이 더 좋다.
           //: 엔티티 자체를 내보내면 필요없는 데이터까지 모두 내보내면서 쿼리가 나가므로 성능저하.
        //3. 프록시 객체 내 데이터를 가져옴으로써 Lazy 강제 초기화(영속성 컨텍스트에 없으면 db쿼리 날려서 가져옴)
        //* Lazy 를 피하기 위해서 즉시 로딩(EARER)으로 설정하면 안됨
        // -> 연관관계가 필요 없는 경우에도 데이터를 항상 조회하여 성능문제 발생
        //항상 지연로딩을 기본으로 하고, 성능 최적화가 필요한 경우에는 페치조인을 사용.
    }

    //엔티티가 아닌 Dto로 변환된 값을 내보낸다.
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;

        //쿼리가 총 1+n+n번 실행된다.
        //order 조회 1번
        //order-> member 지연로딩 조회 n번
        //order-> delivery 지연로딩 조회 n번
        //지연로딩은 영속성 컨텍스트에서 조회하므로, 이미 조회된 경우 쿼리를 생략한다.
        //-> 만약 userA가 모두 한 주문이라면 이미 조회한 order 쿼리는 다시 나가지 않음.

    }

    //Fetch 조인을 하자!
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
        //페치조인으로 order 쿼리 한번에 전부 다 조회해옴.
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderRepository.findOrderDtos();
    }


    @Data
    public class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
    // 쿼리 선택 권장순서
    //1. 엔티티를 dto로 변환하는 방법을 선택
    //2. 필요하다면 페치조인으로 성능을 최적화 한다.
    //3. 그래도 안되면 dto로 직접 조회하는 방법을 사용한다.



}

