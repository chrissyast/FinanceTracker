import java.time.LocalDate;

public class Mortgage {

    double initialPrincipal;
    double interestRate;
    double balance;
    int remainingMonths;
    double mthIntPct;
    OverpaymentAction action = OverpaymentAction.REDUCE_TERM;


    public Mortgage(double initialPrincipal, double interestRate, double balance, int remainingMonths) {
        this.initialPrincipal = initialPrincipal;
        this.interestRate = interestRate;
        this.balance = balance;
        this.remainingMonths = remainingMonths;
        this.mthIntPct = interestRate/100/12;
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }

    public double monthlyPayment(){

        return (mthIntPct/(1-(Math.pow((1+mthIntPct),-remainingMonths)))*balance);
    }

    public void recalculateMonthsRegularOverpayment(double overpayment){

        //return new Double(Math.ceil(-top/bottom)).intValue();

        LocalDate futureDate = getDate().plusMonths(monthsRemaining(overpayment));
        System.out.println("You will finish the mortgage on " + futureDate + ", " + monthsSaved(overpayment) + " months earlier than before");
    }

    public int monthsRemaining(){
        return monthsRemaining(0);
    }

    public int monthsRemaining(double overpayment){
        double top = Math.log(-((mthIntPct*this.balance/(this.monthlyPayment()+overpayment))-1));
        double bottom = Math.log(1+mthIntPct);
        return new Double(Math.ceil(-top/bottom)).intValue();
    }

    public int monthsSaved(double overpayment){
        return monthsRemaining() - monthsRemaining(overpayment);
            }


    public int recalculateMonths(double overpayment){
        double newBalance = balance - overpayment;

        double top = Math.log(-((mthIntPct*newBalance/this.monthlyPayment())-1));
        double bottom = Math.log(1+mthIntPct);

        return new Double(Math.ceil(-top/bottom)).intValue();

    }


    public void makeMonthlyPayment(){
        makeMonthlyPayment(0);
    }

    public void makeMonthlyPayment(double overpayment) {
        double totalPayment = monthlyPayment() + overpayment;
        if (balance > totalPayment) {
            balance = balance - totalPayment;
        }
        else balance = 0;

        if (overpayment > 0) {
            if (action == OverpaymentAction.REDUCE_PAYMENT){
                remainingMonths--;
                recalculateMonthlyPayment(overpayment);

            }
            else {recalculateMonths(overpayment);
                //remainingMonths--;
                }
        }
        else {
            remainingMonths--;
        }

    }

    public void makeMonthlyPayment(double overpayment, OverpaymentAction action) {
        this.action = action;
        makeMonthlyPayment(overpayment);
    }

    public double recalculateMonthlyPayment(double overpayment) {
        double newBalance = balance - overpayment;
        Mortgage newMortgage = new Mortgage(this.initialPrincipal,this.interestRate,newBalance,this.remainingMonths-1);
        return newMortgage.monthlyPayment();
    }

}
