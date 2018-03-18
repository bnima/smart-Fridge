package com.smartfridge.smartfridge;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SmartFridgeRepository extends CrudRepository<FridgeItem,String> {

    public List<FridgeItem> findAllByItemType(long itemType);

    public void deleteAllByItemType(long itemType);

    @Query(value = "SELECT * from FRIDGE_ITEM where item_Type not in (select item_Type from Item_Type_Tracking)", nativeQuery = true)
    List<FridgeItem> getAllTrackingFridgeItem();
}
