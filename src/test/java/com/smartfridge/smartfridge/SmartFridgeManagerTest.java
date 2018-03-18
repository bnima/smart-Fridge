package com.smartfridge.smartfridge;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartFridgeManagerTest {

    @InjectMocks
    private SmartFridgeManageImpl smartFridgeManager;

    @Mock
    private SmartFridgeRepository smartFridgeRepository;

    @Mock
    private ItemTrackingServiceImpl itemTrackingService;

    @Test
    public void getFillFactor_returnTotalFactor_whenAllContainersAreNotEmpty(){
        List<FridgeItem> fridgeItems = new ArrayList<>();
        FridgeItem fridgeItem1 = new FridgeItem("id1","Milk",2,0.5d);
        FridgeItem fridgeItem2 = new FridgeItem("id1","Milk",2,0.3d);
        fridgeItems.add(fridgeItem1);
        fridgeItems.add(fridgeItem2);
        Mockito.when(smartFridgeRepository.findAllByItemType(2)).thenReturn(fridgeItems);
        Double result = smartFridgeManager.getFillFactor(2);
        Assert.assertEquals(0.8d,result,0.001);
    }

    @Test
    public void handleItemRemoved_removeItemBasedOnUUID_whenItemExist(){
        Mockito.doNothing().when(smartFridgeRepository).deleteById("id1");
        smartFridgeManager.handleItemRemoved("id1");
        Mockito.verify(smartFridgeRepository, Mockito.times(1)).deleteById("id1");
    }

    @Test
    public void getItem_returnListOfItemsNeedToRefill_whenContainersNotEmpty(){
        List<FridgeItem> fridgeItems = new ArrayList<>();
        FridgeItem fridgeItem1 = new FridgeItem("id1","Milk",2,0.2d);
        FridgeItem fridgeItem2 = new FridgeItem("id2","Milk",2,0.1d);
        FridgeItem fridgeItem3 = new FridgeItem("id3","Milk",3,0.7d);
        fridgeItems.add(fridgeItem1);
        fridgeItems.add(fridgeItem2);
        fridgeItems.add(fridgeItem3);
        Mockito.when(smartFridgeRepository.getAllTrackingFridgeItem()).thenReturn(fridgeItems);
        Object[] result = smartFridgeManager.getItems(0.5);
        Assert.assertEquals(1,result.length);
    }

    @Test
    public void getItem_returnEmptyListOfItems_whenTheyHaveEnough(){
        List<FridgeItem> fridgeItems = new ArrayList<>();
        FridgeItem fridgeItem1 = new FridgeItem("id1","Milk",2,0.7d);
        FridgeItem fridgeItem2 = new FridgeItem("id2","Milk",2,0.2d);
        FridgeItem fridgeItem3 = new FridgeItem("id3","Milk",3,0.7d);
        fridgeItems.add(fridgeItem1);
        fridgeItems.add(fridgeItem2);
        fridgeItems.add(fridgeItem3);
        Mockito.when(smartFridgeRepository.getAllTrackingFridgeItem()).thenReturn(fridgeItems);
        Object[] result = smartFridgeManager.getItems(0.5);
        Assert.assertEquals(0,result.length);
    }
}
