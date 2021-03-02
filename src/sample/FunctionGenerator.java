package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionGenerator {
    private FunctionGenerator() {
    }

    public static double getValueOfMainCosFunction(double x) {
        return (Math.cos(x));
    }

    public static double getValueOfMainSinFunction(double x) {
        return (Math.sin(3 * x));
    }

    public static Map<Integer, Double> getConvolution(int N){
        Map<Integer, Double> result=new HashMap<Integer, Double>();

        int m=N-1;

        for (int i=0; i<=m; ++i){
            double amount=0;
            for (int j=0; j<N; ++j){
                amount+=getValueOfMainCosFunction(j)*getValueOfMainSinFunction(Math.abs(i-j));
            }
            amount/=N;
            result.put(i, amount);
        }

        return result;
    }

    public static Map<Integer, Double> getCorrelation(int N){
        Map<Integer, Double> result=new HashMap<Integer, Double>();

        int m=N-1;

        for (int i=0; i<=m; ++i){
            double amount=0;
            for (int j=0; j<N; ++j){
                amount+=getValueOfMainCosFunction(j)*getValueOfMainSinFunction(Math.abs((i+j)%N));
            }
            amount/=N;
            result.put(i, amount);
        }

        return result;
    }

    public static List<Complex> doFFT(List<Complex> a, int N, double dir){

        if(a.size()==1){
            return a;
        }

        List<Complex> aEven=new ArrayList<>();
        List<Complex> aOdd=new ArrayList<>();

        for(int i=0; i<a.size(); ++i){
            if(i%2==0){
                aEven.add(a.get(i));
            }else{
                aOdd.add(a.get(i));
            }
        }

        List<Complex> bEven= doFFT(aEven,N/2, dir);
        List<Complex> bOdd= doFFT(aOdd,N/2, dir);

        Complex mainRootN=new Complex(Math.cos(2*Math.PI/N), dir*Math.sin(2*Math.PI/N));
        Complex temp=new Complex(1,0);

        List<Complex> result=new ArrayList<>(N);

        for(int i=0; i<N; ++i){
            result.add(new Complex());
        }

        for (int j=0; j<N/2; ++j){
            result.set(j, ComplexLogic.amountOfTwoComplex(bEven.get(j), ComplexLogic.multiplicationOfTwoComplex(temp, bOdd.get(j))));
            result.set(j+N/2, ComplexLogic.subtractionOfTwoComplex(bEven.get(j), ComplexLogic.multiplicationOfTwoComplex(temp, bOdd.get(j))));
            temp=ComplexLogic.multiplicationOfTwoComplex(temp, mainRootN);
        }

        return result;
    }
}
