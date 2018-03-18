package com.smartfridge.smartfridge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartFridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartFridgeApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(SmartFridgeRepository repository, SmartFridgeManager smartFridgeManager, ItemTrackingServiceImpl itemTrackingService) {
		return (args) -> {
			//In order to test turn on uncomment below lines
//     		smartFridgeManager.handleItemRemoved("id10");
//			smartFridgeManager.handleItemAdded(1l, "id1","Milk",0.5d);
//			smartFridgeManager.handleItemAdded(1l, "id2","Milk",0.2d);
//			smartFridgeManager.handleItemAdded(3l, "id3","Coffee",0.2d);
//			smartFridgeManager.handleItemAdded(3l, "id4","Coffee",0.2d);
//			smartFridgeManager.handleItemAdded(2l, "id5","OrangeJuice",0.3d);
//			smartFridgeManager.handleItemAdded(4l, "id6","Pepsi",0.3d);
//			smartFridgeManager.forgetItem(2l);
//			smartFridgeManager.getItems(0.5);
//			smartFridgeManager.getFillFactor(1l);
		};
	}
}
