example logic

```
        LocalDate firstDayOfCurrentMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = LocalDate.parse("2040-08-01");
        Period period = Period.between(firstDayOfCurrentMonth, endDate);
        int months = period.getYears() * 12 + period.getMonths();
      //  System.out.println(months);
        Mortgage nationwide = new Mortgage(94500, 3.59, 80761.88, months, OverpaymentAction.REDUCE_PAYMENT);
        double previousMP = nationwide.monthlyPayment;
        System.out.println(previousMP);
        double previousLifetime = nationwide.totalToPayOverLifetime();
        System.out.println(previousLifetime);
        double overpayment = 3000;
        nationwide.makeOneOffPayment(overpayment);
        double newMP = nationwide.monthlyPayment;
        double newLifeTime = overpayment + nationwide.totalToPayOverLifetime();
        System.out.println(newLifeTime);
        System.out.println("Lifetime diff:" + (previousLifetime - newLifeTime));
        System.out.println((previousLifetime - newLifeTime) / overpayment * 100);
        System.out.println("Old payment " + previousMP + ", new payment " + newMP);
        System.out.println("Diff:" + (previousMP - newMP));
        double monthsToGetBenefit = overpayment / (previousMP - newMP);
        System.out.println(monthsToGetBenefit);
        System.out.println(monthsToGetBenefit / months);



      /*  System.out.println(nationwide.monthsSaved(100));
        while (nationwide.balance > 0) {
            System.out.println(nationwide.monthlyPayment);
            nationwide.makeMonthlyPayment(250);
            System.out.println(nationwide.remainingMonths);
        }
*/
        /*
    Deduction.Threshold lowerLimit = new Deduction.Threshold(12500,20);
    Deduction.Threshold upperLimit = new Deduction.Threshold(40000, 40);
    List<Deduction.Threshold> incomeTaxThresholds = new ArrayList<>();
    incomeTaxThresholds.add(upperLimit);
    incomeTaxThresholds.add(lowerLimit);
    Deduction incomeTax = new Deduction("Income Tax", incomeTaxThresholds);
    List<Deduction.Threshold> nationalInsuranceThresholds = new ArrayList<>();
    Deduction.Threshold niThreshold1 = new Deduction.Threshold(8632,12);
    nationalInsuranceThresholds.add(niThreshold1);
    Deduction nationalInsurance = new Deduction("National Insurance", nationalInsuranceThresholds);
    List<Deduction> deductions = new ArrayList<>();
    deductions.add(incomeTax);
    deductions.add(nationalInsurance);

    Income ukIncome = new Income(13345.76, deductions, time.Period.PeriodType.YEAR);
    System.out.println("Total after deductions " + ukIncome.totalAfterDeductions(time.Period.PeriodType.YEAR));

         */
```