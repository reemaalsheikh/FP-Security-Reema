package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.Model.Orders;
import com.example.finalproject.Model.UsedItem;
import com.example.finalproject.Model.User;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.OrdersRepository;
import com.example.finalproject.Repository.UsedItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsedItemService {

    private final UsedItemRepository usedItemRepository;
    private final AuthRepository userRepository;
    private final OrdersRepository ordersRepository;

    public List<UsedItem> getAllUsedItem() {
        return usedItemRepository.findAll();
    }

    public void addUsedItem(Integer usedItem_id,UsedItem usedItem) {
        User user=userRepository.findUserById(usedItem_id);
        if(user==null){
            throw new ApiException("User not found");
        }
        usedItem.setSeller(user);
        usedItemRepository.save(usedItem);
    }

    public void updateUsedItem(Integer usedItem_id,Integer auth_id, UsedItem usedItem) {
        User user=userRepository.findUserById(auth_id);
        if(user==null){
            throw new ApiException("User not found");
        }
        UsedItem oldUsedItem=usedItemRepository.findUsedItemById(usedItem_id);
        if(oldUsedItem==null){
            throw new ApiException("UsedItem not found");
        }else if(oldUsedItem.getSeller().getId()!=auth_id){
            throw new ApiException("sorry you don't have authority");
        }
        oldUsedItem.setName(usedItem.getName());
        oldUsedItem.setDescription(usedItem.getDescription());
        oldUsedItem.setPrice(usedItem.getPrice());
        oldUsedItem.setCategory(usedItem.getCategory());
        oldUsedItem.setUsed(usedItem.isUsed());
        oldUsedItem.setSeller(usedItem.getSeller());
        usedItemRepository.save(oldUsedItem);
    }

    public void deleteUsedItem(Integer auth_id,Integer usedItem_id) {
        User user=userRepository.findUserById(auth_id);
        if(user==null){
            throw new ApiException("User not found");
        }
        UsedItem oldUsedItem=usedItemRepository.findUsedItemById(usedItem_id);
        if(oldUsedItem==null){
            throw new ApiException("UsedItem not found");
        }else if(oldUsedItem.getSeller().getId()!=auth_id){
            throw new ApiException("sorry you don't have authority");
        }
        usedItemRepository.delete(oldUsedItem);
    }


    // Buy method by Omar
    public void buyUsedItem(Integer itemId, Integer buyerId) {
        // Find the used item
        UsedItem usedItem = usedItemRepository.findById(itemId)
                .orElseThrow(() -> new ApiException("Used item not found"));

        // Check if the item already has a buyer (i.e., it's already sold)
        if (usedItem.getBuyer() != null) {
            throw new ApiException("Item is already sold");
        }

        // Find the buyer
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new ApiException("Buyer not found"));

        // Check if the seller exists
        if (usedItem.getSeller() == null) {
            throw new ApiException("Seller not assigned for this item");
        }

        // Create a new order
        Orders order = new Orders();
        order.setOrderDate(LocalDate.now());
        order.setTotalPrice(usedItem.getPrice());
        order.setStatus("COMPLETED");
        order.setBuyer(buyer);
        order.setSeller(usedItem.getSeller());

        ordersRepository.save(order);

        // Set the buyer and link the order
        usedItem.setBuyer(buyer);
        usedItem.setOrders(order);

        usedItemRepository.save(usedItem);
    }

    //Omar
    // Get items by seller method by Omar
    public List<UsedItem> getItemsBySeller(Integer sellerId) {
        User seller = userRepository.findUserById(sellerId);
        if (seller == null) {
            throw new ApiException("Seller not found");
        }
        return usedItemRepository.findAllBySeller(seller);
    }
}
