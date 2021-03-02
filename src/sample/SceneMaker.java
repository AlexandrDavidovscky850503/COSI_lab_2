package sample;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Map;


public class SceneMaker { // графики
    private SceneMaker(){
    }

    public static LineChart getMainGridCosPane(NumberAxis xAxis, NumberAxis yAxis, GeneralFunction generalFunction){ // cos
        SceneMaker sc = new SceneMaker();
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setCreateSymbols(false);
        XYChart.Series mainSeries= SeriesGenerator.mainCosFunction(generalFunction, "z=cox(x)");

        lineChart.getData().add(mainSeries);
        lineChart.setMaxSize(300,300);
        return lineChart;
    }

    public static LineChart getMainGridSinPane(NumberAxis xAxis, NumberAxis yAxis, GeneralFunction generalFunction){ // sin
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        XYChart.Series mainSeries= SeriesGenerator.mainSinFunction(generalFunction, "y=sin(3x)");
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(mainSeries);
        lineChart.setMaxSize(300,300);
        return lineChart;
    }

    public static LineChart getGridPaneFromMap(NumberAxis xAxis, NumberAxis yAxis, Map<Integer, Double> map, String name){ // остальные графики
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        XYChart.Series mainSeries=SeriesGenerator.seriesFromMapWithOutNulling(map, name);
        lineChart.setCreateSymbols(false);
        lineChart.setMaxSize(300,300);
        lineChart.getData().add(mainSeries);
        return lineChart;
    }
}
