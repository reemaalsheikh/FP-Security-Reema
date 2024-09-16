package com.example.finalproject.Repository;
import com.example.finalproject.Model.Orders;
import com.example.finalproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Orders findOrdersById(Integer id);

    List<Orders> findOrdersByStatus(String status);

    List<Orders> findAllByBuyerOrSeller(User buyer, User seller);

    List<Orders> findAllByBuyer(User buyer);

    List<Orders> findAllBySeller(User seller);

}
