package com.assignment.frieghtous.models.classes.paymentMethods;
import com.assignment.frieghtous.utils.enums.CoinValueEnum;
import com.assignment.frieghtous.utils.enums.NoteValueEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class Note extends PaymentMoney{
    private NoteValueEnum noteValueEnum;

    public Note(NoteValueEnum noteValueEnum){
        this.noteValueEnum = noteValueEnum;
        super.setTotalPayed(noteValueEnum.value);
    }
}
