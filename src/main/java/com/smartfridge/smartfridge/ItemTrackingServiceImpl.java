package com.smartfridge.smartfridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemTrackingServiceImpl {

    @Autowired
    private ItemTrackingRepository itemTrackingRepository;

    public void setTracking(long itemType) {
        Optional<ItemTypeTracking> itemTypeTrackingCheck = itemTrackingRepository.findById(itemType);
        if(!itemTypeTrackingCheck.isPresent()) {
            ItemTypeTracking itemTypeTracking = new ItemTypeTracking(itemType, false);
            itemTrackingRepository.save(itemTypeTracking);
        }
    }
}
