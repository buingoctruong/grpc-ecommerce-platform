package github.io.truongbn.service;

import static github.io.truongbn.entiry.Status.PICKUP;

import java.util.UUID;

import org.springframework.stereotype.Service;

import github.io.truongbn.entiry.Order;
import github.io.truongbn.entiry.OrderRequest;
import github.io.truongbn.inventory.InventorySyncResponse;
import github.io.truongbn.repository.OrderRepository;
import github.io.truongbn.user.UserDebitBalanceResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;
    private final UserService userService;
    @Transactional
    public void purchaseOrder(OrderRequest orderRequest) {
        String itemId = orderRequest.getItemId();
        String userId = orderRequest.getUserId();
        int purchaseQuantities = orderRequest.getPurchaseQuantities();
        double userBalance = orderRequest.getUserBalance();
        double orderAmount = orderRequest.getOrderAmount();
        InventorySyncResponse inventorySyncResponse = inventoryService.syncInventory(itemId,
                purchaseQuantities);
        if (!inventorySyncResponse.getResult()) {
            throw new IllegalStateException(
                    "Couldn't update inventory for the item %s".formatted(itemId));
        }
        UserDebitBalanceResponse userDebitBalanceResponse = userService
                .executeUserDebitBalance(userId, userBalance - orderAmount);
        if (!userDebitBalanceResponse.getResult()) {
            throw new IllegalStateException(
                    "Couldn't update balance for the user %s".formatted(itemId));
        }
        Order order = Order.builder().id(UUID.randomUUID().toString()).itemId(itemId).userId(userId)
                .orderAmount(orderAmount).purchaseQuantities(purchaseQuantities).status(PICKUP)
                .build();
        orderRepository.save(order);
    }
}
