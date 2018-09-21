package loan;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class DoubleFilter implements UnaryOperator<TextFormatter.Change> {
    private static final DoubleFilter instance = new DoubleFilter();

    private DoubleFilter() {
    }

    static DoubleFilter getInstance() {
        return instance;
    }

    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        String text = change.getText();

        if (text.isEmpty()) {
            return change;
        }

        int newLength = change.getControlNewText().length();
        if (newLength > 5) {
            return null;
        }

        if (change.getControlNewText().matches("^[1-9]?[0-9]((\\.|\\,)[0-9]{0,2})?$")) {
            change.setText(text.replace(",", "."));
            return change;  // can this be more elegant?
        }


        return null;
    }
}
