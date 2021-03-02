package sample;

public class GeneralFunction {

    private double minX;
    private double maxX;
    private double step;

    public GeneralFunction(double minX, double maxX, double step) {
        this.minX = minX;
        this.maxX = maxX;
        this.step = step;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getStep() {
        return step;
    }
}
