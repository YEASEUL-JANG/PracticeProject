package com.sha.springbootdeviceseller.repository.projection;

import com.sha.springbootdeviceseller.model.DeviceType;

import java.time.LocalDateTime;
//반환 타입 dto를 따로 지정해줌
public interface PurchaseItem {
    String getName();
    DeviceType getType();
    Double getPrice();
    String getColor();
    LocalDateTime getPurchaseTime();
}
