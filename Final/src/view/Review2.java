package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import model.Review2Dao;
import model.ReviewDAO;
import model.rec.ReviewVO;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;

public class Review2 extends JDialog implements ActionListener {

	JTextField tfMachineName, tfShopCode, tfMemberCode;
	JTable tbAcceptedReview;
	JButton btnEdit, btnRegist, btnDelete;
	JTextArea taReviewContent;
	JLabel lbMachineName, lbRegistReview, lbShopCode, lbReviewAvg, lbMemberCode;
	JScrollPane scrollPane;
	JComboBox comboBox;
	ReviewVO vo;
	Review2Dao dao;
	ArrayList list;

	ReviewTableModel tmReview;

	int memCode, desc;
	String machineName;

	public Review2(int memCode, String machineName, int desc) {
		this.memCode = memCode;
		this.machineName = machineName;
		this.desc = desc;
		getContentPane().setLayout(null);
		newObject(memCode, machineName);
		eventProc(memCode, machineName);
		tfMemberCode.setText(String.valueOf(memCode));
		tfMachineName.setText(machineName);
		tfShopCode.setText(String.valueOf(desc));
		
		

		try {
			dao = new Review2Dao();
			System.out.println("review ��� ���� ����");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "review DB���� ���� : " + e.getMessage());
		}
		selectTable(memCode, machineName);
	}

	void newObject(int memCode, String machineName) {

		tfMachineName = new JTextField();
		tfMachineName.setEditable(false);
		tfShopCode = new JTextField();
		tfShopCode.setEditable(false);
		tfMemberCode = new JTextField();
		tmReview = new ReviewTableModel();
		taReviewContent = new JTextArea();
		tbAcceptedReview = new JTable();
		btnEdit = new JButton("����");
		btnRegist = new JButton("���");
		btnDelete = new JButton("����");
		lbMachineName = new JLabel("����");
		lbRegistReview = new JLabel("��ϵȸ���");
		lbShopCode = new JLabel("�ֹ��󼼳����ڵ�");
		lbReviewAvg = new JLabel("����");
		lbMemberCode = new JLabel("ȸ���ڵ�");
		scrollPane = new JScrollPane();
		comboBox = new JComboBox();

		tfShopCode.setFont(new Font("����", Font.PLAIN, 15));
		tfShopCode.setBounds(157, 71, 140, 40);
		tfShopCode.setColumns(10);
		tfMachineName.setFont(new Font("����", Font.PLAIN, 15));
		tfMachineName.setBounds(87, 121, 210, 40);
		tfMachineName.setColumns(10);
		tfMemberCode.setEditable(false);
		tfMemberCode.setFont(new Font("����", Font.PLAIN, 15));
		tfMemberCode.setColumns(10);
		tfMemberCode.setBounds(157, 21, 140, 40);
		taReviewContent.setFont(new Font("����", Font.PLAIN, 12));
		taReviewContent.setBounds(15, 458, 480, 132);
		tbAcceptedReview.setFont(new Font("Gulim", Font.PLAIN, 12));
		tbAcceptedReview
				.setModel(new DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "�����ڵ�", "����", "����", "���ֹ��ڵ�" }));

		btnEdit.setFont(new Font("����", Font.PLAIN, 15));
		btnEdit.setBounds(217, 650, 80, 40);
		btnRegist.setFont(new Font("����", Font.PLAIN, 15));
		btnRegist.setBounds(15, 650, 80, 40);
		btnDelete.setFont(new Font("����", Font.PLAIN, 15));
		btnDelete.setBounds(415, 650, 80, 40);

		lbMachineName.setFont(new Font("����", Font.PLAIN, 15));
		lbMachineName.setBounds(15, 121, 60, 40);
		lbRegistReview.setFont(new Font("����", Font.PLAIN, 15));
		lbRegistReview.setBounds(15, 171, 80, 40);
		lbShopCode.setFont(new Font("����", Font.PLAIN, 15));
		lbShopCode.setBounds(15, 71, 120, 40);
		lbReviewAvg.setFont(new Font("����", Font.PLAIN, 15));
		lbReviewAvg.setBounds(217, 600, 80, 40);
		lbMemberCode.setFont(new Font("����", Font.PLAIN, 15));
		lbMemberCode.setBounds(15, 21, 120, 40);
		scrollPane.setViewportView(tbAcceptedReview);
		scrollPane.setBounds(15, 221, 480, 227);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "4", "3", "2", "1"}));
		comboBox.setEditable(true);
		comboBox.setBounds(343, 609, 150, 23);
		
		getContentPane().add(comboBox);
		getContentPane().add(tfMachineName);
		getContentPane().add(tfShopCode);
		getContentPane().add(tfMemberCode);
		getContentPane().add(taReviewContent);
		getContentPane().add(btnEdit);
		getContentPane().add(btnRegist);
		getContentPane().add(btnDelete);
		getContentPane().add(lbMachineName);
		getContentPane().add(lbRegistReview);
		getContentPane().add(lbShopCode);
		getContentPane().add(lbReviewAvg);
		getContentPane().add(lbMemberCode);
		getContentPane().add(scrollPane);
		
		setSize(700,800);
	}

	void eventProc(int memCode, String machineName) {
		// TODO Auto-generated method stub

		btnEdit.addActionListener(this);
		btnRegist.addActionListener(this);
		btnDelete.addActionListener(this);
		tfMachineName.addActionListener(this);

		tbAcceptedReview.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = tbAcceptedReview.getSelectedRow();
				int rNum = (Integer) tbAcceptedReview.getValueAt(row, col);
				try {
					vo = dao.findByNum(rNum);
				} catch (Exception e1) {
					// TODO: handle exception
				}
//				tfReviewAvg.setText(String.valueOf(vo.getAvg()));
				comboBox.setSelectedItem(String.valueOf(vo.getAvg()));
				tfShopCode.setText(String.valueOf(vo.getCode()));
				taReviewContent.setText(String.valueOf(vo.getContent()));
				System.out.println("�˻�  ����");
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnRegist) {
			String avg = String.valueOf(comboBox.getSelectedItem());
			String content = taReviewContent.getText();
			int code = Integer.parseInt(tfShopCode.getText());
			ReviewVO vo = new ReviewVO(avg, content, code);

			try {
				dao.reviewRegist(vo);
				clearScreen();
				selectTable(memCode, machineName);
				System.out.println("��� �Ϸ�");
			} catch (Exception e3) {
				// TODO: handle exception
				System.out.println("��� ����");
			}

		} else if (o == btnEdit) {
			String avg = String.valueOf(comboBox.getSelectedItem());
			String content = taReviewContent.getText();
			int code = Integer.parseInt(tfShopCode.getText());
			vo = new ReviewVO(avg, content, code);
			int row = tbAcceptedReview.getSelectedRow();
			int rNum = (Integer) tbAcceptedReview.getValueAt(row, 0);
			vo.setNum(rNum);

			try {
				int cnt = dao.reviewEdit(vo);
//				selectTable();
				selectTable(memCode, machineName);
				if (cnt > 0) {
					System.out.println("���� �Ϸ�");
					clearScreen();
				} else {
					System.out.println("���� ����");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "���� ���� ����!! : " + e2.getMessage());
			}

		} else if (o == btnDelete) {
			int row = tbAcceptedReview.getSelectedRow();
			int rNum = (Integer) tbAcceptedReview.getValueAt(row, 0);
			vo.setNum(rNum);

			try {
				int cnt = dao.reviewDelete(vo);
//				selectTable();
//				selectTable(memCode, machineName);
				setVisible(false);
				
				if (cnt > 0) {
					clearScreen();
					System.out.println("���� �Ϸ�");
				} else {
					System.out.println("���� ����");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "���� ���� ����!! : " + e2.getMessage());
			}

		} 
	}

	private void selectTable(int memcode, String text) {
		// TODO Auto-generated method stub
		System.out.println(text + "�̰� �ⱸ��");
		System.out.println(memcode + "�̰� ����ڵ�");
		try {
			ArrayList list = new ArrayList();
			list = dao.ReviewSearch(text, memcode);
			System.out.println("�丮��Ʈ" + list);
			tmReview.data = list;
			tbAcceptedReview.setModel(tmReview);
			tmReview.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "���� �˻� ���� : " + e.getMessage());
		}
	}

	void clearScreen() {
		comboBox.setSelectedIndex(0);
		taReviewContent.setText(null);
		tfShopCode.setText(null);
		tfMachineName.setText(null);
	}
}

class ReviewTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "�����ڵ�", "����", "����", "���ֹ��ڵ�" };

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

}
