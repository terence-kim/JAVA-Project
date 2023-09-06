package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class BuyMachineView {

	private JFrame frame;
	private JTable table;
	private JComboBox comboBox;
	private JTable table_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyMachineView window = new BuyMachineView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BuyMachineView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 708, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(26, 59, 249, 324);
		frame.getContentPane().add(table);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC885\uB958\uC120\uD0DD\uD558\uAE30"}));
		comboBox.setBounds(26, 22, 249, 23);
		frame.getContentPane().add(comboBox);
		
		table_1 = new JTable();
		table_1.setBounds(322, 59, 249, 324);
		frame.getContentPane().add(table_1);
		
		btnNewButton = new JButton("\uC8FC\uBB38");
		btnNewButton.setBounds(583, 59, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("\uC0AD\uC81C");
		btnNewButton_1.setBounds(583, 96, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
