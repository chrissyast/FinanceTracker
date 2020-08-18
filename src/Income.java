import time.Period;

import java.util.List;

public class Income {
    double amount;
    List<Deduction> deductions;
    Period.PeriodType period;

    public Income(double amount, List<Deduction> deductions, Period.PeriodType period) {
        this.amount = amount;
        this.deductions = deductions;
        this.period = period;
    }

    public double totalAfterDeductions(Period.PeriodType period) {
        return round((amount / reportPeriodDivisor(period))  - totalOfAllDeductions(period),2);
    }

    public double round(double number, int dp) {
        return (double) Math.round(number * Math.pow(10,dp)) / Math.pow(10,dp);
    }

    public void addDeduction(Deduction deduction) {
        this.deductions.add(deduction);
    }

    public double reportPeriodDivisor(Period.PeriodType reportPeriod) {
        return (Period.periodDivisor(reportPeriod) / Period.periodDivisor(this.period));
    }

    public double totalOfAllDeductions(Period.PeriodType period) {
        double totalDeductions = 0;
        for (Deduction d : deductions) {
            double deductionAmount = d.totalDeductions(amount / reportPeriodDivisor(period), period);
            totalDeductions += deductionAmount;
            System.out.println(d.name + " " + deductionAmount);
        }
        System.out.println("Total: " + round(totalDeductions,2));
        return totalDeductions;
    }
}
