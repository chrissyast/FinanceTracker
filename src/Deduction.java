import java.util.ArrayList;

public class Deduction extends ArrayList {

    private ArrayList<ThresholdCriterion> criteria;

    public Deduction(){

    }

    public Deduction(ArrayList<ThresholdCriterion> criteria) {
        this.criteria = criteria;
    }


}
