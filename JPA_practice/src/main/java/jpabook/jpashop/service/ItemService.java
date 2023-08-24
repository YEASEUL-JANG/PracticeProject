package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item){
        itemRepository.save(item);
        return item.getId();
    }
    @Transactional
    //트랜잭션-> 커밋을한다. (영속성 컨텍스트에 업데이트 된 내용을 반영한다)
    // -> save로 재 저장할 필요가 없음.
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        //트랜잭션 안에서 엔티티를 조회해야 영속성 상태로 조회가 되고, 영속성 컨텍스트안의 객체를 변경해야
        //커밋시점에 db에 반영됨.(변경감지-> update쿼리 실행)
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }
    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }
}
