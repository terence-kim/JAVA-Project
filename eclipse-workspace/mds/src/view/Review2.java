package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

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

public class Review2 extends JPanel implements ActionListener {

	JTextField tfMachineName, tfReviewAvg, tfShopCode, tfMemberCode;
	JTable tbAcceptedReview;
	JButton btnEdit, btnRegist, btnDelete;
	JTextArea taReviewContent;

	ReviewVO vo;
	ReviewDAO dao;
	ArrayList list;
	int memCode;// ȸ�� �ڵ�
	String id;// ȸ�� ���̵�
	ReviewTableModel tmReview;

	public Review2(int memCode, String id) {
		newObject(memCode, id);
		eventProc(memCode, id);
		
		this.memCode = memCode;
		this.id = id;

		try {
			dao = new ReviewDAO();
			System.out.println("review ��� ���� ����");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "review DB���� ���� : " + e.getMessage());
		}
	}

	void newObject(int memCode, String id) {

		tfMachineName = new JTextField();
		tfMachineName.setEditable(false);
		tfShopCode = new JTextField();
		tfShopCode.setEditable(false);
		tfReviewAvg = new JTextField();
		tfMemberCode = new JTextField();
		tmReview = new ReviewTableModel();
		taReviewContent = new JTextArea();
		btnEdit = new JButton("����");
		btnRegist = new JButton("���");
		btnDelete = new JButton("����");
		setLayout(null);

		tfShopCode.setFont(new Font("����", Font.PLAIN, 15));
		tfShopCode.setBounds(134, 40, 116, 24);
		add(tfShopCode);
		tfShopCode.setColumns(10);

		tfMachineName.setFont(new Font("����", Font.PLAIN, 15));
		tfMachineName.setBounds(134, 6, 116, 24);
		add(tfMachineName);
		tfMachineName.setColumns(10);

		tfReviewAvg.setFont(new Font("����", Font.PLAIN, 15));
		tfReviewAvg.setBounds(134, 564, 116, 24);
		add(tfReviewAvg);
		tfReviewAvg.setColumns(10);

		btnEdit.setFont(new Font("����", Font.PLAIN, 15));
		btnEdit.setBounds(189, 608, 61, 27);
		add(btnEdit);
		btnRegist.setFont(new Font("����", Font.PLAIN, 15));
		btnRegist.setBounds(63, 608, 61, 27);
		add(btnRegist);
		btnDelete.setFont(new Font("����", Font.PLAIN, 15));
		btnDelete.setBounds(311, 608, 61, 27);
		add(btnDelete);

		JLabel lbMachineName = new JLabel("����");
		lbMachineName.setFont(new Font("����", Font.PLAIN, 15));
		lbMachineName.setBounds(33, 9, 42, 18);
		add(lbMachineName);

		taReviewContent.setFont(new Font("����", Font.PLAIN, 12));
		taReviewContent.setBounds(0, 323, 452, 201);
		add(taReviewContent);

		JLabel lbRegistReview = new JLabel("��ϵȸ���");
		lbRegistReview.setFont(new Font("����", Font.PLAIN, 15));
		lbRegistReview.setBounds(12, 79, 70, 18);
		add(lbRegistReview);

		JLabel lbShopCode = new JLabel("�ֹ��󼼳����ڵ�");
		lbShopCode.setFont(new Font("����", Font.PLAIN, 15));
		lbShopCode.setBounds(12, 43, 112, 18);
		add(lbShopCode);

		JLabel lbReviewAvg = new JLabel("����");
		lbReviewAvg.setFont(new Font("����", Font.PLAIN, 15));
		lbReviewAvg.setBounds(63, 567, 28, 18);
		add(lbReviewAvg);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 107, 452, 201);
		add(scrollPane);
		tbAcceptedReview = new JTable();
		tbAcceptedReview.setModel(
				new DefaultTableModel(
			new Object[][] {{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null}},
			new String[] {"�����ڵ�", "����", "����", "��ǰ�ڵ�"}));
		scrollPane.setViewportView(tbAcceptedReview);
		tbAcceptedReview.setFont(new Font("Gulim", Font.PLAIN, 12));
		
		JLabel lbMemberCode = new JLabel("ȸ���ڵ�");
		lbMemberCode.setFont(new Font("����", Font.PLAIN, 15));
		lbMemberCode.setBounds(262, 9, 56, 18);
		add(lbMemberCode);
		
		tfMemberCode.setEditable(false);
		tfMemberCode.setText(String.valueOf(memCode));
		tfMemberCode.setFont(new Font("����", Font.PLAIN, 15));
		tfMemberCode.setColumns(10);
		tfMemberCode.setBounds(330, 6, 116, 24);
		add(tfMemberCode);
	}

	void eventProc(int memCode, String id) {
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
				} catch (Exception e1) {
					// TODO: handle exception
				}
				tfReviewAvg.setText(String.valueOf(vo.getAvg()));
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
			String avg = tfReviewAvg.getText();
			String content = taReviewContent.getText();
			int code = Integer.parseInt(tfShopCode.getText());
			ReviewVO vo = new ReviewVO(avg, content, code);

			try {
				boolean poss = dao.isPossible(code);
				if(poss) {
					try {						
				dao.reviewRegist(vo);
//				if (cnt > 0) {
					clearScreen();
					System.out.println("��� �Ϸ�");
//				} else {
//				}
					}catch (Exception e3) {
						// TODO: handle exception
						System.out.println("��� ����");
					}	
					}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "���� ��� ����!! : " + e2.getMessage());
			}
			
		} else if (o == btnEdit) {
			vo = new ReviewVO();

			try {
				int cnt = dao.reviewEdit(vo);
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

		} else if (o == btnDelete) {
			vo = new ReviewVO();

			try {
				int cnt = dao.reviewDelete(vo);
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
		} else if (o == tfMachineName) {
//			String text = (String)tfMachineName.getText();
			selectTable();
		}
	}

	private void selectTable() {
		// TODO Auto-generated method stub
		String text = tfMachineName.getText();
		try {
			ArrayList list = new ArrayList();

			list = dao.ReviewSearch(text);
			System.out.println("�丮��Ʈ" + list);
			tmReview.data = list;
			tbAcceptedReview.setModel(tmReview);
//			tmReview.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "���� �˻� ���� : " + e.getMessage());

		}
	}

	void clearScreen() {
		tfReviewAvg.setText(null);
		taReviewContent.setText(null);
	}
}

class ReviewTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "�����ڵ�", "����", "����", "��ǰ�ڵ�" };

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
