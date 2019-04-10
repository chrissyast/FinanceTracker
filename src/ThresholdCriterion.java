public class ThresholdCriterion {

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    private double threshold;
    private double percentage;

    public ThresholdCriterion(double threshold, double percentage) {
        this.threshold = threshold;
        this.percentage = percentage;
    }


}
