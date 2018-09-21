package loan;

public class FixedCapitalPaymentLoan extends Loan {

    private double calculateAndGetMonthlyCapitalShare() {
        return loanAmount / durationInMonths;
    }

    @Override
    public double maxMonthlyPayment() {
        return loanAmount * monthlyInterestRate + loanAmount / durationInMonths;
    }

    @Override
    Payment[] payments() {
        totalInterest = 0;
        totalCost = 0;

        final double capitalShare = calculateAndGetMonthlyCapitalShare();
        double currentPayment;
        double currentOutstanding = loanAmount;
        double currentInterestShare;
        Payment[] payments = new Payment[durationInMonths];

        for (int month = 1; month <= durationInMonths; month++) {
            currentInterestShare = currentOutstanding * monthlyInterestRate;
            currentPayment = capitalShare + currentInterestShare;
            payments[month - 1] = new Payment(month, currentOutstanding, currentPayment, currentInterestShare, capitalShare);

            currentOutstanding -= capitalShare;

            totalInterest += currentInterestShare;
            totalCost += currentPayment;
        }

        return payments;
    }
}
