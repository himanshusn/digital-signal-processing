package cps.lab.gui.windows;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import cps.lab.gui.buttons.ChoosePathButton;
import cps.lab.gui.buttons.SignalOperationButton;

public class SignalOperationWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4213303730392151742L;
	
	private JTextField signalPath0;
    private JTextField signalPath1;

    public SignalOperationWindow() {

        setBounds(50, 100, 500, 300);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        signalPath0 = new JTextField();
        GridBagConstraints gbc_signalPath0 = new GridBagConstraints();
        gbc_signalPath0.insets = new Insets(0, 0, 5, 0);
        gbc_signalPath0.fill = GridBagConstraints.HORIZONTAL;
        gbc_signalPath0.gridx = 4;
        gbc_signalPath0.gridy = 1;
        getContentPane().add(signalPath0, gbc_signalPath0);
        signalPath0.setColumns(10);

        signalPath1 = new JTextField();
        GridBagConstraints gbc_signalPath1 = new GridBagConstraints();
        gbc_signalPath1.insets = new Insets(0, 0, 5, 0);
        gbc_signalPath1.fill = GridBagConstraints.HORIZONTAL;
        gbc_signalPath1.gridx = 4;
        gbc_signalPath1.gridy = 3;
        getContentPane().add(signalPath1, gbc_signalPath1);
        signalPath1.setColumns(10);

        JButton choosePathButton0 = new ChoosePathButton("Choose path", signalPath0);
        GridBagConstraints gbc_choosePathButton0 = new GridBagConstraints();
        gbc_choosePathButton0.insets = new Insets(0, 0, 5, 5);
        gbc_choosePathButton0.gridx = 1;
        gbc_choosePathButton0.gridy = 1;
        getContentPane().add(choosePathButton0, gbc_choosePathButton0);

        JButton choosePathButton1 = new ChoosePathButton("Choose path", signalPath1);
        GridBagConstraints gbc_choosePathButton1 = new GridBagConstraints();
        gbc_choosePathButton1.insets = new Insets(0, 0, 5, 5);
        gbc_choosePathButton1.gridx = 1;
        gbc_choosePathButton1.gridy = 3;
        getContentPane().add(choosePathButton1, gbc_choosePathButton1);


        JComboBox operationTypeCB = new JComboBox(new String[]{"Add", "Sub", "Multiply", "Divide", "Convolution",
                "Correlation", "Correlation through convolution"});
        GridBagConstraints gbc_operationTypeCB = new GridBagConstraints();
        gbc_operationTypeCB.insets = new Insets(0, 0, 5, 0);
        gbc_operationTypeCB.fill = GridBagConstraints.HORIZONTAL;
        gbc_operationTypeCB.gridx = 4;
        gbc_operationTypeCB.gridy = 5;
        getContentPane().add(operationTypeCB, gbc_operationTypeCB);

        JButton computeButton = new SignalOperationButton("Compute", operationTypeCB, signalPath0, signalPath1);
        GridBagConstraints gbc_computeButton = new GridBagConstraints();
        gbc_computeButton.insets = new Insets(0, 0, 0, 5);
        gbc_computeButton.gridx = 1;
        gbc_computeButton.gridy = 7;
        getContentPane().add(computeButton, gbc_computeButton);
    }

}
