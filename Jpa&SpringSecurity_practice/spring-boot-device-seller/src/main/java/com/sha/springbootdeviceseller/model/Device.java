package com.sha.springbootdeviceseller.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Column(name="description", nullable = false, length = 100)
    private String description;

    @Column(name="price", nullable = false)
    private Double price;

    @Column(name="create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name="stockQuantity", nullable = false)
    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name="device_type", nullable = false)
    private DeviceType deviceType;
}
