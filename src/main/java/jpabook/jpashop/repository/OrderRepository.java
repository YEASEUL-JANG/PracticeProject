package jpabook.jpashop.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.QMember;
import jpabook.jpashop.domain.QOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;
    public void save(Order order) {
        em.persist(order);
    }
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }
    //동적 쿼리 작성법
    //실무에서는 조건에 따라 실행되는 쿼리가 달라지는 동적쿼리를 많이 사용한다.
    //쿼리를 분기식을 통해 문자로 직접 짜는 방법은 복잡하고 실수가 잦을 수 있으므로 비추.
    //JPA 공식문서에 criteria 를 활용한 동적쿼리방법이 있지만
    // 실제 실무에서는 Querydsl로 처리할 것을 추천한다.
    public List<Order> findAllByString(OrderSearch orderSearch) {
//        List<Order> resultList = em.createQuery("select o from Order o join o.member m" +
//                        " where o.status = :status " +
//                        "and m.name like :name", Order.class)
//                .setParameter("status",orderSearch.getOrderStatus())
//                .setParameter("name",orderSearch.getMemberName())
//                //.setFirstResult(100) :100부터 시작함 (페이징할때 사용)
//                .setMaxResults(1000)
//                .getResultList();
//        return resultList;

        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;

        return query
                .select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
                .limit(1000)
                .fetch();
    }

    private Predicate nameLike(String memberName) {
        if(!StringUtils.hasText(memberName)){ //null or ""
            return null;
        }
        return QMember.member.name.contains(memberName);
    }

    private BooleanExpression statusEq(OrderStatus orderStatus) {
        if(orderStatus == null){
            return null; // null을 반환하면 where문 자체가 실행안됨.
        }
        return QOrder.order.status.eq(orderStatus);
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class
        ).getResultList();
    }
    //fetch 조인을 사용하게 되면 Lazy 처리 무시하고
    // Order를 가져올 때 연관관계 에 있는 member, delivery 모두 가져와서 채움.

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                "select new jpabook.jpashop.repository.OrderSimpleQueryDto(o.id, m.name,o.orderDate, o.status,d.address) " +
                        "from Order o " +
                " join o.member m" +
                " join o.delivery d",OrderSimpleQueryDto.class)
                .getResultList();
    }

    //new 명령어를 사용해서 JPQL 의 결과를 DTO로 즉시 변환함.
    //리포지토리 재사용성이 떨어진다. api스펙에 맞춘 코드가 리포지토리에 들어가는 단점.
}

