package loan;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Payment {
    private final SimpleIntegerProperty month;
    private final SimpleDoubleProperty outstanding;
    private final SimpleDoubleProperty payment;
    private final SimpleDoubleProperty interest;
    private final SimpleDoubleProperty capital;

    public Payment(int month, double outstanding, double payment, double interest, double capital) {
        this.month = new SimpleIntegerProperty(month);
        this.outstanding = new SimpleDoubleProperty(outstanding);
        this.payment = new SimpleDoubleProperty(payment);
        this.interest = new SimpleDoubleProperty(interest);
        this.capital = new SimpleDoubleProperty(capital);
    }

    public int getMonth() {
        return month.get();
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public SimpleIntegerProperty monthProperty() {
        return month;
    }

    public double getOutstanding() {
        return outstanding.get();
    }

    public void setOutstanding(double outstanding) {
        this.outstanding.set(outstanding);
    }

    public SimpleDoubleProperty outstandingProperty() {
        return outstanding;
    }

    public double getPayment() {
        return payment.get();
    }

    public void setPayment(double payment) {
        this.payment.set(payment);
    }

    public SimpleDoubleProperty paymentProperty() {
        return payment;
    }

    public double getInterest() {
        return interest.get();
    }

    public void setInterest(double interest) {
        this.interest.set(interest);
    }

    public SimpleDoubleProperty interestProperty() {
        return interest;
    }

    public double getCapital() {
        return capital.get();
    }

    public void setCapital(double capital) {
        this.capital.set(capital);
    }

    public SimpleDoubleProperty capitalProperty() {
        return capital;
    }
}
