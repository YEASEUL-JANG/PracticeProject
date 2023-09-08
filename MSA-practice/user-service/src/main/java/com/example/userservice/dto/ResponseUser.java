package com.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //json 데이터에서 null 값은 제외시킴
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    private List<ResponseOrder> orders;

    @Data
    public class ResponseOrder{
        private String productId;
        private String qty;
        private Integer unitPrice;
        private Integer totalPrice;
        private Date createdAt;
        private String orderId;
    }
}
