package cps.lab.gui.buttons;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * User: maciek
 * Date: 18.10.13
 * Time: 23:41
 */
public class IntervalSlider extends JSlider {

    public IntervalSlider(int min, int max, int initial, int minorSpc, int majorSpc) {
        super(JSlider.HORIZONTAL, min, max, initial);
        setMajorTickSpacing(minorSpc);
        setPaintLabels(true);
        setMinorTickSpacing(majorSpc);
        setPaintTicks(true);
    }

}
