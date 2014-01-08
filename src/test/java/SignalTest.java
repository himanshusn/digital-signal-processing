import com.google.common.primitives.Doubles;
import cps.lab.signal.Signal;
import cps.lab.signal.operation.Convolution;
import cps.lab.signal.operation.Correlation;
import cps.lab.signal.operation.CorrelationThroughConvolution;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class SignalTest {

    Signal signalA = new Signal();
    double[] h;

    Signal signalB = new Signal();
    Signal signalBSmall = new Signal();
    Signal signalBLarge = new Signal();
    double[] x;
    double[] x_small;
    double[] x_large;

    Signal convolutionSignal = new Signal();
    Signal convolutionSignalSmall = new Signal();
    Signal convolutionSignalLarge = new Signal();
    double[] convolution;
    double[] convolution_small;
    double[] convolution_large;

    Signal correlationSignal = new Signal();
    Signal correlationSignalSmall = new Signal();
    Signal correlationSignalLarge = new Signal();
    double[] correlation;
    double[] correlation_small;
    double[] correlation_large;


    @Before
    public void prepare() {

        h = new double[]{1, 2, 4, 8};
        signalA.setValues(Doubles.asList(h));

        x = new double[]{2, 3, 4, 5};
        signalB.setValues(Doubles.asList(x));

        x_small = new double[]{2, 3};
        signalBSmall.setValues(Doubles.asList(x_small));

        x_large = new double[]{2, 3, 4, 5, 6};
        signalBLarge.setValues(Doubles.asList(x_large));

        convolution = new double[]{2, 7, 18, 41, 50, 52, 40};
        convolutionSignal.setValues(Doubles.asList(convolution));

        convolution_small = new double[]{2, 7, 14, 28, 24};
        convolutionSignalSmall.setValues(Doubles.asList(convolution_small));

        convolution_large = new double[]{2, 7, 18, 41, 56, 64, 64, 48};
        convolutionSignalLarge.setValues(Doubles.asList(convolution_large));

        correlation = new double[]{5, 14, 31, 64, 48, 32, 16};
        correlationSignal.setValues(Doubles.asList(correlation));

        correlation_small = new double[]{3, 8, 16, 32, 16};
        correlationSignalSmall.setValues(Doubles.asList(correlation_small));

        correlation_large = new double[]{6, 17, 38, 79, 64, 48, 32, 16};
        correlationSignalLarge.setValues(Doubles.asList(correlation_large));

    }

    @Test
    public void convolution() throws Exception {

        List<Double> conv = new Convolution().compute(signalA, signalB).getValues();

        Assert.assertThat(conv, IsEqual.equalTo(Doubles.asList(convolution)));

        conv = new Convolution().compute(signalA, signalBSmall).getValues();
        Assert.assertThat(conv, IsEqual.equalTo(Doubles.asList(convolution_small)));

        conv = new Convolution().compute(signalA, signalBLarge).getValues();
        Assert.assertThat(conv, IsEqual.equalTo(Doubles.asList(convolution_large)));

    }

    @Test
    public void correlationThroughConvTest() throws Exception {
        List<Double> corr = new CorrelationThroughConvolution().compute(signalA, signalB).getValues();

        Assert.assertThat(corr, IsEqual.equalTo(Doubles.asList(correlation)));
    }

    @Test
    public void correlationThroughConvTestSmall() throws Exception {
        List<Double> corr = new CorrelationThroughConvolution().compute(signalA, signalBSmall).getValues();
        Assert.assertThat(corr, IsEqual.equalTo(Doubles.asList(correlation_small)));
    }

    @Test
    public void correlationThroughConvTestLarge() throws Exception {
        List<Double> corr = new CorrelationThroughConvolution().compute(signalA, signalBLarge).getValues();
        Assert.assertThat(corr, IsEqual.equalTo(Doubles.asList(correlation_large)));
    }

    @Test
    public void correlationTest() throws Exception {
        List<Double> corr = new Correlation().compute(signalA, signalB).getValues();
        Assert.assertThat(corr, IsEqual.equalTo(Doubles.asList(correlation)));

        corr = new Correlation().compute(signalA, signalBSmall).getValues();
        Assert.assertThat(corr, IsEqual.equalTo(Doubles.asList(correlation_small)));

        corr = new Correlation().compute(signalA, signalBLarge).getValues();
        Assert.assertThat(corr, IsEqual.equalTo(Doubles.asList(correlation_large)));
    }

}
