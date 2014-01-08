package cps.lab.fillter.windows;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

/**
 * User: maciek
 * Date: 11.11.13
 * Time: 02:06
 */
public class BlackmanWindow implements WindowFunction {

    @Override
    public double value(int n, int M) {

        double x = 2 * PI * n / M;

        return 0.42 - 0.5 * cos(x) + 0.08 * cos(2 * x);

    }
}
