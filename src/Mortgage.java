import java.time.LocalDate;

public class Mortgage {

    double initialPrincipal;
    double interestRate;
    double monthlyPayment;
    double balance;
    int remainingMonths;
    double mthIntPct;
    OverpaymentAction action;


    public Mortgage(double initialPrincipal, double interestRate, double balance, int remainingMonths, OverpaymentAction action) {
        this.initialPrincipal = initialPrincipal;
        this.interestRate = interestRate;
        this.balance = balance;
        this.remainingMonths = remainingMonths;
        this.mthIntPct = interestRate/100/12;
        this.monthlyPayment = monthlyPayment();
        this.action = action;
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }

    public double monthlyPayment(){

        return (mthIntPct/(1-(Math.pow((1+mthIntPct),-remainingMonths)))*balance);
    }

    public void recalculateMonthsRegularOverpayment(double overpayment){
        LocalDate futureDate = getDate().plusMonths(monthsRemaining(overpayment));
        System.out.println("You will finish the mortgage on " + futureDate + ", " + monthsSaved(overpayment) + " months earlier than before");
    }

    public int monthsRemaining(){
        return monthsRemaining(0);
    }

    private int monthsRemaining(double overpayment){
        double top = Math.log(-((mthIntPct*this.balance/(this.monthlyPayment()+overpayment))-1));
        double bottom = Math.log(1+mthIntPct);
        return new Double(Math.ceil(-top/bottom)).intValue();
    }

    public int monthsSaved(double overpayment){
        return monthsRemaining() - monthsRemaining(overpayment);
            }


    public void recalculateMonths(){
        double top = Math.log(-((mthIntPct*balance/this.monthlyPayment)-1));
        double bottom = Math.log(1+mthIntPct);
        this.remainingMonths = new Double(Math.ceil(-top/bottom)).intValue();
    }

    public double totalToPayOverLifetime() {
        return this.monthlyPayment * this.remainingMonths;
    }

    public void makeOneOffPayment(double payment) {
        this.balance -= payment;
        recalculate();
    }

    public void recalculate() {
        if (this.action == OverpaymentAction.REDUCE_PAYMENT) {
            recalculateMonthlyPayment();
        } else {
            recalculateMonths();
        }
    }


    public void makeMonthlyPayment(){
        makeMonthlyPayment(0);
    }

    public void makeMonthlyPayment(double overpayment) {
        double totalPayment = monthlyPayment + overpayment;
        if (balance > totalPayment) {
            balance = balance - totalPayment;
            remainingMonths--;
        }
        else balance = 0;

        if (balance == 0) {
            remainingMonths = 0;
            return;
        }

        if (overpayment > 0) {
            recalculate();
        }

    }

    public void makeMonthlyPayment(double overpayment, OverpaymentAction action) {
        this.action = action;
        makeMonthlyPayment(overpayment);
    }

    public void recalculateMonthlyPayment() {
        this.monthlyPayment = monthlyPayment();
    }

}
