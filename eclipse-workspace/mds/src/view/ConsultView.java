package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.ConsultDAO;
import model.rec.ConsultVO;

public class ConsultView extends JPanel implements ActionListener{
	JTextField textField;
	JTextField textField_1;
	JTextField textField_2;
	JLabel lblconLabel, lblwdateLabel_1, lblrepLabel_2, lblappLabel_2_1, lblmemcodeLabel_3;
	JComboBox comboBox;
	JTable reptable;
	JButton btnOkButton, btnCancelButton;
	JScrollPane scrollPane;
	ConsultTableModel tmConsult;
	ConsultVO vo;
	ConsultDAO dao;
	ArrayList list;
	int memCode;
	String id;
	
	public ConsultView(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;
		setLayout(null);
		
		JLabel lblconLabel = new JLabel("�������");
		lblconLabel.setBounds(35, 8, 48, 15);
		add(lblconLabel);
		
		textField = new JTextField();
		textField.setBounds(88, 5, 116, 21);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblwdateLabel_1 = new JLabel("�����¥");
		lblwdateLabel_1.setBounds(35, 36, 48, 15);
		add(lblwdateLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(88, 33, 116, 21);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblrepLabel_2 = new JLabel("����");
		lblrepLabel_2.setBounds(47, 85, 36, 15);
		add(lblrepLabel_2);
		
		JLabel lblappLabel_2_1 = new JLabel("����");
		lblappLabel_2_1.setBounds(448, 67, 24, 15);
		add(lblappLabel_2_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A/S", "����", "����", "����"}));
		comboBox.setBounds(448, 82, 51, 21);
		add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(88, 64, 116, 21);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblmemcodeLabel_3 = new JLabel("�����ڵ�");
		lblmemcodeLabel_3.setBounds(23, 67, 60, 15);
		add(lblmemcodeLabel_3);
		
		JButton btnOkButton = new JButton("Ȯ��");
		btnOkButton.setBounds(300, 81, 57, 23);
		add(btnOkButton);
		
		JButton btnCancelButton = new JButton("���");
		btnCancelButton.setBounds(369, 81, 57, 23);
		add(btnCancelButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 114, 452, 428);
		add(scrollPane);
		
		reptable = new JTable();
		reptable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(reptable);
		newObject(memCode, id);
		eventProc(memCode, id);
		
		//�����ͺ��̽� ����
		try {
			dao = new ConsultDAO();
			System.out.println("���Ἲ��!");
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,  "��������߾�� : " + e.getMessage());
		}
		

	}
	
	//��ü����
	void newObject(int memCode, String id) {
		tmConsult = new ConsultTableModel();
	}
	
	
	//�ڸ���ġ
	void initialize() {
		
	}
	// �̺�Ʈ ����
	public void eventProc(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;
		reptable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = reptable.getSelectedRow();
				String vNum = String.valueOf(reptable.getValueAt(row, col));
				
				textField_2.setText(vNum);
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if (index == 0) {
					System.out.println("A/S����");
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						reptable.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,  "1�޺�������߾�� : " + e1.getMessage());
					}
				} else if (index == 1) {
					System.out.println("���ż���");
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						reptable.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,  "2�޺�������߾�� : " + e1.getMessage());
					}
				} else if (index == 2) {
					System.out.println("���ż���");
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						reptable.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,  "3�޺�������߾�� : " + e1.getMessage());
					}
				} else if (index == 3) {
					System.out.println("��������");
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						reptable.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,  "4�޺�������߾�� : " + e1.getMessage());
					}
				}
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class ConsultTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = {"�����ڵ�", "�����̸�", "���μ�"};
		
		public int getColumnCount() {
			return columnNames.length;
		}
		
		public int getRowCount() {
			return data.size();
		}
		
		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}
		
		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

}
