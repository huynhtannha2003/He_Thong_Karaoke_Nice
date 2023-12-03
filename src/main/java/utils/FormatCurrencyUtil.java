package utils;

import java.text.NumberFormat;

public class FormatCurrencyUtil {
    public static String formatCurrency(double value) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        return currencyFormatter.format(value);
    }
}
