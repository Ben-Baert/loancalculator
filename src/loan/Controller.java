package loan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller {
    private double loanAmount;
    private int durationInYears;
    private int durationInMonths;
    private double yearlyInterestRate;
    private double monthlyInterestRate;

    private Loan loan;
    private ObservableList<Payment> data;

    @FXML
    private Label labelLoanAmount;

    @FXML
    private TextField textFieldLoanAmount;

    @FXML
    private Label labelYearlyInterestRate;

    @FXML
    private TextField textFieldYearlyInterestRate;

    @FXML
    private Label labelMonthlyInterestRate;

    @FXML
    private TextField textFieldMonthlyInterestRate;

    @FXML
    private Label labelDuration;

    @FXML
    private Slider sliderDuration;

    @FXML
    private Label labelMonthlyPayment;

    @FXML
    private Label labelMonthlyPaymentAmount;

    @FXML
    private Label labelNrOfYearsAmount;


    @FXML
    private Label labelNrOfPaymentsAmount;

    @FXML
    private ComboBox<String> dropdownType;

    @FXML
    private TableView<Payment> table = new TableView<>();

    @FXML
    private TableColumn<Payment, Integer> columnMonth;

    @FXML
    private TableColumn<Payment, Double> columnOutstanding;

    @FXML
    private TableColumn<Payment, Double> columnPayment;

    @FXML
    private TableColumn<Payment, Double> columnInterest;

    @FXML
    private TableColumn<Payment, Double> columnCapital;

    @FXML
    private Label labelTotalInterest;

    @FXML
    private Label labelTotalInterestAmount;

    @FXML
    private Label labelTotalCost;

    @FXML
    private Label labelTotalCostAmount;


    private TextFormatter<String> yearlyInterestRateFormatter = new TextFormatter<>(DoubleFilter.getInstance());
    private TextFormatter<String> monthlyInterestRateFormatter = new TextFormatter<>(DoubleFilter.getInstance());

    @FXML
    public void initialize() {
        dropdownType.getSelectionModel().selectFirst();
        durationInMonths = 60;
        loan = new FixedPaymentLoan();
        loan.setDurationInMonths(60);
        textFieldYearlyInterestRate.setTextFormatter(yearlyInterestRateFormatter);
        textFieldMonthlyInterestRate.setTextFormatter(monthlyInterestRateFormatter);
        sliderDuration.valueProperty().addListener((obs, oldval, newVal) ->
                sliderDuration.setValue(newVal.intValue()));

        data = FXCollections.observableArrayList();

        table.setItems(data);

        columnMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        columnOutstanding.setCellValueFactory(new PropertyValueFactory<>("outstanding"));
        columnOutstanding.setCellFactory(tc -> new FormattedDoubleCell());
        columnPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        columnInterest.setCellValueFactory(new PropertyValueFactory<>("interest"));
        columnCapital.setCellValueFactory(new PropertyValueFactory<>("capital"));

    }

    @FXML
    private void onLoanAmountChanged() {
        String loanAmountString = textFieldLoanAmount.getText();
        if (loanAmountString != null && !loanAmountString.isEmpty()) {
            loanAmount = Double.parseDouble(loanAmountString);
            loan.setLoanAmount(loanAmount);
        }
        updateResults();
    }

    @FXML
    private void onDurationChanged() {
        durationInYears = (int) Math.round(sliderDuration.getValue());
        durationInMonths = durationInYears * 12;

        labelNrOfYearsAmount.setText(String.format("%d", durationInYears));
        labelNrOfPaymentsAmount.setText(String.format("%d", durationInMonths));

        loan.setDurationInMonths(durationInMonths);

        updateResults();
    }

    @FXML
    private void onTypeChanged() {
        String loanTypeString = dropdownType.getValue();
        switch (loanTypeString) {
            case "Fixed payment":
                loan = new FixedPaymentLoan();
                labelMonthlyPayment.setText("Monthly payment");
                break;
            case "Fixed capital payment":
                loan = new FixedCapitalPaymentLoan();
                labelMonthlyPayment.setText("Maximum monthly payment");
                break;
            default:
                throw new UnsupportedOperationException("Not yet supported!");
        }

        loan.setLoanAmount(loanAmount);
        loan.setMonthlyInterestRate(monthlyInterestRate);
        loan.setDurationInMonths(durationInMonths);

        updateResults();
    }

    @FXML
    private void onYearlyInterestRateChanged() {
        String yearlyInterestRateString = textFieldYearlyInterestRate.getText();
        System.out.println(yearlyInterestRateString);
        if (yearlyInterestRateString == null || yearlyInterestRateString.isEmpty()) {
            return;
        }
        yearlyInterestRate = Double.parseDouble(yearlyInterestRateString) / 100;
        calculateAndSetMonthlyInterestRate();
        updateResults();
    }

    @FXML
    private void onMonthlyInterestRateChanged() {
        String monthlyInterestRateString = textFieldMonthlyInterestRate.getText();
        if (monthlyInterestRateString == null || monthlyInterestRateString.isEmpty()) {
            return;
        }
        monthlyInterestRate = Double.parseDouble(monthlyInterestRateString) / 100;
        calculateAndSetYearlyInterestRate();
        updateResults();
    }

    private void calculateAndSetMonthlyInterestRate() {

        monthlyInterestRate = Utils.roundPercentage(Math.pow(1.0 + yearlyInterestRate, 1.0 / 12.0) - 1);

        System.out.println(monthlyInterestRate);
        textFieldMonthlyInterestRate.setText(String.format("%1$,.2f", monthlyInterestRate * 100));
        loan.setMonthlyInterestRate(monthlyInterestRate);
    }

    private void calculateAndSetYearlyInterestRate() {

        yearlyInterestRate = Math.pow(1.0 + monthlyInterestRate, 12) - 1;
        textFieldYearlyInterestRate.setText(String.format("%1$,.2f", yearlyInterestRate * 100));
    }

    @FXML
    private void updateResults() {
        data.clear();

        labelMonthlyPaymentAmount.setText(String.format("%1$,.2f", loan.maxMonthlyPayment()));

        Payment[] payments = loan.payments();
        if (payments != null) {
            data.addAll(payments);

            System.out.println(loan.getTotalCost());

            labelTotalInterestAmount.setText(String.format("%1$,.2f", loan.getTotalInterest()));
            labelTotalCostAmount.setText(String.format("%1$,.2f", loan.getTotalCost()));
        }
    }
}
