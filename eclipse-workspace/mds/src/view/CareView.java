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

import model.CareDAO;
import model.rec.CareVO;
import view.CareView.CareTableModel;
import view.CareView.CaregiverTableModel;
public class CareView extends JPanel implements ActionListener {
	JTextField textField;
	JTextField textField_1;
	JTextField textField_2;
	JTextField textField_3;
	JLabel lblcssdateLabel, lblcsldateLabel, lblmemcodeLabel, lblcgLabel, lbldescLabel, lblcscodeLabel;
	JLabel lblappLabel_1, lblmemcodeLabel_1, lblcssdateLabel_2, lblcgcodeLabel;
	JButton btnOkButton, btnCancel;
	JComboBox comboBox;
	JScrollPane SPdesc, scrollPane, scrollPane_1;
	CareTableModel tmCare;
	CaregiverTableModel tmgiver;
	CareVO vo;
	CareDAO dao;
	ArrayList list;
	JTable cgtable;
	JTable apptable;
	JTextField textField_4;
	int memCode;
	String id;
	
	/**
	 * Create the panel.
	 */
	public CareView(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;
		
		btnOkButton = new JButton("Ȯ��");
		//eventProc(); �� ���� �ʹٸ� ������ �ϰŶ�
		//������ ���ؼ� �ϰ� �信�� ��ư �ϳ��ϳ��� �ִ°Ŵ�
		//���������� Ȯ�δ����� �ǳ�
		btnOkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object o =e.getSource();
				String csstartdate = textField.getText();
				String cslastdate = textField_1.getText();
				int memcode = Integer.parseInt(textField_3.getText());
				int cgcode = Integer.parseInt(textField_2.getText());
								
				try {
					
					vo = new CareVO(csstartdate, cslastdate, memcode, cgcode);
					dao.careInsert(vo);
					System.out.println("��û�Ϸ�!");
				}catch (Exception e3) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "��û���� : " + e3.getMessage());
				}
			}
			
		});
		setLayout(null);
		btnOkButton.setBounds(283, 371, 57, 23);
		add(btnOkButton);
		
		JButton btnCancelButton = new JButton("���");
		btnCancelButton.setBounds(496, 371, 57, 23);
		add(btnCancelButton);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"����", "����", "�λ�", "���", "����"}));
		comboBox.setBounds(12, 77, 63, 21);
		add(comboBox);
		//����
		textField = new JTextField();
		textField.setBounds(12, 168, 116, 21);
		add(textField);
		textField.setColumns(10);
		//����
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(12, 224, 116, 21);
		add(textField_1);
		//�������ڵ�
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(12, 280, 116, 21);
		add(textField_2);
		//ȸ���ڵ�
		textField_3 = new JTextField();
		textField_3.setText(String.valueOf(memCode));
		textField_3.setBounds(12, 336, 116, 21);
		add(textField_3);
		textField_3.setColumns(10);
		
		lblcgLabel = new JLabel("������");
		lblcgLabel.setBounds(12, 255, 36, 15);
		add(lblcgLabel);
		  
		lblcssdateLabel_2 = new JLabel("���۳�¥");
		lblcssdateLabel_2.setBounds(12, 143, 48, 15);
		add(lblcssdateLabel_2);
		
		lblcsldateLabel = new JLabel("���ᳯ¥");
		lblcsldateLabel.setBounds(12, 199, 48, 15);
		add(lblcsldateLabel);
		
		lblcgcodeLabel = new JLabel("�������ڵ�");
		lblcgcodeLabel.setBounds(223, 127, 60, 15);
		add(lblcgcodeLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 152, 452, 205);
		add(scrollPane);
		
		cgtable = new JTable();
		cgtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(cgtable);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(223, 404, 452, 246);
		add(scrollPane_1);
		
		apptable = new JTable();
		apptable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(apptable);
		
		lblappLabel_1 = new JLabel("��û");
		lblappLabel_1.setBounds(338, 127, 24, 15);
		add(lblappLabel_1);
		
		
		lblmemcodeLabel_1 = new JLabel("ȸ���ڵ�");
		lblmemcodeLabel_1.setBounds(12, 311, 48, 15);
		add(lblmemcodeLabel_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(12, 402, 116, 21);
		add(textField_4);
		
		lblcscodeLabel = new JLabel("������û�ڵ�");
		lblcscodeLabel.setBounds(12, 377, 72, 15);
		add(lblcscodeLabel);
		newObject();
		initialize();
		eventProc(memCode, id);
		
		//�����ͺ��̽� ����
		try {
			dao = new CareDAO();
			System.out.println("����Ϸ�");
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,  "������߾�� : " + e.getMessage());
		}
	}
	
	//��ü ����
	void newObject() {
		btnOkButton = new JButton();
		tmCare = new CareTableModel();
		tmgiver = new CaregiverTableModel();
	}
	
	//�ڸ���ġ
	void initialize() {


	}
	// �̺�Ʈ ����
	void eventProc(int memCode, String id) {
	
		this.memCode = memCode;
		this.id = id;
		
		textField_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = Integer.parseInt(textField_4.getText());
				ArrayList all;
				try {
					all = dao.ServiceSearch(index);
					System.out.println(all);
					tmgiver.data = all;
					apptable.setModel(tmgiver);
					tmgiver.fireTableDataChanged();
				}catch (Exception ev) {
					// TODO: handle exception
					ev.printStackTrace();
					JOptionPane.showMessageDialog(null,  "������߾�� : " + ev.getMessage());
				}
			}
		});
		
		// click�̿��� clik�ƴϿ���
		cgtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = cgtable.getSelectedRow();
				String vNum = String.valueOf(cgtable.getValueAt(row, col));
				
				textField_2.setText(vNum);
			}
		}); 
	
		
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if (index == 0) {
					System.out.println("���Ｑ��");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,  "1�޺�������߾�� : " + e1.getMessage());
					}
				} else if (index == 1) {
					System.out.println("��������");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,  "2�޺�������߾�� : " + e1.getMessage());
					}
				} else if (index == 2) {
					System.out.println("�λ꼱��");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,  "3�޺�������߾�� : " + e1.getMessage());
					}
					
				} else if (index == 3) {
					System.out.println("��󵵼���");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,  "4�޺�������߾�� : " + e1.getMessage());
					}
				} else if (index == 4) {
					System.out.println("���󵵼���");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,  "5�޺�������߾�� : " + e1.getMessage());
					}
				// TODO Auto-generated method stub
				}
			}
		
		});
	}
	
	
	
	
	class CareTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "�������ڵ�", "�����θ�", "�Ҽ�ȸ��", "����ó", "�޿��ڵ�", "����"};
		
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
		class CaregiverTableModel extends AbstractTableModel {
			ArrayList data = new ArrayList();
			String[] columnNames = { "���������ڵ�", "������", "������", "ȸ���ڵ�", "�������ڵ�" , "��û��¥"};
			
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





	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
