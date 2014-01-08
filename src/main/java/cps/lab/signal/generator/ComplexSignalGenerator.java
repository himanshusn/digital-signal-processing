package cps.lab.signal.generator;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;
import cps.lab.signal.operation.base.Operation;

/**
 * User: maciek
 * Date: 18.11.13
 * Time: 09:20
 */
public class ComplexSignalGenerator implements SignalGenerator{

    private Signal signal0;
    private Signal signal1;
    private Operation signalOperation;

    public ComplexSignalGenerator(Signal signal0, Signal signal1, Operation signalOperation) {
        this.signal0 = signal0;
        this.signal1 = signal1;
        this.signalOperation = signalOperation;
    }

    @Override
    public Signal generateSignal(double frequency) {
        Signal newSignal0 = signal0.getSignalGenerator().generateSignal(frequency);
        Signal newSignal1 = signal1.getSignalGenerator().generateSignal(frequency);

        try {
            return signalOperation.compute(newSignal0,newSignal1);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    @Override
    public Signal generateSamples(double frequency) {
        Signal newSignal0 = signal0.getSignalGenerator().generateSamples(frequency);
        Signal newSignal1 = signal1.getSignalGenerator().generateSamples(frequency);

        try {
            return signalOperation.compute(newSignal0, newSignal1);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
