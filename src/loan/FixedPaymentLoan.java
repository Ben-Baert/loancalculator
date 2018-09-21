package loan;

class FixedPaymentLoan extends Loan {

    private double calculateAndGetMonthlyPayment() {
        double payment = loanAmount /
                ((1 - Math.pow(1 + monthlyInterestRate, -durationInMonths)) / monthlyInterestRate);
        return Utils.roundPercentage(payment);
    }

    @Override
    double maxMonthlyPayment() {
        return calculateAndGetMonthlyPayment();
    }

    @Override
    Payment[] payments() {
        totalInterest = 0;
        totalCost = 0;

        final double monthlyPayment = calculateAndGetMonthlyPayment();
        double currentOutstanding = loanAmount;
        double currentInterestShare;
        double currentCapitalShare;
        Payment[] payments = new Payment[durationInMonths];

        for (int month = 1; month <= durationInMonths; month++) {
            currentInterestShare = currentOutstanding * monthlyInterestRate;
            currentCapitalShare = monthlyPayment - currentInterestShare;
            payments[month - 1] = new Payment(month, currentOutstanding, monthlyPayment, currentInterestShare, currentCapitalShare);

            currentOutstanding -= monthlyPayment;

            totalInterest += currentInterestShare;
            totalCost += monthlyPayment;
        }

        return payments;
    }
}
