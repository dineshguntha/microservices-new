package com.dguntha.orderservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Order {

    private String id;
    private String orderNumber;

    private List<OrderLineItems> orderLineItems;
}
