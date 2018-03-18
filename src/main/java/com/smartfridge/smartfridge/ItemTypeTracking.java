package com.smartfridge.smartfridge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ItemTypeTracking {
    @Id
    private long itemType;
    private boolean tracking;
}
