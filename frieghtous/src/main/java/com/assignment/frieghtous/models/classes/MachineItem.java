package com.assignment.frieghtous.models.classes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MachineItem {
    private String itemName;
    private double itemPrice;
    private int itemQuantity;
}
