package com.assignment.frieghtous;

import com.assignment.frieghtous.models.Interfaces.PaymentMethod;
import com.assignment.frieghtous.models.classes.MachineItem;
import com.assignment.frieghtous.models.classes.VendingMachine;
import com.assignment.frieghtous.models.classes.paymentMethods.Card;
import com.assignment.frieghtous.models.classes.paymentMethods.Coin;
import com.assignment.frieghtous.models.classes.paymentMethods.Note;
import com.assignment.frieghtous.models.classes.paymentMethods.PaymentMoney;
import com.assignment.frieghtous.utils.enums.CoinValueEnum;
import com.assignment.frieghtous.utils.enums.NoteValueEnum;
import com.assignment.frieghtous.utils.enums.VendingMachineType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class FrieghtousApplication {

	public static void main(String[] args) throws InterruptedException, IOException {

		// define vm and items
		// generated with dummy coffee names  with random generated values for each item in VM
		VendingMachine frieghtousVM = defineVendingMachineAndItsItems();

		Scanner sc = new Scanner(System.in);
		// do vending machine stuff
		while (true) {
			System.out.println("Welcome ! Please Enter your Order !");
			// 1-  read input from user, store the order number in VM
			frieghtousVM.setMachineKeypadInput(sc.nextInt());

			// 2- show the user a message with Item name and its price
			if(frieghtousVM.showSelectedItemInfo()==false){
				//skip if item is not available - quantity =0
				continue;
			}

			// 3- Select Payment Method
			System.out.println("Please Select Your Payment Method\n1- Card \n2-Coins\n3- Noes\n4-Cancel");
			switch(sc.nextInt()){
				case 1: frieghtousVM.setPaymentMoney(new Card(20)); //let suppose the payed money are always the same
						break;
				case 2: frieghtousVM.setPaymentMoney(new Coin(CoinValueEnum.FIFTY_CENTS));
						break;
				case 3: frieghtousVM.setPaymentMoney(new Note(NoteValueEnum.FIFTY_USD));
						break;
				case 4: continue;
			}

			System.out.println("Money Payed: "+frieghtousVM.getPaymentMoney().getTotalPayed());

			// 4- validate if payment is done successfully
			if(!isValidPayment(frieghtousVM)){
				// invalid payment
				System.out.println("Invalid Payment, Not Enough Balance !!");
				continue;
			}
			else {
				// 5- if payment is valid -  check if there is any exchange to return - desc the quantity and do the job

				//update the item map inside VM - desc the quantity by 1
				MachineItem machineItem = frieghtousVM.getMachineItemsMap().get(frieghtousVM.getMachineKeypadInput());
				machineItem.setItemQuantity(machineItem.getItemQuantity()-1);
				frieghtousVM.getMachineItemsMap().put(frieghtousVM.getMachineKeypadInput(),machineItem);

				// alert the customer to take the exchange if any
				double totalPayed = frieghtousVM.getPaymentMoney().getTotalPayed();
				double itemPrice = frieghtousVM.getMachineItemsMap().get(frieghtousVM.getMachineKeypadInput()).getItemPrice();
				if (totalPayed - itemPrice > 0) {
					System.out.println("Take the exchange pls !\nExchange : "+ (totalPayed-itemPrice));
				}
				System.out.println("Processing Your Order .......");
				TimeUnit.SECONDS.sleep(3);

				// show a message to user to take his/her order
				System.out.println("Please take your order ! Bon Appetit !");
				// sleep and re-do  VM machines stuff
				TimeUnit.SECONDS.sleep(3);

			}
			// reset keypad to null
			frieghtousVM.setMachineKeypadInput(null);
		}


	}

	private static boolean isValidPayment(VendingMachine frieghtousVM) {
		double itemPrice = frieghtousVM.getMachineItemsMap().get(frieghtousVM.getMachineKeypadInput()).getItemPrice();
		PaymentMoney paymentMethodNet = frieghtousVM.getPaymentMoney();
		return  ((paymentMethodNet.getTotalPayed()-itemPrice)>0 )?  true:false;
	}

	private static VendingMachine defineVendingMachineAndItsItems() {
		//define vm attributes - coffee type machine - DOLLAR Currency
		final DecimalFormat df = new DecimalFormat("0.00");

		VendingMachine frieghtousVM = new VendingMachine();
		frieghtousVM.setVendingMachineType(VendingMachineType.COFFEE);
		frieghtousVM.setPaymentMoney(new PaymentMoney());

		// define items - items in 5 x 5 - 25 numbers - each item connected to a number from 1 to 25
		Map<Integer,MachineItem> machineItemMap = new HashMap<>();
		for (int i=1;i<=25;i++){
			machineItemMap.put(i,new MachineItem("Coffee #"+i,Double.valueOf(df.format((double) (Math.random() * (3 - 0.1)) + 0.1)),10));
		}

		frieghtousVM.setMachineItemsMap(machineItemMap);
		return frieghtousVM;
	}

}
