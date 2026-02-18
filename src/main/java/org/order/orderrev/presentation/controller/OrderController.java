package org.order.orderrev.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.order.orderrev.application.dto.request.NewOrderRequestDTO;
import org.order.orderrev.application.dto.response.NewOrderResponseDTO;
import org.order.orderrev.application.usecase.CreateNewOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final CreateNewOrderUseCase newOrderUseCase;

    @PostMapping
    public ResponseEntity<NewOrderResponseDTO> create(@RequestBody NewOrderRequestDTO dto) {
        var orderDto = newOrderUseCase.create(dto);
        return ResponseEntity.ok(orderDto);
    }
}
