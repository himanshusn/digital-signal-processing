package cps.lab.signal.generator.base;

import java.io.Serializable;

import cps.lab.signal.Signal;

public interface SignalGenerator extends Serializable {

    Signal generateSignal(double frequency);

    Signal generateSamples(double frequency);

}
