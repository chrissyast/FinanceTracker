import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Deduction incomeTax = new Deduction();
        ThresholdCriterion interestFree = new ThresholdCriterion(10000,0);
        incomeTax.add(interestFree);
    }
}
