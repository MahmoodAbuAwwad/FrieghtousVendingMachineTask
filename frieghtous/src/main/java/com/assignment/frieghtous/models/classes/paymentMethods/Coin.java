package com.assignment.frieghtous.models.classes.paymentMethods;
import com.assignment.frieghtous.utils.enums.CoinValueEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class Coin extends  PaymentMoney{
    private CoinValueEnum coinValueEnum;

    public Coin(CoinValueEnum coinValueEnum){
        this.coinValueEnum = coinValueEnum;
        super.setTotalPayed(coinValueEnum.value);
    }

}
