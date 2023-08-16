package com.dguntha.orderservice.service;

import com.dguntha.orderservice.dto.InventoryResponse;
import com.dguntha.orderservice.dto.OrderLineItemsDto;
import com.dguntha.orderservice.dto.OrderRequest;
import com.dguntha.orderservice.model.Order;
import com.dguntha.orderservice.model.OrderLineItems;
import com.dguntha.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        order.setOrderLineItems( orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::mapToOrderLineItemEntity).toList());
       List<String> skuCodes = orderRequest.getOrderLineItemsDtoList().stream()
                .map(OrderLineItemsDto::getSkuCode)
                .toList();


       InventoryResponse[] results = webClientBuilder.build().get()
               .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
      boolean isInStock =  Arrays.stream(results).allMatch(InventoryResponse::isInStock);
       if (isInStock) {
           orderRepository.save(order);
       } else {
           throw new IllegalArgumentException("Product is not in stock");
       }
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    private OrderLineItems mapToOrderLineItemEntity(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .skuCode(orderLineItemsDto.getSkuCode())
                .quantity(orderLineItemsDto.getQuantity())
                .build();
    }
}
