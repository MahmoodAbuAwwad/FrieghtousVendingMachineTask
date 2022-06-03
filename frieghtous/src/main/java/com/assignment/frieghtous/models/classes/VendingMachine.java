package com.assignment.frieghtous.models.classes;

import com.assignment.frieghtous.models.Interfaces.PaymentMethod;
import com.assignment.frieghtous.models.classes.paymentMethods.PaymentMoney;
import com.assignment.frieghtous.utils.enums.VendingMachineType;
import lombok.Data;

import java.util.Map;

@Data
public class VendingMachine {
    private final String machineCurrency = "USD";
    private VendingMachineType vendingMachineType;
    private Integer machineKeypadInput;
    private Map<Integer, MachineItem> machineItemsMap;
    private PaymentMoney paymentMoney;

    private boolean validateMachineKeypadInput(int machineKeypadInput){
        if(machineKeypadInput>0 && machineKeypadInput<=25){
            return true;
        }
        else{
            // Show to customer warning message - no items with the selected number
            System.out.println("No Items with the selected number ! Please Try again ! ");
            return  false;
        }
    }

    public boolean showSelectedItemInfo(){
        if(this.getMachineItemsMap().get(this.machineKeypadInput).getItemQuantity()>0){
            System.out.println( "Item Name :" + this.getMachineItemsMap().get(this.machineKeypadInput).getItemName() +
                    "\nItem Price :"+ this.getMachineItemsMap().get(this.machineKeypadInput).getItemPrice());
            return true;
        }
        else{
            System.out.println("Item you requested is not available ! ");
            return false;
        }

    }
}
