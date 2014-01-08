package cps.lab.signal.operation;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Operation;

import java.util.Collections;

/**
 * User: maciek
 * Date: 11.11.13
 * Time: 16:52
 */
public class CorrelationThroughConvolution extends Operation {

    @Override
    public Signal compute(Signal signal0, Signal signal1) throws Exception {
        Collections.reverse(signal1.getValues());
        return new Convolution().compute(signal0, signal1);
    }

}
