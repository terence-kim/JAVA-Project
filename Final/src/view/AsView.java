package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import com.toedter.calendar.JDateChooser;

import model.ASDAO;
import model.rec.AsVO;
import model.rec.AscurrentVO;
import model.rec.AsnowVO;

public class AsView extends JPanel {
	JTextField tfReturn, tfmachinename, tfRtlist, tfASlist;
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
	JDateChooser dateChooser;
	SimpleDateFormat simpleDateFormat;
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
			System.out.println("A/S �뿰寃� �꽦怨�");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "A/S DB�뿰寃� �떎�뙣 : " + e.getMessage());
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
//      appbtn = new JButton("�떊泥� �븯湲�");
//      tfmachinename = new JTextField();
//      tfReturn = new JTextField();
//      AStable = new JTable();
//      tfASlist = new JTextField();
//      RTtable = new JTable();
//      RtcomboBox = new JComboBox();
//      searchbutn = new JButton(" 寃��깋 ");
//      AScomboBox = new JComboBox();
//      tfRtlist = new JTextField();
//      searchbutn1 = new JButton(" 寃��깋 ");
//      table = new JTable();
//      ASrabtn = new JRadioButton("A/S");
//      Returnrabtn = new JRadioButton("�닔嫄�");
	}

	// 硫ㅻ쾭�븘�뱶 媛앹껜 �깮�꽦
	void addLayout(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;
		AStable = new JTable();
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
		appbtn = new JButton("�떊泥� �븯湲�");
		dateChooser = new JDateChooser();
		
		
		
		appbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object o = e.getSource();
				
				simpleDateFormat = new SimpleDateFormat("yy/MM/dd"); 
				Date date =  dateChooser.getDate();
				 
				String assch = simpleDateFormat.format(date);
				System.out.println(assch);
				int memcode = Integer.parseInt(textFieldmem.getText());
				System.out.println(memcode);
				String machinename = tfmachinename.getText();
				System.out.println(machinename);
				acvo = new AscurrentVO(assch, machinename, memcode);

				JOptionPane.showConfirmDialog(null, "�떊泥��쓣 吏꾪뻾�븯�떆寃좎뒿�땲源�?");

				try {

					int nownum = asdao.asappInsert(acvo);
					int asapcode = Integer.parseInt(tfcomcode.getText());
					String ascdate = simpleDateFormat.format(date);
					String ascsname = tfcomname.getText();
					String ascstel = tfcomtel.getText();
					asdao.asnow(asapcode, nownum, ascdate, ascsname, ascstel);

					System.out.println("�떊泥��셿猷�!");
				} catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "�떊泥� �떎�뙣 :" + e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
		tfmachinename = new JTextField();
		astable = new JTable();

		JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setDragMode(1);
		desktopPane_2.setLayout(null);

		JLabel date = new JLabel("�씗留� �궇吏�");
		date.setFont(new Font("援대┝", Font.BOLD, 18));
		date.setBounds(22, 129, 89, 39);
		desktopPane_2.add(date);

		JLabel loc = new JLabel("A/S \uC5C5\uCCB4 \uAC80\uC0C9");
		loc.setFont(new Font("援대┝", Font.BOLD, 18));
		loc.setBounds(22, 351, 132, 39);
		desktopPane_2.add(loc);

		asloccomboBox.setModel(
				new DefaultComboBoxModel(new String[] { "�꽌�슱", "遺��궛", "源��빐", "�씤泥�", "�닔�썝", "源��룷", "愿묒＜", "�룷�빆", "�뙆二�", "遺�泥�" }));
		asloccomboBox.setFont(new Font("援대┝", Font.BOLD, 15));
		asloccomboBox.setBounds(146, 352, 112, 39);
		desktopPane_2.add(asloccomboBox);

		appbtn.setFont(new Font("援대┝", Font.BOLD, 15));

		appbtn.setBounds(366, 239, 170, 76);
		desktopPane_2.add(appbtn);

		JLabel machine = new JLabel("AS \uD488\uBAA9");
		machine.setFont(new Font("援대┝", Font.BOLD, 18));
		machine.setBounds(22, 80, 89, 39);
		desktopPane_2.add(machine);

		tfmachinename.setColumns(10);
		tfmachinename.setBounds(133, 75, 145, 39);
		desktopPane_2.add(tfmachinename);

		JLabel appname = new JLabel("�떊泥�");
		appname.setFont(new Font("援대┝", Font.BOLD, 25));
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
				new String[] { "�뾽泥� 肄붾뱶", "�뾽泥대챸", "吏��뿭", "�뾽泥� �뿰�씫泥�", " URL ", "�쁺�뾽 �떆媛�", "鍮꾩슜" }));
		scrollPane.setViewportView(astable);

		ButtonGroup bg = new ButtonGroup();
		
		textFieldmem.setText(String.valueOf(memCode));
		textFieldmem.setEditable(false);
		textFieldmem.setBounds(114, 25, 96, 21);
		desktopPane_2.add(textFieldmem);
		textFieldmem.setColumns(10);

		JLabel date_1 = new JLabel("\uC5C5\uCCB4 \uCF54\uB4DC");
		date_1.setFont(new Font("援대┝", Font.BOLD, 18));
		date_1.setBounds(22, 178, 89, 39);
		desktopPane_2.add(date_1);

		JLabel date_1_1 = new JLabel("\uC5C5\uCCB4\uBA85");
		date_1_1.setFont(new Font("援대┝", Font.BOLD, 18));
		date_1_1.setBounds(22, 227, 89, 39);
		desktopPane_2.add(date_1_1);

		JLabel date_1_2 = new JLabel("\uC5C5\uCCB4 \uC5F0\uB77D\uCC98");
		date_1_2.setFont(new Font("援대┝", Font.BOLD, 18));
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

		JLabel ASname = new JLabel("A/S �떊泥� �쁽�솴");
		ASname.setBounds(22, 634, 123, 23);
		desktopPane_2.add(ASname);
		ASname.setFont(new Font("援대┝", Font.BOLD, 18));
		
		
		tfASlist.setText(String.valueOf(memCode));
		tfASlist.setEditable(false);
		tfASlist.setBounds(342, 634, 144, 23);
		desktopPane_2.add(tfASlist);
		tfASlist.setColumns(10);
		
		JButton btnNewButton = new JButton("寃��깋");
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
					System.out.println("寃��깋 �꽦怨�");
					
				}catch (Exception d) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "�떊泥� �쁽�솴 寃��깋 �떎�뙣 :" + d.getMessage());
				}
			}
		});
		btnNewButton.setBounds(498, 634, 91, 23);
		desktopPane_2.add(btnNewButton);
		
		
		dateChooser.setBounds(133, 129, 145, 39);
		desktopPane_2.add(dateChooser);

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
			JOptionPane.showMessageDialog(null, "�떊泥� �쁽�솴 寃��깋 �떎�뙣 :" + ex.getMessage());
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
						JOptionPane.showMessageDialog(null, "�뾽泥닿��깋 �떎�뙣 :" + eq.getMessage());
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
					JOptionPane.showMessageDialog(null, " 寃��깋 �떎�뙣 :" + c.getMessage());
				}
				tfcomname.setText(asvo.getAfscname());
				tfcomtel.setText(asvo.getAfsctel());
				tfcomcode.setText(String.valueOf(asvo.getAfsccode()));
				System.out.println("�뾽泥� 寃��깋 �꽦怨�");
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
			JOptionPane.showMessageDialog(null, "�뾽泥닿��깋 �떎�뙣 :" + eq.getMessage());
		}

	}

	class AscomtableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "�뾽泥� 肄붾뱶", "�뾽泥대챸", "吏��뿭", "�뾽泥� �뿰�씫泥�", " URL ", "�쁺�뾽 �떆媛�", "鍮꾩슜" };

	
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
			String[] columnNames = { "A/S �쁽�솴 肄붾뱶", "A/S 肄붾뱶", "A/S �뾽泥� 肄붾뱶", "A/S �궇吏� ", "A/S �뿬遺�", "A/S �뾽泥대챸", "A/S �뿰�씫泥� " };

		
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
			// 2. 吏��젙�맂 而щ읆紐낆쑝濡� 蹂��솚�븯湲�
			//
//	                  湲곕낯�쟻�쑝濡� A, B, C, D �씪�뒗 �씠由꾩쑝濡� 而щ읆紐낆씠 吏��젙�맂�떎
			public String getColumnName(int col) {
				return columnNames[col];
		}
	

			}
	}


