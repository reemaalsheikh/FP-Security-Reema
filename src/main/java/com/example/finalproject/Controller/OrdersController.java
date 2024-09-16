package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Model.Club;
import com.example.finalproject.Model.Orders;
import com.example.finalproject.Model.Student;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/get")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.status(200).body(ordersService.getAllOrders());
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@AuthenticationPrincipal User user, @Valid @RequestBody Orders orders) {
        ordersService.addOrder(user.getId(),orders);
        return ResponseEntity.status(200).body("Order added");
    }

    @PutMapping("/update/{orders_id}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal User user,@PathVariable Integer orders_id,@Valid @RequestBody Orders orders) {
        ordersService.updateOrder(user.getId(),orders_id, orders);
        return ResponseEntity.status(200).body("Order updated");
    }

    @DeleteMapping("/delete/{orders_id}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal User user,@PathVariable Integer orders_id) {
        ordersService.deleteOrder(user.getId(),orders_id);
        return ResponseEntity.status(200).body("Order deleted");
    }

    //Reema
    @PutMapping("/applyDiscount/{order_id}")
    public ResponseEntity applyDiscountToOrder(@AuthenticationPrincipal User user, @PathVariable Integer order_id, @RequestParam double discountPercentage) {
        ordersService.applyDiscountToOrder(order_id, user.getId(), discountPercentage);
        return ResponseEntity.status(200).body(new ApiResponse("Discount applied successfully"));
    }

//    /*Renad*/
//    @GetMapping("/get/order/{status}")
//    public ResponseEntity<List<Orders>> getOrdersByStatus(@AuthenticationPrincipal User user,@PathVariable String status) {
//        List<Orders> orders = ordersService.getOrdersByStatus(user.getId(),status);
//        return ResponseEntity.ok(orders);
//    }
//    /*Renad*/
//    @PutMapping("/changeStatus/{order_id}/{status}")
//    public ResponseEntity changeOrderStatus(@AuthenticationPrincipal User user,@PathVariable Integer order_id,@PathVariable String status) {
//        return ResponseEntity.status(200).body(ordersService.changeOrderStatus(user.getId(),order_id,status));
//    }


    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Orders>> getOrderHistoryForUser(@PathVariable Integer userId) {
        List<Orders> orders = ordersService.getOrderHistoryForUser(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<Orders>> getOrdersForBuyer(@PathVariable Integer buyerId) {
        List<Orders> orders = ordersService.getOrdersForBuyer(buyerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Orders>> getOrdersForSeller(@PathVariable Integer sellerId) {
        List<Orders> orders = ordersService.getOrdersForSeller(sellerId);
        return ResponseEntity.ok(orders);
    }






}
