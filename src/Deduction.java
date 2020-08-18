import time.Period;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deduction {
    public Deduction(List<Threshold> thresholds) {
        Collections.sort(thresholds, (t1, t2) -> t1.amountLimit < t2.amountLimit ? -1 : 1);
        this.thresholds = thresholds;
    }

    public Deduction(String name, List<Threshold> thresholds) {
    Collections.sort(thresholds, (t1, t2) -> t1.amountLimit < t2.amountLimit ? -1 : 1);
    this.thresholds = thresholds;
    this.name = name;
    }

    List<Threshold> thresholds;
    String name;

    public static class Threshold {
        double amountLimit;
        double percentage;

        public Threshold(double amountLimit, double percentage) {
            this.amountLimit = amountLimit;
            this.percentage = percentage;
        }
    }

    public List<Threshold> thresholdsForPeriod(Period.PeriodType period) {
        double divisor = Period.calculationPeriods().get(period);
        List<Threshold> list = thresholds.stream().map(threshold -> new Threshold(threshold.amountLimit / divisor, threshold.percentage)).collect(Collectors.toList());
        return list;
    }

    public double totalDeductions(double income, Period.PeriodType period) {
        double totalDeductions = 0;
        List<Threshold> thresholdsForPeriod = thresholdsForPeriod(period);
        for (int i = 0; i < thresholds.size(); i++) {
            Threshold threshold = thresholdsForPeriod.get(i);
            double lowerLimit = threshold.amountLimit;
            double upperLimit = i < thresholdsForPeriod.size() - 1 ? thresholdsForPeriod.get(i + 1).amountLimit : Double.POSITIVE_INFINITY;
            if (income < lowerLimit) {break;}
            double taxableAmountAtPercentage = Double.min(income, upperLimit) - lowerLimit;
            double amountAtThreshold = (taxableAmountAtPercentage * threshold.percentage / 100);
            totalDeductions += amountAtThreshold;
        }
        return totalDeductions;
    }
}
