package com.assignment.frieghtous.models.classes.paymentMethods;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class Card extends PaymentMoney{
    private double cardNetValue;

    public Card(double cardNetValue){
        this.cardNetValue = cardNetValue;
        super.setTotalPayed(cardNetValue);
    }
}
