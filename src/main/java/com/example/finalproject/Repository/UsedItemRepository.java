package com.example.finalproject.Repository;
import com.example.finalproject.Model.UsedItem;
import com.example.finalproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsedItemRepository extends JpaRepository<UsedItem, Integer> {

    UsedItem findUsedItemById(Integer id);

    List<UsedItem> findAllBySeller(User seller);

}
