package cps.lab.gui.panels;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JPanel;

public class CharPanelReal extends JPanel {

    private static final long serialVersionUID = -1323139808447372725L;
    private JPanel chartPanel;

	public CharPanelReal(JPanel chartPanel){
		super();
		this.add(chartPanel, BorderLayout.CENTER);
	}

	public CharPanelReal() {
	}

	public JPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(JPanel chartPanel) {
		if (this.chartPanel != null) {
			this.remove(this.chartPanel);
		}
		this.chartPanel = chartPanel;
		this.add(this.chartPanel, BorderLayout.CENTER);
	}

}
