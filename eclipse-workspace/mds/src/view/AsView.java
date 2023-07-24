package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
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

import model.ASDAO;
import model.rec.AsVO;
import model.rec.AscurrentVO;
import model.rec.AsnowVO;

public class AsView extends JPanel {
	JTextField tfdate, tfReturn, tfmachinename, tfRtlist, tfASlist;
	JPanel panel;
	JComboBox asloccomboBox, reloccomboBox;
	JTable AStable, astable;
	JButton appbtn;
	ArrayList list;
	ArrayList asnlist;
	AscomtableModel tmAscom;
	appnowtableModel tmasncom;
	ASDAO asdao;
	AsVO asvo;
	AscurrentVO acvo;
	AsnowVO anvo;
	JTextField textFieldmem;
	JTextField tfcomcode;
	JTextField tfcomname;
	JTextField tfcomtel;
	JTable asnowtable;
	int memCode;
	String id;

	// constructor method
	public AsView(int memCode, String id) {
		addLayout( memCode,  id);
		event( memCode,  id);
		newObject();
		
		this.memCode = memCode;
		this.id = id;

		try {
			asdao = new ASDAO();
			System.out.println("A/S ���� ����");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "A/S DB���� ���� : " + e.getMessage());
		}
	}

	void newObject() {
//      tfdate = new JTextField(15);
//      tfmachinename = new JTextField(15);
//      tfReturn = new JTextField(15);
//      tfRtlist = new JTextField(15);
//      tmAscom = new AscomtableModel();
//		tmRecom = new RecomtableModel();
//      panel = new JPanel();
//      loccomboBox = new JComboBox();
//      appbtn = new JButton("��û �ϱ�");
//      tfmachinename = new JTextField();
//      tfReturn = new JTextField();
//      AStable = new JTable();
//      tfASlist = new JTextField();
//      RTtable = new JTable();
//      RtcomboBox = new JComboBox();
//      searchbutn = new JButton(" �˻� ");
//      AScomboBox = new JComboBox();
//      tfRtlist = new JTextField();
//      searchbutn1 = new JButton(" �˻� ");
//      table = new JTable();
//      ASrabtn = new JRadioButton("A/S");
//      Returnrabtn = new JRadioButton("����");
	}

	// ����ʵ� ��ü ����
	void addLayout(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;
		AStable = new JTable();
		tfdate = new JTextField(15);
		tfmachinename = new JTextField(15);
		tfReturn = new JTextField(15);
		tfRtlist = new JTextField(15);
		tmAscom = new AscomtableModel();
		tmasncom = new appnowtableModel();
		panel = new JPanel();
		asloccomboBox = new JComboBox();
		reloccomboBox = new JComboBox();
		asnlist = new ArrayList();
		textFieldmem = new JTextField();
		tfASlist = new JTextField();
		appbtn = new JButton("��û �ϱ�");
		appbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object o = e.getSource();

				String assch = tfdate.getText();
				System.out.println(assch);
				int memcode = Integer.parseInt(textFieldmem.getText());
				System.out.println(memcode);
				String machinename = tfmachinename.getText();
				System.out.println(machinename);
				acvo = new AscurrentVO(assch, machinename, memcode);

				JOptionPane.showConfirmDialog(null, "��û�� �����Ͻðڽ��ϱ�?");

				try {

					int nownum = asdao.asappInsert(acvo);
					int asapcode = Integer.parseInt(tfcomcode.getText());
					String ascdate = tfdate.getText();
					String ascsname = tfcomname.getText();
					String ascstel = tfcomtel.getText();
					asdao.asnow(asapcode, nownum, ascdate, ascsname, ascstel);

					System.out.println("��û�Ϸ�!");
				} catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "��û ���� :" + e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
		tfmachinename = new JTextField();
		astable = new JTable();

		JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setDragMode(1);
		desktopPane_2.setLayout(null);

		JLabel date = new JLabel("��� ��¥");
		date.setFont(new Font("����", Font.BOLD, 18));
		date.setBounds(22, 129, 89, 39);
		desktopPane_2.add(date);

		tfdate.setColumns(10);
		tfdate.setBounds(133, 127, 145, 39);
		desktopPane_2.add(tfdate);

		JLabel loc = new JLabel("A/S \uC5C5\uCCB4 \uAC80\uC0C9");
		loc.setFont(new Font("����", Font.BOLD, 18));
		loc.setBounds(22, 351, 132, 39);
		desktopPane_2.add(loc);

		asloccomboBox.setModel(
				new DefaultComboBoxModel(new String[] { "����", "�λ�", "����", "��õ", "����", "����", "����", "����", "����", "��õ" }));
		asloccomboBox.setFont(new Font("����", Font.BOLD, 15));
		asloccomboBox.setBounds(146, 352, 112, 39);
		desktopPane_2.add(asloccomboBox);

		appbtn.setFont(new Font("����", Font.BOLD, 15));

		appbtn.setBounds(366, 239, 170, 76);
		desktopPane_2.add(appbtn);

		JLabel machine = new JLabel("AS \uD488\uBAA9");
		machine.setFont(new Font("����", Font.BOLD, 18));
		machine.setBounds(22, 80, 89, 39);
		desktopPane_2.add(machine);

		tfmachinename.setColumns(10);
		tfmachinename.setBounds(133, 75, 145, 39);
		desktopPane_2.add(tfmachinename);

		JLabel appname = new JLabel("��û");
		appname.setFont(new Font("����", Font.BOLD, 25));
		appname.setBounds(33, 13, 78, 39);
		desktopPane_2.add(appname);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(274)
						.addComponent(desktopPane_2, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(277, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(desktopPane_2, GroupLayout.PREFERRED_SIZE, 930, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(32, Short.MAX_VALUE)));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 401, 577, 223);
		desktopPane_2.add(scrollPane);

		astable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "��ü �ڵ�", "��ü��", "����", "��ü ����ó", " URL ", "���� �ð�", "���" }));
		scrollPane.setViewportView(astable);

		ButtonGroup bg = new ButtonGroup();
		
		textFieldmem.setText(String.valueOf(memCode));
		textFieldmem.setEditable(false);
		textFieldmem.setBounds(114, 25, 96, 21);
		desktopPane_2.add(textFieldmem);
		textFieldmem.setColumns(10);

		JLabel date_1 = new JLabel("\uC5C5\uCCB4 \uCF54\uB4DC");
		date_1.setFont(new Font("����", Font.BOLD, 18));
		date_1.setBounds(22, 178, 89, 39);
		desktopPane_2.add(date_1);

		JLabel date_1_1 = new JLabel("\uC5C5\uCCB4\uBA85");
		date_1_1.setFont(new Font("����", Font.BOLD, 18));
		date_1_1.setBounds(22, 227, 89, 39);
		desktopPane_2.add(date_1_1);

		JLabel date_1_2 = new JLabel("\uC5C5\uCCB4 \uC5F0\uB77D\uCC98");
		date_1_2.setFont(new Font("����", Font.BOLD, 18));
		date_1_2.setBounds(22, 276, 101, 39);
		desktopPane_2.add(date_1_2);

		tfcomcode = new JTextField(10);
		tfcomcode.setBounds(133, 178, 145, 39);
		desktopPane_2.add(tfcomcode);

		tfcomname = new JTextField(10);
		tfcomname.setBounds(133, 227, 145, 39);
		desktopPane_2.add(tfcomname);

		tfcomtel = new JTextField(10);
		tfcomtel.setBounds(133, 276, 145, 39);
		desktopPane_2.add(tfcomtel);

		JScrollPane sPASlist_1 = new JScrollPane();
		sPASlist_1.setBounds(12, 667, 577, 235);
		desktopPane_2.add(sPASlist_1);

		asnowtable = new JTable();
		asnowtable.setModel(tmasncom);
		asnowtable.getColumnModel().getColumn(0).setPreferredWidth(87);
		asnowtable.getColumnModel().getColumn(1).setPreferredWidth(59);
		asnowtable.getColumnModel().getColumn(2).setPreferredWidth(88);
		asnowtable.getColumnModel().getColumn(3).setPreferredWidth(63);
		asnowtable.getColumnModel().getColumn(4).setPreferredWidth(59);
		asnowtable.getColumnModel().getColumn(6).setPreferredWidth(88);
		sPASlist_1.setViewportView(asnowtable);

		JLabel ASname = new JLabel("A/S ��û ��Ȳ");
		ASname.setBounds(22, 634, 123, 23);
		desktopPane_2.add(ASname);
		ASname.setFont(new Font("����", Font.BOLD, 18));
		
		
		tfASlist.setText(String.valueOf(memCode));
		tfASlist.setEditable(false);
		tfASlist.setBounds(342, 634, 144, 23);
		desktopPane_2.add(tfASlist);
		tfASlist.setColumns(10);
		
		JButton btnNewButton = new JButton("�˻�");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				asnowtable.addMouseListener(new MouseAdapter() {
				});
				String anNum = tfASlist.getText();
				try {
					asnlist = asdao.asnList(anNum);
					tmasncom.data = asnlist;
					asnowtable.setModel(tmasncom);
					tmasncom.fireTableDataChanged();
					System.out.println("�˻� ����");
					
				}catch (Exception d) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "��û ��Ȳ �˻� ���� :" + d.getMessage());
				}
			}
		});
		btnNewButton.setBounds(498, 634, 91, 23);
		desktopPane_2.add(btnNewButton);

		panel.setLayout(gl_panel);
		setVisible(true);
		add(panel);
	}

	void selectanTable() {
		String anNum = tfASlist.getText();
		try {
			asnlist = asdao.asnList(anNum);
			tmasncom.data = asnlist;
			asnowtable.setModel(tmasncom);
			tmasncom.fireTableDataChanged();
		}catch (Exception ex) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��û ��Ȳ �˻� ���� :" + ex.getMessage());
		}
	}

	void event(int memCode, String id) {
	
		asloccomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o == asloccomboBox) {
					String asloc = (String) asloccomboBox.getSelectedItem();
					ArrayList all;
					try {
						String asloca = (String) asloccomboBox.getSelectedItem();
						selectTable(asloc);

					} catch (Exception eq) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "��ü�˻� ���� :" + eq.getMessage());
					}
					// tfmachinename.setText(asvo.get

				}

			}
		});
		astable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = astable.getSelectedRow();
				int asNum = (Integer) astable.getValueAt(row, col);
				try {
					asvo = asdao.findAslist(asNum);

				} catch (Exception c) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, " �˻� ���� :" + c.getMessage());
				}
				tfcomname.setText(asvo.getAfscname());
				tfcomtel.setText(asvo.getAfsctel());
				tfcomcode.setText(String.valueOf(asvo.getAfsccode()));
				System.out.println("��ü �˻� ����");
			}

		});
	}

	void selectTable(String str) {

		try {
			list = new ArrayList();
			list = asdao.AscomSearch(str);
			tmAscom.data = list;
			astable.setModel(tmAscom);
			tmAscom.fireTableDataChanged();

		} catch (Exception eq) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "��ü�˻� ���� :" + eq.getMessage());
		}

	}

	class AscomtableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "��ü �ڵ�", "��ü��", "����", "��ü ����ó", " URL ", "���� �ð�", "���" };

	
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

	
		public String getColumnName(int col) {
			return columnNames[col];

		}
	}
		
		class appnowtableModel extends AbstractTableModel {
			ArrayList data = new ArrayList();
			String[] columnNames = { "A/S ��Ȳ �ڵ�", "A/S �ڵ�", "A/S ��ü �ڵ�", "A/S ��¥ ", "A/S ����", "A/S ��ü��", "A/S ����ó " };

		
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


