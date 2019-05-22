public class Main {

    public static void main(String[] args) {

   Mortgage nationwide = new Mortgage(94500,2.39,82786.07,255);
    int i = 1;
    double savings = 0;
   while (nationwide.balance > 0) {
       nationwide.makeMonthlyPayment();
       savings = savings + (413 - nationwide.monthlyPayment());
       System.out.println("Month " + i + " " + nationwide.monthlyPayment() + ". Total savings: " + savings);

       i++;
   }


    }
}
