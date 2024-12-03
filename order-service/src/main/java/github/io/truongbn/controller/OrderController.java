package github.io.truongbn.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.io.truongbn.entiry.OrderRequest;
import github.io.truongbn.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public void purchaseOrder(@RequestBody OrderRequest orderRequest) {
        orderService.purchaseOrder(orderRequest);
        log.info("User {} successfully purchased {} (quantity: {}).", orderRequest.getUserId(),
                orderRequest.getItemId(), orderRequest.getPurchaseQuantities());
    }
}
