import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Mortgage nationwide = new Mortgage(94500,2.39,82988.38,256);

        System.out.println(nationwide.monthlyPayment());

        System.out.println(nationwide.recalculateMonths(10000));
        LocalDate date = LocalDate.now();
        LocalDate futureDate = date.plusMonths(nationwide.recalculateMonths(10000));

        System.out.println("You will finish your mortgage on " + futureDate.toString());
    }
}
