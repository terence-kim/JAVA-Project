package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.ReturnDAO;
import model.rec.ReturnServiceVO;
import model.rec.ReturnVO;
import model.rec.ReturnnowVO;

public class ReturnView extends JPanel {

	JComboBox reloccomboBox;
	JPanel panel;
	JTextField tfRedate;
	JTextField tfRecode;
	JTextField memid;
	JTextField tfcolcode;
	JTextField tfcolname;
	JTextField tfcoltel;
	JTable retable;
	JTable renowtable;
	JTextField tfcolnow;
	ReturnDAO redao;
	ReturnVO revo;
	ReturnServiceVO resvo;
	ReturnnowVO renowvo;
	int memCode;
	String id;
	JButton appbtn;
	ArrayList list;
	ArrayList renlist;
	RecomtableModel tmRecom;
	apprenowtableModel tmarncom;

	/**
	 * Launch the application.
	 */
	public ReturnView(int memCode, String id) {
		addLayout(memCode, id);
		event(memCode, id);

		this.memCode = memCode;
		this.id = id;

		try {
			redao = new ReturnDAO();
			System.out.println("���� ���� ����");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���� DB���� ���� : " + e.getMessage());
		}
	}

	void addLayout(int memCode, String id) {

		this.memCode = memCode;
		this.id = id;
		panel = new JPanel();
		tfRedate = new JTextField();
		tfRecode = new JTextField();
		tfcolcode = new JTextField();
		tfcolname = new JTextField();
		tfcoltel = new JTextField();
		tfcolnow = new JTextField();
		retable = new JTable();
		renowtable = new JTable();
		appbtn = new JButton("��û �ϱ�");
		tmRecom = new RecomtableModel();
		tmarncom = new apprenowtableModel();
		reloccomboBox = new JComboBox();
		memid = new JTextField();
		tfcolnow = new JTextField();
		JButton appbtn = new JButton("��û �ϱ�");
		renlist = new ArrayList();

		appbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object o = e.getSource();

				String resch = tfRedate.getText();
				System.out.println(resch);
				int memcode = Integer.parseInt(memid.getText());
				System.out.println("4");
				String reitem = tfRecode.getText();
				System.out.println("11");
				resvo = new ReturnServiceVO(reitem, resch, memcode);
				
				JOptionPane.showConfirmDialog(null, "��û�� �����Ͻðڽ��ϱ�?");
				try {

					int renow = redao.reappInsert(resvo);
					int recode = Integer.parseInt(tfcolcode.getText());
					String ccsdate = tfRedate.getText();
					String ccsname = tfcolname.getText();
					String ccstel = tfcoltel.getText();
					redao.renow( renow, recode, ccsdate, ccsname, ccstel);

					System.out.println("��û�Ϸ�!");
				} catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "��û ���� :" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});

		panel = new JPanel();
		panel.setBounds(100, 100, 619, 932);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);

		JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setLayout(null);
		desktopPane_2.setDragMode(1);

		JLabel date = new JLabel("��� ��¥");
		date.setFont(new Font("����", Font.BOLD, 18));
		date.setBounds(22, 129, 89, 39);
		desktopPane_2.add(date);

		tfRedate = new JTextField(10);
		tfRedate.setBounds(133, 127, 145, 39);
		desktopPane_2.add(tfRedate);

		appbtn.setFont(new Font("����", Font.BOLD, 15));
		appbtn.setBounds(350, 239, 170, 76);
		desktopPane_2.add(appbtn);

		JLabel returnname = new JLabel("\uC218\uAC70 \uD488\uBAA9");
		returnname.setFont(new Font("����", Font.BOLD, 18));
		returnname.setBounds(22, 80, 89, 39);
		desktopPane_2.add(returnname);

		tfRecode = new JTextField();
		tfRecode.setColumns(10);
		tfRecode.setBounds(133, 78, 145, 39);
		desktopPane_2.add(tfRecode);

		JLabel appname = new JLabel("��û");
		appname.setFont(new Font("����", Font.BOLD, 25));
		appname.setBounds(33, 13, 78, 39);
		desktopPane_2.add(appname);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 401, 577, 217);
		desktopPane_2.add(scrollPane);

		retable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "��ü �ڵ�", "��ü��", "����", "��ü ����ó", " URL ", "���� �ð�", "���" }));
		scrollPane.setViewportView(retable);

		memid.setText(String.valueOf(memCode));
		memid.setEditable(false);
		memid.setColumns(10);
		desktopPane_2.add(memid);
		memid.setBounds(114, 25, 96, 21);

		JLabel date_1 = new JLabel("��ü �ڵ�");
		date_1.setFont(new Font("����", Font.BOLD, 18));
		date_1.setBounds(22, 178, 89, 39);
		desktopPane_2.add(date_1);

		JLabel date_1_1 = new JLabel("��ü��");
		date_1_1.setFont(new Font("����", Font.BOLD, 18));
		date_1_1.setBounds(22, 227, 89, 39);
		desktopPane_2.add(date_1_1);

		JLabel date_1_2 = new JLabel("��ü ����ó");
		date_1_2.setFont(new Font("����", Font.BOLD, 18));
		date_1_2.setBounds(22, 276, 101, 39);
		desktopPane_2.add(date_1_2);

		tfcolcode = new JTextField(10);
		tfcolcode.setBounds(133, 178, 145, 39);
		desktopPane_2.add(tfcolcode);

		tfcolname = new JTextField(10);
		tfcolname.setBounds(133, 227, 145, 39);
		desktopPane_2.add(tfcolname);

		tfcoltel = new JTextField(10);
		tfcoltel.setBounds(133, 276, 145, 39);
		desktopPane_2.add(tfcoltel);

		JLabel loc_1 = new JLabel("���� ��ü �˻�");
		loc_1.setFont(new Font("����", Font.BOLD, 18));
		loc_1.setBounds(12, 351, 132, 39);
		desktopPane_2.add(loc_1);

		// JComboBox reloccomboBox = new JComboBox();
		reloccomboBox.setModel(new DefaultComboBoxModel(new String[] { "����", "����", "�λ�", "�뱸", "��⵵" }));
		reloccomboBox.setFont(new Font("����", Font.BOLD, 15));
		reloccomboBox.setBounds(147, 352, 112, 39);
		desktopPane_2.add(reloccomboBox);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(276)
						.addComponent(desktopPane_2, GroupLayout.PREFERRED_SIZE, 598, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(275, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(desktopPane_2, GroupLayout.PREFERRED_SIZE, 916, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE)));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 668, 577, 208);
		desktopPane_2.add(scrollPane_1);

		renowtable = new JTable();
		renowtable.setModel(tmarncom);
		renowtable.getColumnModel().getColumn(0).setPreferredWidth(87);
		renowtable.getColumnModel().getColumn(1).setPreferredWidth(61);
		renowtable.getColumnModel().getColumn(2).setPreferredWidth(87);
		renowtable.getColumnModel().getColumn(3).setPreferredWidth(65);
		renowtable.getColumnModel().getColumn(4).setPreferredWidth(62);
		scrollPane_1.setViewportView(renowtable);

		JLabel loc_1_1 = new JLabel("���� ��û ��Ȳ");
		loc_1_1.setFont(new Font("����", Font.BOLD, 18));
		loc_1_1.setBounds(12, 628, 132, 39);
		desktopPane_2.add(loc_1_1);

		JButton searchbutn = new JButton(" �˻� ");
		searchbutn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				renowtable.addMouseListener(new MouseAdapter() {
					
				});
				String rnNum = tfcolnow.getText();
				try {
					renlist = redao.renList(rnNum);
					tmarncom.data = renlist;
					renowtable.setModel(tmarncom);
					tmarncom.fireTableDataChanged();
				
				
				}catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "��û ��Ȳ �˻� ���� :" + ex.getMessage());
				}
			}
		});
		searchbutn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o == searchbutn) {
					
				}
				
			}
		});
		searchbutn.setBounds(495, 637, 91, 23);
		desktopPane_2.add(searchbutn);

		
		tfcolnow.setText(String.valueOf(memCode));
		tfcolnow.setEditable(false);
		
		tfcolnow.setBounds(345, 637, 144, 23);
		desktopPane_2.add(tfcolnow);
		tfcolnow.setColumns(10);

		panel_1.setLayout(gl_panel_1);
		setVisible(true);
		add(panel);
	}
	
	void selectrenTable() {
		String rnNum = tfcolnow.getText();
		try {
			renlist = redao.renList(rnNum);
			tmarncom.data = renlist;
			renowtable.setModel(tmarncom);
			tmarncom.fireTableDataChanged();
		}catch (Exception w) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��û ��Ȳ �˻� ���� :" + w.getMessage());
		}
	}

	void event(int memCode, String id) {

		reloccomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o == reloccomboBox) {
					String reloc = (String) reloccomboBox.getSelectedItem();
					ArrayList all;
					try {
						String reloca = (String) reloccomboBox.getSelectedItem();
						selectTable(reloc);
					} catch (Exception t) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "��ü�˻� ���� :" + t.getMessage());
					}
				}
			}
		});

		retable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = retable.getSelectedRow();
				int reNum = (Integer) retable.getValueAt(row, col);
				try {
					revo = redao.findRelist(reNum);
				} catch (Exception q) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "��ü�˻� ���� :" + q.getMessage());
				}
				tfcolname.setText(revo.getColinfo());
				tfcoltel.setText(revo.getColtel());
				tfcolcode.setText(String.valueOf(revo.getColcode()));
				System.out.println("��ü �˻� ����");
			}
		});
	}

	void selectTable(String reloc) {
		try {
			list = new ArrayList();
			list = redao.RecomSearch(reloc);
			tmRecom.data = list;
			retable.setModel(tmRecom);
			tmRecom.fireTableDataChanged();
		} catch (Exception e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��ü�˻� ���� :" + e1.getMessage());

		}

	}

	class RecomtableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "��ü �ڵ�", "��ü��", "��ü ����ó", "����", " URL ", "���� �ð�", "���" };

		// =============================================================
		// 1. �⺻���� TabelModel �����
		// �Ʒ� �� �Լ��� TabelModel �������̽��� �߻��Լ��ε�
		// AbstractTabelModel���� �������� �ʾұ⿡...
		// �ݵ�� ����� ���� �ʼ�!!!!

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList tem = (ArrayList) data.get(row);
			return tem.get(col);
		}

		// ===============================================================
		// 2. ������ �÷������� ��ȯ�ϱ�
		//
//                  �⺻������ A, B, C, D ��� �̸����� �÷����� �����ȴ�
		public String getColumnName(int col) {
			return columnNames[col];

		}
	}
		
	class apprenowtableModel extends AbstractTableModel {

			ArrayList data = new ArrayList();
			String[] columnNames = { "���� ��Ȳ �ڵ�", "���� �ڵ�", "���� ��ü �ڵ�", "���� ��¥", " ���� ���� ", "���� ��ü��", "���� ����ó" };

			// =============================================================
			// 1. �⺻���� TabelModel �����
			// �Ʒ� �� �Լ��� TabelModel �������̽��� �߻��Լ��ε�
			// AbstractTabelModel���� �������� �ʾұ⿡...
			// �ݵ�� ����� ���� �ʼ�!!!!

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

			// ===============================================================
			// 2. ������ �÷������� ��ȯ�ϱ�
			//
//	                  �⺻������ A, B, C, D ��� �̸����� �÷����� �����ȴ�
			public String getColumnName(int col) {
				return columnNames[col];

	}

}
	}

