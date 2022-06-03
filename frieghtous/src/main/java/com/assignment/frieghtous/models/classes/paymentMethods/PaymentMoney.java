package com.assignment.frieghtous.models.classes.paymentMethods;

import com.assignment.frieghtous.models.Interfaces.PaymentMethod;
import lombok.Data;
@Data

public class PaymentMoney implements PaymentMethod {
    private  double totalPayed;
}
