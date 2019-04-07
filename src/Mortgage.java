public class Mortgage {

    double initialPrincipal;
    double interestRate;
    double balance;
    int remainingMonths;
    double mthIntPct;


    public Mortgage(double initialPrincipal, double interestRate, double balance, int remainingMonths) {
        this.initialPrincipal = initialPrincipal;
        this.interestRate = interestRate;
        this.balance = balance;
        this.remainingMonths = remainingMonths;
        this.mthIntPct = interestRate/100/12;
    }




    public double monthlyPayment(){

        return (mthIntPct/(1-(Math.pow((1+mthIntPct),-remainingMonths)))*balance);
    }


    public int recalculateMonths(double overpayment){
        double newBalance = balance - overpayment;

        double top = Math.log(-((mthIntPct*newBalance/this.monthlyPayment())-1));
        double bottom = Math.log(1+mthIntPct);

        return new Double(Math.ceil(-top/bottom)).intValue();

    }

}
