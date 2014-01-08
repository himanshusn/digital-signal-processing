package cps.lab;

import java.util.Collections;
import java.util.List;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.Correlation;
import cps.lab.utils.IOUtils;


public class RadarTest {

    double signalVelocity;
    double signalSamplingFrequency;
    double signalOscilationTime;


    int buforSize;

    double raportingTime;

//
//    @Before
//    public void prepare() {

    static Signal signal = IOUtils.loadSignal("D:/Workspaces/Standart Eclipse/cyfrowe_przetwarzanie_sygnalu/src/test/resources/fajny_sin");

    static double objectStartDistance = 1;    //  m
    static double    objectVelocity = 2;         //  m/s

//    }

    
    public static void main(String args[]) throws Exception {

        double deltat = objectStartDistance * signal.getFrequency() / objectVelocity;
        int phase = (int) (deltat * signal.getFrequency());

        System.out.println("objectStartDistance: " + objectStartDistance + " objectVelocity: " + objectVelocity + " phase: " + phase + " deltat: " + deltat);
        System.out.println(signal.getValues().size());

        Signal signalOut = makePhase(signal, 0);
        Signal signalIn = makePhase(signal, phase);

//        System.out.println("out: " + signalOut.getValues());
//        System.out.println(signalOut.getSignalTime());
//
//        System.out.println("in: " + signalIn.getValues());
//        System.out.println(signalIn.getSignalTime());

        Signal correlation = new Correlation().compute(signalOut, signalIn);


        System.out.println(findDeltaT(correlation));


    }

    public static Signal makePhase(Signal signal, int n) {

        Signal result = new Signal();
        result.setSignalTime(signal.getSignalTime());
        result.setStartTime(signal.getStartTime());
        result.setFrequency(signal.getFrequency());

        List<Double> values = signal.getValues().subList(n, signal.getValues().size() - 1);
        values.addAll(signal.getValues().subList(0, n));
        System.out.println(values);
        result.setValues(values);

        return result;
    }

    public static int findDeltaT(Signal correlation) {

        int middle = correlation.getValues().size() / 2;

        List<Double> halfValues = correlation.getValues().subList(middle + 1, correlation.getValues().size() - 1);

        int deltaSamples = halfValues.indexOf(Collections.max(halfValues));

        return deltaSamples;
    }

}
