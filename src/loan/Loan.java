package loan;

abstract public class Loan {
    double loanAmount;
    double monthlyInterestRate;
    int durationInMonths;

    double totalInterest;
    double totalCost;

    public Loan() {
    }

    public Loan(double loanAmount, double monthlyInterestRate, int durationInMonths) {
        this.loanAmount = loanAmount;
        this.monthlyInterestRate = monthlyInterestRate;
        this.durationInMonths = durationInMonths;
    }

    double getLoanAmount() {
        return loanAmount;
    }

    void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    void setMonthlyInterestRate(double monthlyInterestRate) {
        this.monthlyInterestRate = monthlyInterestRate;
    }

    int getDurationInMonths() {
        return durationInMonths;
    }

    void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    double getTotalInterest() {
        return totalInterest;
    }

    double getTotalCost() {
        return totalCost;
    }

    abstract double maxMonthlyPayment();

    abstract Payment[] payments();
}
