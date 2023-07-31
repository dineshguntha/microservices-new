package com.dguntha.orderservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderLineItems {

    @Id
    private String id;

    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
