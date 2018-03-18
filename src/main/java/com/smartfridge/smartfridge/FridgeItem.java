package com.smartfridge.smartfridge;

import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FridgeItem {

    @Id
    private String itemUUID;

    private String name;

    private long itemType;

    private Double fillFactor;
}
