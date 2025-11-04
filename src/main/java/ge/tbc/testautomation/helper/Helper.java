package ge.tbc.testautomation.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class Helper {
    public static String calculateMonthlyContribution(String incomeStr) {
        if (incomeStr == null || incomeStr.trim().isEmpty()) {
            return "0₾";
        }

        // keep digits, dot and minus (remove commas, currency symbols, spaces)
        String cleaned = incomeStr.replaceAll("[^\\d.\\-]", "");
        if (cleaned.isEmpty()) {
            return "0₾";
        }

        BigDecimal income;
        try {
            income = new BigDecimal(cleaned);
        } catch (NumberFormatException e) {
            return "0₾";
        }

        BigDecimal threshold = new BigDecimal("1500");
        BigDecimal rate = income.compareTo(threshold) < 0 ? new BigDecimal("0.25") : new BigDecimal("0.50");
        BigDecimal contribution = income.multiply(rate).setScale(2, RoundingMode.HALF_UP);

        // choose pattern: no decimals when whole, otherwise two decimals
        boolean isWhole = contribution.stripTrailingZeros().scale() <= 0;
        String pattern = isWhole ? "#,##0" : "#,##0.00";

        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
        df.applyPattern(pattern);

        return df.format(contribution) + "₾";
    }
}
