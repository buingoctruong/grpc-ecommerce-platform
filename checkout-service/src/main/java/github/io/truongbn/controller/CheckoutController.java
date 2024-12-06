package github.io.truongbn.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.io.truongbn.model.CheckoutRequest;
import github.io.truongbn.model.CheckoutResponse;
import github.io.truongbn.service.InventoryService;
import github.io.truongbn.service.ItemService;
import github.io.truongbn.service.ShippingService;
import github.io.truongbn.service.UserService;
import github.io.truongbn.shipping.Address;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final InventoryService inventoryService;
    private final ItemService itemService;
    private final ShippingService shippingService;
    private final UserService userService;
    @PostMapping("/order")
    public CheckoutResponse checkoutOrder(@RequestBody CheckoutRequest checkoutRequest) {
        var itemId = checkoutRequest.getItemId();
        var userId = checkoutRequest.getUserId();
        var purchaseQuantities = checkoutRequest.getPurchaseQuantities();
        var shippingAddress = checkoutRequest.getShippingAddress();
        var inventoryInformation = inventoryService.getInventoryInformation(itemId);
        if (inventoryInformation.getQuantities() < checkoutRequest.getPurchaseQuantities()) {
            throw new IllegalArgumentException(
                    "User %s attempted to place an order exceeding available inventory quantities."
                            .formatted(checkoutRequest.getUserId()));
        }
        var item = itemService.getItemInformation(itemId);
        Address address = Address.newBuilder().setPostalCode(shippingAddress.postalCode())
                .setCity(shippingAddress.city()).setState(shippingAddress.state())
                .setStreet(shippingAddress.street()).setBuildingName(shippingAddress.buildingName())
                .setRoomNumber(shippingAddress.roomNumber()).build();
        var shippingFee = shippingService.getShippingFee(item, purchaseQuantities, address);
        double orderAmount = item.getPrice() * purchaseQuantities + shippingFee.getFee();
        var user = userService.getUserInformation(userId);
        if (orderAmount > user.getBalance()) {
            throw new IllegalArgumentException(
                    "User %s attempted to place an order exceeding available user balance."
                            .formatted(checkoutRequest.getUserId()));
        }
        return CheckoutResponse.builder().itemId(itemId).userId(userId)
                .purchaseQuantities(purchaseQuantities).orderAmount(orderAmount).build();
    }
}
