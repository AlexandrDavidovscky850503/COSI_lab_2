package sample;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    private int N=16;

    private Map<GridPane, Scene> SceneBuffer =new HashMap<GridPane, Scene>();

    public void start(Stage stage) throws Exception {
        stage.setTitle("COSI_2");

        System.out.println("start");

        AxisGenerator axisGenerator=AxisGenerator.getInstance();

        GeneralFunction generalFunction=new GeneralFunction(axisGenerator.getXAxis().getLowerBound(), axisGenerator.getXAxis().getUpperBound(), 0.01);

        GridPane gridPane=new GridPane();
        gridPane.getColumnConstraints().add(new ColumnConstraints(400));
        gridPane.getColumnConstraints().add(new ColumnConstraints(400));
        gridPane.getColumnConstraints().add(new ColumnConstraints(400));
        gridPane.getRowConstraints().add(new RowConstraints(360));
        gridPane.getRowConstraints().add(new RowConstraints(360));
        gridPane.setGridLinesVisible(true);

        //usual
        gridPane.add(SceneMaker.getMainGridCosPane(axisGenerator.getXAxis(), axisGenerator.getYAxis(), generalFunction),0,0);
        gridPane.setHalignment(SceneMaker.getMainGridCosPane(axisGenerator.getXAxis(), axisGenerator.getYAxis(), generalFunction),HPos.CENTER);
        gridPane.setValignment(SceneMaker.getMainGridCosPane(axisGenerator.getXAxis(), axisGenerator.getYAxis(), generalFunction), VPos.CENTER);

        gridPane.add(SceneMaker.getMainGridSinPane(axisGenerator.getXAxis(), axisGenerator.getYAxis(), generalFunction),0,1);
        gridPane.setHalignment(SceneMaker.getMainGridSinPane(axisGenerator.getXAxis(), axisGenerator.getYAxis(), generalFunction),HPos.CENTER);
        gridPane.setValignment(SceneMaker.getMainGridSinPane(axisGenerator.getXAxis(), axisGenerator.getYAxis(), generalFunction), VPos.CENTER);

        //convertion
        Map<Integer, Double> convolution= FunctionGenerator.getConvolution(N);

        gridPane.add(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), convolution, "Convolution"),1,0);
        gridPane.setHalignment(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), convolution, "Convolution"),HPos.CENTER);
        gridPane.setValignment(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), convolution, "Convolution"), VPos.CENTER);

        //cos
        List<Complex> complexListCos=new ArrayList<>();
        for (int i=0; i<N; ++i){
            complexListCos.add(new Complex(FunctionGenerator.getValueOfMainCosFunction(i)));
        }
        List<Complex> resultFFTCos=FunctionGenerator.doFFT(complexListCos, N, -1);

        System.out.println("FFT cos " + resultFFTCos);

        //sin
        List<Complex> complexListSin=new ArrayList<>();
        for (int i=0; i<N; ++i){
            complexListSin.add(new Complex(FunctionGenerator.getValueOfMainSinFunction(i)));
        }
        List<Complex> resultFFTSin=FunctionGenerator.doFFT(complexListSin, N, -1);
        System.out.println("FFT sin" + resultFFTSin);

        //a*b
        List<Complex> resultOfMulti=new ArrayList<>();
        for (int i=0; i<N; ++i){
            resultOfMulti.add(ComplexLogic.multiplicationOfTwoComplex(resultFFTCos.get(i), resultFFTSin.get(i)));
        }
        System.out.println("mul " + resultOfMulti);

        //FFT reverse
        List<Complex> res=FunctionGenerator.doFFT(resultOfMulti, N, 1);
        System.out.println(res);

        Map<Integer, Double> backFunctionFFT=new HashMap<>();
        for (int i=0; i<N; ++i){
            backFunctionFFT.put(i, res.get(i).getReal()/N/N);
        }

        gridPane.add(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), backFunctionFFT, "Convolution with FFT"),1,1);
        gridPane.setHalignment(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), backFunctionFFT, "Convolution with FFT"), HPos.CENTER);
        gridPane.setValignment(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), backFunctionFFT, "Convolution with FFT"), VPos.CENTER);

        //convertion
        Map<Integer, Double> correlation= FunctionGenerator.getCorrelation(N);
        gridPane.add(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), correlation, "Correlation"),2,0);
        gridPane.setHalignment(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), correlation, "Correlation"),HPos.CENTER);
        gridPane.setValignment(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), correlation, "Correlation"), VPos.CENTER);
        //cos reverse
        List<Complex>  complexConjugateCos=new ArrayList<>();
        for (int i=0; i<N; ++i){
            complexConjugateCos.add(ComplexLogic.getComplexConjugate(resultFFTCos.get(i)));
        }
        System.out.println(complexConjugateCos);

        //a*b
        List<Complex> resultOfMulti2=new ArrayList<>();
        for (int i=0; i<N; ++i){
            resultOfMulti2.add(ComplexLogic.multiplicationOfTwoComplex(complexConjugateCos.get(i), resultFFTSin.get(i)));
        }
        System.out.println(resultOfMulti2);

        //FFT reverse
        List<Complex> res2=FunctionGenerator.doFFT(resultOfMulti2, N, 1);
        System.out.println(res);

        Map<Integer, Double> backFunctionFFT2=new HashMap<>();
        for (int i=0; i<N; ++i){
            backFunctionFFT2.put(i, res2.get(i).getReal()/N/N);
        }

        gridPane.add(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), backFunctionFFT2, "сorrelation with FFT"),2,1);
        gridPane.setHalignment(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), backFunctionFFT2, "сorrelation with FFT"),HPos.CENTER);
        gridPane.setValignment(SceneMaker.getGridPaneFromMap(axisGenerator.getCustomXAxis(0, N-1), axisGenerator.getCustomYAxis(-1, 1), backFunctionFFT2, "сorrelation with FFT"), VPos.CENTER);


        System.out.println("finish");
        Scene scene = new Scene(gridPane, 1200, 720);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
