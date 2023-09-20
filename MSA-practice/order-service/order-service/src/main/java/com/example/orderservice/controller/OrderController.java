package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.RequestOrder;
import com.example.orderservice.dto.ResponseOrder;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.kafka.KafkaProducer;
import com.example.orderservice.kafka.OrderProducer;
import com.example.orderservice.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order-service")
public class OrderController {
    private OrderService orderService;
    private KafkaProducer kafkaProducer;

    private OrderProducer orderProducer;
    @Autowired
    public OrderController(OrderProducer orderProducer,OrderService orderService, KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        this.orderService = orderService;
        this.orderProducer = orderProducer;
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder (@PathVariable("userId") String userId,
                                                      @RequestBody RequestOrder orderDetails){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = modelMapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        /* jpa */
//        OrderDto createDto = orderService.createOrder(orderDto);
//        ResponseOrder returnValue = modelMapper.map(createDto, ResponseOrder.class);

        /* kafka */
         orderDto.setOrderId(UUID.randomUUID().toString());
         orderDto.setTotalPrice(orderDetails.getQty()* orderDetails.getUnitPrice());
         ResponseOrder returnValue = modelMapper.map(orderDto, ResponseOrder.class);

        /* Send an order to the Kafka */
        kafkaProducer.send("example-order-topic", orderDto);
        orderProducer.send("orders",orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder (@PathVariable("userId") String userId){
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v-> {
            result.add(new ModelMapper().map(v, ResponseOrder.class));
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
