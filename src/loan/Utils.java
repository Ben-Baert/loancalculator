package loan;

import java.text.DecimalFormat;

class Utils {

    static double roundPercentage(double value) {
        DecimalFormat bankRounding = new DecimalFormat("#.####");
        return Double.valueOf(bankRounding.format(value));
    }

    static double roundNumber(double value) {
        DecimalFormat bankRounding = new DecimalFormat("#.##");
        return Double.valueOf(bankRounding.format(value));
    }
}
