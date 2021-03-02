package sample;
import javafx.scene.chart.NumberAxis;

public class AxisGenerator {
    private static final AxisGenerator instance=new AxisGenerator();

    private NumberAxis xAxis; //ось Х
    private NumberAxis yAxis; //ось Y

    private AxisGenerator(){
        xAxis=new NumberAxis();
        yAxis=new NumberAxis();

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0); //минимальное значение
        xAxis.setUpperBound(10); // максимальное значение
        xAxis.setTickUnit(0.5); //шаг

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(-1.5);
        yAxis.setUpperBound(1.5);
        yAxis.setTickUnit(0.5);
    }

    public static AxisGenerator getInstance() {
        return instance;
    }

    public NumberAxis getXAxis(){
        return getNumberAxis(xAxis);
    }

    public NumberAxis getYAxis(){
        return getNumberAxis(yAxis);
    }

    private NumberAxis getNumberAxis(NumberAxis modelAxis) {
        NumberAxis numberAxis=new NumberAxis();
        numberAxis.setAutoRanging(modelAxis.isAutoRanging());
        numberAxis.setLowerBound(modelAxis.getLowerBound());
        numberAxis.setUpperBound(modelAxis.getUpperBound());
        numberAxis.setTickUnit(modelAxis.getTickUnit());

        return numberAxis;
    }

    public NumberAxis getCustomXAxis(double min, double max){
        return getCustomNumberAxis(min, max, xAxis);
    }

    public NumberAxis getCustomYAxis(double min, double max){
        return getCustomNumberAxis(min, max, yAxis);
    }
    private NumberAxis getCustomNumberAxis(double min, double max, NumberAxis xAxis) {
        NumberAxis numberAxis=new NumberAxis();
        numberAxis.setAutoRanging(xAxis.isAutoRanging());
        numberAxis.setLowerBound(min);
        numberAxis.setUpperBound(max);
        numberAxis.setTickUnit(xAxis.getTickUnit());

        return numberAxis;
    }


}
