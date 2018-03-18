package com.smartfridge.smartfridge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTrackingServiceImplTest {

    @InjectMocks
    private ItemTrackingServiceImpl itemTrackingService;

    @Mock
    private ItemTrackingRepository itemTrackingRepository;

    @Test
    public void setTrackingTest_CallToAddItemTypeTracking_WhenItemTypeDoesNotExist() {
        Mockito.when(itemTrackingRepository.save(Mockito.any(ItemTypeTracking.class))).thenReturn(new ItemTypeTracking());
        itemTrackingService.setTracking(2);
        Mockito.verify(itemTrackingRepository, Mockito.times(1)).save(Mockito.any(ItemTypeTracking.class));
    }

    @Test
    public void setTrackingTest_ShouldNotCallToAddItemTypeTracking_WhenItemExists() {
        Optional<ItemTypeTracking> itemTypeTrackingCheck = Optional.of(new ItemTypeTracking());
        Mockito.when(itemTrackingRepository.findById(2l)).thenReturn(itemTypeTrackingCheck);
        itemTrackingService.setTracking(2);
        Mockito.verify(itemTrackingRepository, Mockito.times(0)).save(Mockito.any(ItemTypeTracking.class));

    }
}
