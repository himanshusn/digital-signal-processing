package cps.lab.gui.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import cps.lab.gui.buttons.IntervalSlider;
import cps.lab.gui.buttons.TransformButton;

public class TransformWindow extends JFrame {

	private JPanel contentPane;

	public enum TransformType{Fourier, Cosine,};
	public enum PresentationType{W1,W2};

	public TransformWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblTransformtionType = new JLabel("Transformtion type");
		GridBagConstraints gbc_lblTransformtionType = new GridBagConstraints();
		gbc_lblTransformtionType.insets = new Insets(0, 0, 5, 5);
		gbc_lblTransformtionType.gridx = 1;
		gbc_lblTransformtionType.gridy = 2;
		contentPane.add(lblTransformtionType, gbc_lblTransformtionType);

		JComboBox transformComboBox = new JComboBox(TransformType.values());
		GridBagConstraints gbc_transformComboBox = new GridBagConstraints();
		gbc_transformComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_transformComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_transformComboBox.gridx = 4;
		gbc_transformComboBox.gridy = 2;
		contentPane.add(transformComboBox, gbc_transformComboBox);

		JLabel lblPrezentacja = new JLabel("Presentation type");
		GridBagConstraints gbc_lblPrezentacja = new GridBagConstraints();
		gbc_lblPrezentacja.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrezentacja.gridx = 1;
		gbc_lblPrezentacja.gridy = 4;
		contentPane.add(lblPrezentacja, gbc_lblPrezentacja);

		JComboBox presentationComboBox = new JComboBox(PresentationType.values());
		GridBagConstraints gbc_presentationComboBox = new GridBagConstraints();
		gbc_presentationComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_presentationComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_presentationComboBox.gridx = 4;
		gbc_presentationComboBox.gridy = 4;
		contentPane.add(presentationComboBox, gbc_presentationComboBox);

		JLabel lblN = new JLabel("N");
		GridBagConstraints gbc_lblN = new GridBagConstraints();
		gbc_lblN.insets = new Insets(0, 0, 5, 5);
		gbc_lblN.gridx = 1;
		gbc_lblN.gridy = 6;
		contentPane.add(lblN, gbc_lblN);

		JSlider slider = new IntervalSlider(1, 10, 10, 1, 1);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 0);
		gbc_slider.gridx = 4;
		gbc_slider.gridy = 6;
		contentPane.add(slider, gbc_slider);

		JButton btnTransform = new TransformButton("Transform",transformComboBox,presentationComboBox,slider);
		GridBagConstraints gbc_btnTransform = new GridBagConstraints();
		gbc_btnTransform.insets = new Insets(0, 0, 0, 5);
		gbc_btnTransform.gridx = 1;
		gbc_btnTransform.gridy = 8;
		contentPane.add(btnTransform, gbc_btnTransform);
	}

}
