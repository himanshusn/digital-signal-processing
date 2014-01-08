package cps.lab.gui.menu;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.Correlation;
import cps.lab.utils.IOUtils;
import cps.lab.utils.SignalUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RadarTest {

    class Radar {
        double signalVelocity;
        double samplingFrequency;
        int buforSize;
        double oscilationTime;
        double raportTime;

    }
    class TrackingObject {
        double velocity;
        double currentDistance;

    }
    Radar radar;

    TrackingObject object;

    double oscilationNumber;

    Signal signal;

    public void prepare() {

        signal = IOUtils.loadSignal("D:/Workspaces/Standart Eclipse/cyfrowe_przetwarzanie_sygnalu/src/test/resources/fajny_sin");

        radar = new Radar();
        object = new TrackingObject();

        object.currentDistance = 1.5;
        object.velocity = 1;

        radar.raportTime = 1;
        radar.signalVelocity = 3;

        oscilationNumber = 3;

    }

    public void radarTest() throws Exception {

        prepare();

        signal = SignalUtils.extendSignalDuration(signal, 5);

        System.out.println("Object velocity: " + object.velocity);
        System.out.println("Bufor size: " + signal.getValues().size());

        for (int i = 0; i < oscilationNumber * radar.raportTime; i += radar.raportTime) {

            object.currentDistance += i * object.velocity;

            double realDeltaTime = 2d * object.currentDistance / radar.signalVelocity;
            int phase = (int) (realDeltaTime * signal.getFrequency());

            System.out.println("Real object distance: " + object.currentDistance
                    + "\nPhase: " + phase + "\nDelta time: " + realDeltaTime);

            Signal signalOut = SignalUtils.makePhase(signal, 0);
            Signal signalIn = SignalUtils.makePhase(signal, phase);

            Signal correlation = new Correlation().compute(signalOut, signalIn);

            double trackingDistance = (radar.signalVelocity * findDeltaT(correlation) / signalOut.getFrequency()) / 2d;
            System.out.println("Delta: " + findDeltaT(correlation) + "\nTracking distance: " + trackingDistance  + "\n");

        }

    }

    public int findDeltaT(Signal correlation) {

        int middle = correlation.getValues().size() / 2;

        List<Double> halfValues = correlation.getValues().subList(middle, correlation.getValues().size() - 1);

        int deltaSamples = halfValues.indexOf(Collections.max(halfValues));
        System.out.println(halfValues.indexOf(Collections.max(halfValues)));

        return deltaSamples;
    }

}
