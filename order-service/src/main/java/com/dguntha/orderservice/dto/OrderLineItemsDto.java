package com.dguntha.orderservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderLineItemsDto {

    private String id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
