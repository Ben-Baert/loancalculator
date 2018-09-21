package loan;

import javafx.scene.control.TableCell;

import java.util.Locale;

public class FormattedDoubleCell extends TableCell<Payment, Double> {
    @Override
    protected void updateItem(Double value, boolean empty) {
        if (empty) setText(null);
        else setText(String.format(Locale.getDefault(), "%1$,2f", value));
    }
}
