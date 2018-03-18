package com.smartfridge.smartfridge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class SmartFridgeManageImpl implements SmartFridgeManager {


    private SmartFridgeRepository smartFridgeRepository;

    private ItemTrackingServiceImpl itemTrackingService;

    @Autowired
    public SmartFridgeManageImpl(SmartFridgeRepository smartFridgeRepository, ItemTrackingServiceImpl itemTrackingService) {
        this.smartFridgeRepository = smartFridgeRepository;
        this.itemTrackingService = itemTrackingService;
    }

    @Override
    public void handleItemRemoved(String itemUUID) {
        try {
            smartFridgeRepository.deleteById(itemUUID);
        } catch(EmptyResultDataAccessException e) {
            log.info("Item with UUID %s doesn't exist to remove", itemUUID);
        }
    }

    @Override
    public void handleItemAdded(long itemType, String itemUUID, String name, Double fillFactor) {
        FridgeItem newFridgeItem = new FridgeItem(itemUUID,name,itemType,fillFactor);
        smartFridgeRepository.save(newFridgeItem);
    }

    @Override
    public Object[] getItems(Double fillFactor) {
        List<ItemTypeFillFactorDto> itemTypeFillFactorDtos = new ArrayList<>();
        Map<Long,Double> itemTypeFillFactorMap = new HashMap<>();
        List<FridgeItem> fridgeItems = smartFridgeRepository.getAllTrackingFridgeItem();
        populateTheMap(fridgeItems,itemTypeFillFactorMap);
        calculateFillFactor(itemTypeFillFactorMap,itemTypeFillFactorDtos,fillFactor);
    return itemTypeFillFactorDtos.toArray();
    }

    private void calculateFillFactor(Map<Long, Double> itemTypeFillFactorMap, List<ItemTypeFillFactorDto> itemTypeFillFactorDtos, Double fillFactor) {

        for (Map.Entry<Long, Double> entry : itemTypeFillFactorMap.entrySet()) {
            if (entry.getValue() <= fillFactor) {
                ItemTypeFillFactorDto itemTypeFillFactorDto = new ItemTypeFillFactorDto();
                itemTypeFillFactorDto.setItemType(entry.getKey());
                itemTypeFillFactorDto.setTotalFactor(entry.getValue());
                itemTypeFillFactorDtos.add(itemTypeFillFactorDto);
            }
        }

    }

    private void populateTheMap(List<FridgeItem> fridgeItems, Map<Long, Double> itemTypeFillFactorMap) {
        for(FridgeItem fridgeItem: fridgeItems) {
            if(itemTypeFillFactorMap.containsKey(fridgeItem.getItemType())) {
                Double total = itemTypeFillFactorMap.get(fridgeItem.getItemType()) + fridgeItem.getFillFactor();
                itemTypeFillFactorMap.put(fridgeItem.getItemType(), total);
            }
            else {
                itemTypeFillFactorMap.put(fridgeItem.getItemType(),fridgeItem.getFillFactor());
            }
        }
    }

    @Override
    public Double getFillFactor(long itemType) {
        Double totalFactor=0.0d;
        List<FridgeItem>  fridgeItemList = smartFridgeRepository.findAllByItemType(itemType);
        for (FridgeItem fridgeItem: fridgeItemList) {
            if(fridgeItem.getFillFactor() != 0.0) {
                totalFactor+=fridgeItem.getFillFactor();
            }
        }
        return totalFactor;
    }

    @Override
    public void forgetItem(long itemType) {
        itemTrackingService.setTracking(itemType);
    }
}
